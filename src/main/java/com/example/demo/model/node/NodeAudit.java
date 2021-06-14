package com.example.demo.model.node;

import java.io.IOException;

import com.example.demo.model.InfinityDBModel;

public class NodeAudit extends InfinityDBModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	double cpuUsage;
	
	double memoryUsage;

	private String nodeName;

	public NodeAudit() {
		super();
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public static NodeAudit newNode(long id, String name) throws IOException {
		NodeAudit node = new NodeAudit();
		node.setId(id);
		node.setNodeName(name);
		return node;
	}

}
