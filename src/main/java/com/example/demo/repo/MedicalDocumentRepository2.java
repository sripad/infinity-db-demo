package com.example.demo.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.MedicalDocument;
import com.example.demo.repo.infinitydb.CompressionUtil;
import com.example.demo.repo.infinitydb.InfinityDBConnection;
import com.google.gson.Gson;
import com.infinitydb.Cu;
import com.infinitydb.InfinityDB;

@Component
public class MedicalDocumentRepository2 {

	private InfinityDB db;
//	private InfinityDBMap<String, String> rootMap;
//	private InfinityDBMap<String, byte[]> nestedMap;
	private static final String KEY = MedicalDocument.class.getName();

	@Autowired
	public MedicalDocumentRepository2() throws IOException {
		db = InfinityDBConnection.getConnection(MedicalDocument.class.getSimpleName());
//		rootMap = new InfinityDBMap<String, String>(db);
//		nestedMap = rootMap.getMap(KEY);
	}

	/**
	 * Find all MedicalDocument
	 * 
	 * @return List<MedicalDocument>
	 * @throws IOException
	 */
	public List<MedicalDocument> findAll() throws IOException {
		List<MedicalDocument> medicalRecords = new ArrayList<MedicalDocument>();
		try (Cu cu = Cu.alloc()) {
			cu.clear().append(KEY);
			int prefixLength = cu.length();
			while (db.next(cu, prefixLength)) {
				String json = cu.stringAt(prefixLength);
				MedicalDocument doc = new Gson().fromJson(CompressionUtil.decompressB64(json), MedicalDocument.class);
				medicalRecords.add(doc);
			}
		}
		return medicalRecords;
	}

	/**
	 * Find MedicalDocument from infinity db
	 * 
	 * @param offset
	 * @param size
	 * @return
	 * @throws IOException
	 */
	public MedicalDocument find(long medicalDocumentId) throws IOException {
		for (MedicalDocument medicalDocument : findAll()) {
			if (medicalDocument.getId() == medicalDocumentId) {
				return medicalDocument;
			}
		}
		return null;
	}

	/**
	 * Insert MedicalDocument into infinity db
	 * 
	 * @param MedicalDocument
	 * @return
	 * @throws Exception
	 */
	public MedicalDocument insert(MedicalDocument medicalRecord) throws Exception {
		try (Cu cu = Cu.alloc()) {
			// nestedMap.put(String.valueOf(medicalRecord.getMedicalDocumentId()),
			// FileService.serialize(medicalRecord));
//			byte[] bytes = FileService.serialize(medicalRecord);
//			String decoded = new String(bytes);
			String data = CompressionUtil.compressAndReturnB64(new Gson().toJson(medicalRecord));
			cu.clear().append(KEY).append(data);
			db.insert(cu);
			db.commit();
			return medicalRecord;
		} catch (Exception e) {
			System.err.println("Unable to insert medical record.");
			e.printStackTrace();
			throw e;
		}
	}

}
