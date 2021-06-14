/**
 * 
 */
package com.example.demo.repo.infinitydb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
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
public class InfinityDBLargeObjectRepository<T extends InfinityDBModel> {

	private Class<T> typeClass;
	private String KEY;
	private boolean compress = false;
	private InfinityDB db;
	private InfinityDBMap<String, String> map;

	@Autowired
	public InfinityDBLargeObjectRepository() throws IOException {
		super();
	}

	public void init(Class<T> cls) throws IOException {
		typeClass = cls;
		KEY = cls.getSimpleName();
		db = InfinityDBConnection.getConnection(KEY);
		map = new InfinityDBMap<String, String>(db);
	}

	/**
	 * Find all T records from Infinity DB
	 * 
	 * @return List<T>
	 * @throws IOException
	 */
	public List<T> findAll() throws IOException {
		List<T> records = new ArrayList<T>();
		for (String id : map.keySet()) {
			T record = getRecord(id);
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
		String key = String.valueOf(id);
		T record = getRecord(key);
		if (record.getId() == id) {
			return Optional.of(record);
		}
		return Optional.empty();
	}

	private T getRecord(String id) throws IOException {
		try (Reader reader = map.getReader(id); BufferedReader br = new BufferedReader(reader);) {
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			// delete the last new line separator
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);

			String json = stringBuilder.toString();

			T record = new Gson().fromJson(json, typeClass);
			return record;
		} catch (IOException e) {
			System.err.println(String.format("Error while reading %s record %s", KEY, id));
			e.printStackTrace();
			throw e;
		}
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
		try (Writer writer = map.getWriter(String.valueOf(record.getId()));
				BufferedWriter bufferWriter = new BufferedWriter(writer);) {
			String data = new Gson().toJson(record);
			bufferWriter.write(data);
			db.commit();
			return record;
		} catch (IOException e) {
			System.err.println(String.format("Error while updating %s record %s", KEY, String.valueOf(record.getId())));
			e.printStackTrace();
			throw e;
		}
	}

}
