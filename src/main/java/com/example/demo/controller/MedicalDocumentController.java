package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.MedicalDocument;
import com.example.demo.repo.MedicalDocumentRepository;

@RequestMapping("/api")
@RestController
public class MedicalDocumentController {

	@Autowired
	private MedicalDocumentRepository repository;

	@Autowired
	public MedicalDocumentController() {
		super();
	}

	@PostMapping("/medicalDocuments")
	public MedicalDocument insert(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id)
			throws Exception {
		MedicalDocument medicalDocument = MedicalDocument.newMedicalDocument(file, id);
		repository.insert(medicalDocument);
		return medicalDocument;
	}

	@PutMapping("/medicalDocuments")
	public MedicalDocument update(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id)
			throws Exception {
		MedicalDocument medicalDocument = MedicalDocument.newMedicalDocument(file, id);
		repository.insert(medicalDocument);
		return medicalDocument;
	}

	@GetMapping("/medicalDocuments")
	public List<MedicalDocument> getAll() throws IOException {
		return repository.findAll();
	}

	@GetMapping("/medicalDocuments/{id}")
	public ResponseEntity<MedicalDocument> get(@PathVariable Long id, HttpServletRequest request) throws Exception {
		Optional<MedicalDocument> medicalDocument = repository.find(id);
		if (medicalDocument.isPresent()) {
			return new ResponseEntity<MedicalDocument>(medicalDocument.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<MedicalDocument>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/medicalDocuments/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long id, HttpServletRequest request) throws Exception {
		Optional<MedicalDocument> medicalDocument = repository.find(id);
		if (medicalDocument.isPresent()) {
			MediaType contentType = MediaType.parseMediaType(medicalDocument.get().getFileType());
			byte[] byteResource = medicalDocument.get().getContent();
			ByteArrayResource resource = new ByteArrayResource(byteResource);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment;filename=" + medicalDocument.get().getFileName())
					.contentLength(byteResource.length).contentType(contentType).body(resource);
		} else {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}

	}
}
