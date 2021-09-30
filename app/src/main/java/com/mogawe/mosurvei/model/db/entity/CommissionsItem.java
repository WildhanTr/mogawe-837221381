package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommissionsItem implements Serializable {

	@SerializedName("sequence")
	private int sequence;

	@SerializedName("price")
	private double price;

	@SerializedName("uuidProduct")
	private String uuidProduct;

	@SerializedName("name")
	private String name;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("value")
	private int value;

	@SerializedName("commissionType")
	private String commissionType;

	public CommissionsItem() {
	}

	public CommissionsItem(double price, String commissionType) {
		this.price = price;
		this.commissionType = commissionType;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUuidProduct() {
		return uuidProduct;
	}

	public void setUuidProduct(String uuidProduct) {
		this.uuidProduct = uuidProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}
}