package com.example.demo.model.node;

import java.io.IOException;

import com.example.demo.model.InfinityDBModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class Node extends InfinityDBModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("holder_id")
	@SerializedName(value = "holder_id")
	private Long holderId;

	private String role;

	private String description;

	private String status;

	@JsonProperty("bc_address")
	@SerializedName(value = "bc_address")
	private String bcAddress;

	@JsonProperty("cw_id")
	@SerializedName(value = "cw_id")
	private String cwId;

	@JsonProperty("ec_public_key")
	@SerializedName(value = "ec_public_key")
	private String ecPublicKey;

	@JsonProperty("role_type")
	@SerializedName(value = "role_type")
	private String roleType;

	@JsonProperty("group_id")
	@SerializedName(value = "group_id")
	private String groupId;

	@JsonProperty("smart_contract_address")
	@SerializedName(value = "smart_contract_address")
	private String smartContractAddress;

	@JsonProperty("role_class")
	@SerializedName(value = "role_class")
	private String roleClass;

	@JsonProperty("role_code")
	@SerializedName(value = "role_code")
	private String roleCode;

	@JsonProperty("role_id")
	@SerializedName(value = "role_id")
	private Integer roleId;

	public Node() {
		super();
	}

	public Long getHolderId() {
		return holderId;
	}

	public Node setHolderId(Long holderId) {
		this.holderId = holderId;
		return this;
	}

	public String getRole() {
		return role;
	}

	public Node setRole(String role) {
		this.role = role;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Node setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Node setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getBcAddress() {
		return bcAddress;
	}

	public Node setBcAddress(String bcAddress) {
		this.bcAddress = bcAddress;
		return this;
	}

	public String getCwId() {
		return cwId;
	}

	public Node setCwId(String cwId) {
		this.cwId = cwId;
		return this;
	}

	public String getEcPublicKey() {
		return ecPublicKey;
	}

	public Node setEcPublicKey(String ecPublicKey) {
		this.ecPublicKey = ecPublicKey;
		return this;
	}

	public String getRoleType() {
		return roleType;
	}

	public Node setRoleType(String roleType) {
		this.roleType = roleType;
		return this;
	}

	public String getGroupId() {
		return groupId;
	}

	public Node setGroupId(String groupId) {
		this.groupId = groupId;
		return this;
	}

	public String getSmartContractAddress() {
		return smartContractAddress;
	}

	public Node setSmartContractAddress(String smartContractAddress) {
		this.smartContractAddress = smartContractAddress;
		return this;
	}

	public String getRoleClass() {
		return roleClass;
	}

	public Node setRoleClass(String roleClass) {
		this.roleClass = roleClass;
		return this;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public Node setRoleCode(String roleCode) {
		this.roleCode = roleCode;
		return this;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public Node setRoleId(Integer roleId) {
		this.roleId = roleId;
		return this;
	}

	public static Node newNode(long id) throws IOException {
		Node node = new Node();
		node.setId(id);
		return node;
	}

}
