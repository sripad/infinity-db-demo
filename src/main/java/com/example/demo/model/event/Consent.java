package com.example.demo.model.event;

import java.io.IOException;

import com.example.demo.model.InfinityDBModel;

public class Consent extends InfinityDBModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nodeName;

	public Consent() {
		super();
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public static Consent newNode(long id, String name) throws IOException {
		Consent node = new Consent();
		node.setId(id);
		node.setNodeName(name);
		return node;
	}

}
