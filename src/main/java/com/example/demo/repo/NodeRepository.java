package com.example.demo.repo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.node.Node;
import com.example.demo.repo.infinitydb.InfinityDBMapRepository;

@Component
public class NodeRepository extends InfinityDBMapRepository<Node> {

	@Autowired
	@SuppressWarnings("unused")
	public NodeRepository() throws IOException {
		super();
		init(Node.class);
	}

}