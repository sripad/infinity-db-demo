package com.example.demo.repo.infinitydb;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.infinitydb.InfinityDB;

public class InfinityDBConnection {

	// This is for the maximum of the data blocks in memory.
	static long MAX_CACHE_SIZE = 1000_000_000;
	// Passwords are char[] so you can zero them out to minimize time in memory.
	// A String can't be zeroed.
	static final char[] PASS_WORD = new char[] { 'x', 'Q', '6', 'i', '$' };

	/*
	 * 0 is for regular 128-bit AES strength with no export issues, 1 is for strong
	 * 256-bit AES.
	 * 
	 * This is not required on open, but set by create permanently. In the far
	 * future there will be more if these two become obsolete.
	 * 
	 * Note that a database created with strong encryption can only be opened by a
	 * JVM with strong encryption enabled. Some countries control the use or
	 * distribution of strong encryption. However, it can normally be enabled with
	 * simple changes to files in $JAVA_HOME/jre/lib/security.
	 */
	static final int ENCRYPTION_PARAMETERS_NUMBER = 1;

	static Map<String, InfinityDB> map = new HashMap<String, InfinityDB>();

	public static InfinityDB getConnection(String model) throws IOException {
		if (map.get(model) == null) {
			InfinityDB db = getEncryptedConnection("./db/" + model + ".infdb");
			map.put(model, db);
			return db;
		}
		return map.get(model);
	}

	/**
	 * create InfinityDB encrypted storage module
	 * 
	 * @param dbFilePath
	 * @return
	 * @throws IOException
	 */
	public static InfinityDB getEncryptedConnection(String dbFilePath) throws IOException {
		File dbFile = new File(dbFilePath);
//		if (dbFile.exists()) {
//			return InfinityDB.open(dbFile.getAbsolutePath(), true);
//		}
		return InfinityDB.create(dbFile.getAbsolutePath(), true, MAX_CACHE_SIZE, PASS_WORD,
				ENCRYPTION_PARAMETERS_NUMBER);

	}

}
