package com.example.demo.model.event;

import java.io.IOException;

import com.example.demo.model.InfinityDBModel;

public class Event extends InfinityDBModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nodeName;

	public Event() {
		super();
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public static Event newNode(long id, String name) throws IOException {
		Event node = new Event();
		node.setId(id);
		node.setNodeName(name);
		return node;
	}

}
