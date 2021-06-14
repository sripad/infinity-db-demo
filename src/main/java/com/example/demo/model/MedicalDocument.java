package com.example.demo.model;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repo.infinitydb.FileService;

public class MedicalDocument extends InfinityDBModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private Long size;
	private byte[] content;

	public MedicalDocument() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public static MedicalDocument newMedicalDocument(MultipartFile file, long id) throws IOException {
		MedicalDocument medicalDocument = new MedicalDocument();
		medicalDocument.setId(id);
		String fileName = FileService.getOriginalFileName(file);
		byte[] content = FileService.getByteArray(file);
		if (medicalDocument.getFileName() == null) {
			medicalDocument.setFileName(fileName);
		}
		medicalDocument.setFileDownloadUri("/api/medicalDocuments/" + id);
		medicalDocument.setContent(content);
		medicalDocument.setFileType(file.getContentType());
		medicalDocument.setSize(file.getSize());
		return medicalDocument;
	}

}
