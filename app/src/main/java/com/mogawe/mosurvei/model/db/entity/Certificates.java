package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.SerializedName;

public class Certificates{

	@SerializedName("code")
	private String code;

	@SerializedName("uuidJob")
	private String uuidJob;

	@SerializedName("instruction")
	private String instruction;

	@SerializedName("uuidCategory")
	private String uuidCategory;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("iconUrl")
	private String iconUrl;

	@SerializedName("category")
	private String category;

	@SerializedName("isActive")
	private boolean isActive;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("status")
	private String status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUuidJob() {
		return uuidJob;
	}

	public void setUuidJob(String uuidJob) {
		this.uuidJob = uuidJob;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getUuidCategory() {
		return uuidCategory;
	}

	public void setUuidCategory(String uuidCategory) {
		this.uuidCategory = uuidCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}