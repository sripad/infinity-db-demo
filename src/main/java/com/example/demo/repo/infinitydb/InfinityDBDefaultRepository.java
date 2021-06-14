/**
 * 
 */
package com.example.demo.repo.infinitydb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.InfinityDBModel;
import com.google.gson.Gson;
import com.infinitydb.Cu;
import com.infinitydb.InfinityDB;

/**
 * @author Sripad
 *
 */

@Component
public class InfinityDBDefaultRepository<T extends InfinityDBModel> {

	private Class<T> typeClass;
	private String KEY;
	private boolean compress = true;
	private InfinityDB db;

	@Autowired
	public InfinityDBDefaultRepository() throws IOException {
		super();
	}

	public void init(Class<T> cls) throws IOException {
		typeClass = cls;
		KEY = cls.getSimpleName();
		db = InfinityDBConnection.getConnection(KEY);
	}

	/**
	 * Is compression required for the record value
	 * 
	 * @param compress
	 */
	public void setCompress(boolean compress) {
		this.compress = compress;
	}

	/**
	 * Find all T records from Infinity DB
	 * 
	 * @return List<T>
	 * @throws IOException
	 */
	public List<T> findAll() throws IOException {
		List<T> records = new ArrayList<T>();
		try (Cu cu = Cu.alloc()) {
			cu.clear().append(KEY);
			int prefixLength = cu.length();
			while (db.next(cu, prefixLength)) {
				String json = cu.stringAt(prefixLength);
				if (this.compress) {
					json = CompressionUtil.decompressB64(json);
				}
				T record = new Gson().fromJson(json, typeClass);
				records.add(record);
			}
		}
		return records;
	}

	/**
	 * Find T from Infinity DB
	 * 
	 * @param offset
	 * @param size
	 * @return
	 * @throws IOException
	 */
	public Optional<T> find(long id) throws IOException {
		for (T record : findAll()) {
			if (record.getId() == id) {
				return Optional.of(record);
			}
		}
		return Optional.empty();
	}

	/**
	 * Insert T into Infinity DB
	 * 
	 * @param T
	 * @return
	 * @throws Exception
	 */
	public T insert(T record) throws Exception {
		try (Cu cu = Cu.alloc()) {
			String data = new Gson().toJson(record);
			if (this.compress) {
				data = CompressionUtil.compressAndReturnB64(data);
			}
			cu.clear().append(KEY).append(data);
			db.insert(cu);
			db.commit();
			return record;
		} catch (Exception e) {
			System.err.println(String.format("Error while inserting %s record", KEY));
			e.printStackTrace();
			throw e;
		}
	}

}
