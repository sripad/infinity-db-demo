/**
 * 
 */
package com.example.demo.repo.infinitydb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.InfinityDBModel;
import com.google.gson.Gson;
import com.infinitydb.InfinityDB;
import com.infinitydb.map.db.InfinityDBMap;

/**
 * @author Sripad
 *
 */

@Component
public class InfinityDBMapRepository<T extends InfinityDBModel> {

	private Class<T> typeClass;
	private String KEY;
	private boolean compress = true;
	private InfinityDB db;
	private InfinityDBMap<String, String> map;
	private Map<String, String> nestedMap;

	@Autowired
	public InfinityDBMapRepository() throws IOException {
		super();
	}

	public void init(Class<T> cls) throws IOException {
		typeClass = cls;
		KEY = cls.getSimpleName();
		db = InfinityDBConnection.getConnection(KEY);
		map = new InfinityDBMap<String, String>(db);
		nestedMap = map.getMap(KEY);
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
		for (String json : nestedMap.values()) {
			T record = getRecord(json);
			records.add(record);
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
		String json = (String) nestedMap.get(String.valueOf(id));
		T record = getRecord(json);
		if (record.getId() == id) {
			return Optional.of(record);
		}
		return Optional.empty();
	}

	private T getRecord(String json) throws IOException {
		if (this.compress) {
			json = CompressionUtil.decompressB64(json);
		}
		T record = new Gson().fromJson(json, typeClass);
		return record;
	}

	/**
	 * Insert T into Infinity DB
	 * 
	 * @param T
	 * @return
	 * @throws Exception
	 */
	public T insert(T record) throws Exception {
		return update(record);
	}

	/**
	 * Update T into Infinity DB
	 * 
	 * @param T
	 * @return
	 * @throws Exception
	 */
	public T update(T record) throws Exception {
		try {
			String data = new Gson().toJson(record);
			if (this.compress) {
				data = CompressionUtil.compressAndReturnB64(data);
			}
			nestedMap.put(String.valueOf(record.getId()), data);
			map.put(KEY, data);
			db.commit();
			return record;
		} catch (Exception e) {
			System.err.println(String.format("Error while updating %s record %s", KEY, String.valueOf(record.getId())));
			e.printStackTrace();
			throw e;
		}
	}

}
