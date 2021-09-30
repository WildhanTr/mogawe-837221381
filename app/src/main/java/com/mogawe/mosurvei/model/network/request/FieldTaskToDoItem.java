package com.mogawe.mosurvei.model.network.request;

import com.google.gson.annotations.SerializedName;

public class FieldTaskToDoItem{

	@SerializedName("uuid")
	private String uuid;

	public String getUuid(){
		return uuid;
	}
}