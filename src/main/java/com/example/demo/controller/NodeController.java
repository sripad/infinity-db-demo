package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.node.Node;
import com.example.demo.repo.NodeRepository;

@RequestMapping("/api")
@RestController
public class NodeController {

	@Autowired
	private NodeRepository repository;

	@Autowired
	public NodeController() {
		super();
	}

	@PostMapping("/nodes")
	public Node create(@RequestBody Node node) throws Exception {
		repository.insert(node);
		return node;
	}

	@GetMapping("/nodes")
	public List<Node> getAll() throws IOException {
		return repository.findAll();
	}

	@GetMapping("/nodes/{id}")
	public ResponseEntity<Node> getById(@PathVariable Long id) throws Exception {
		Optional<Node> node = repository.find(id);
		if (node.isPresent()) {
			return new ResponseEntity<Node>(node.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Node>(HttpStatus.NOT_FOUND);
		}
	}
}
