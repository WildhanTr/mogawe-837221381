package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WidgetEntity implements Serializable {

	@SerializedName("actionValue")
	private String actionValue;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("hasComment")
	private boolean hasComment;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("uuidWidgetType")
	private String uuidWidgetType;

	@SerializedName("sequence")
	private int sequence;

	@SerializedName("size")
	private String size;

	@SerializedName("widgetTypeCode")
	private String widgetTypeCode;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("hasLike")
	private boolean hasLike;

	@SerializedName("name")
	private String name;

	@SerializedName("action")
	private String action;

	@SerializedName("value")
	private List<Chart> value;

	@SerializedName("uuidWidgetRow")
	private String uuidWidgetRow;

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isHasComment() {
		return hasComment;
	}

	public void setHasComment(boolean hasComment) {
		this.hasComment = hasComment;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuidWidgetType() {
		return uuidWidgetType;
	}

	public void setUuidWidgetType(String uuidWidgetType) {
		this.uuidWidgetType = uuidWidgetType;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getWidgetTypeCode() {
		return widgetTypeCode;
	}

	public void setWidgetTypeCode(String widgetTypeCode) {
		this.widgetTypeCode = widgetTypeCode;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isHasLike() {
		return hasLike;
	}

	public void setHasLike(boolean hasLike) {
		this.hasLike = hasLike;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<Chart> getValue() {
		return value;
	}

	public void setValue(List<Chart> value) {
		this.value = value;
	}

	public String getUuidWidgetRow() {
		return uuidWidgetRow;
	}

	public void setUuidWidgetRow(String uuidWidgetRow) {
		this.uuidWidgetRow = uuidWidgetRow;
	}
}