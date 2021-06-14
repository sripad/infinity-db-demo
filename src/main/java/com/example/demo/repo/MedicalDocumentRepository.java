package com.example.demo.repo;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.example.demo.model.MedicalDocument;
import com.example.demo.repo.infinitydb.InfinityDBLargeObjectRepository;

@Component
public class MedicalDocumentRepository extends InfinityDBLargeObjectRepository<MedicalDocument> {

	public MedicalDocumentRepository() throws IOException {
		super();
		init(MedicalDocument.class);
	}

}
