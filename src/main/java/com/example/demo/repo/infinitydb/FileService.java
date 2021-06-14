package com.example.demo.repo.infinitydb;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileService {

	public static byte[] getByteArray(MultipartFile file) throws IOException {
		InputStream initialStream = file.getInputStream();
		byte[] byteArray = new byte[initialStream.available()];
		initialStream.read(byteArray);
		return byteArray;
	}

	public static String getOriginalFileName(MultipartFile file) {
		return StringUtils.cleanPath(file.getOriginalFilename());
	}

	public static byte[] serialize(Serializable obj) {
		byte[] data = SerializationUtils.serialize(obj);
		return data;
	}

	public static Serializable deserialize(byte[] data) throws IOException {
		return SerializationUtils.deserialize(data);
	}

}
