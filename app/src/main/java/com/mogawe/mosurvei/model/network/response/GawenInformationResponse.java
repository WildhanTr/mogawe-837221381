package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.TaskInformation;

public class GawenInformationResponse extends GenericResp{

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("object")
	private TaskInformation taskInformation;

	@SerializedName("object2")
	private TaskInformation object2;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public TaskInformation getObject2() {
		return object2;
	}

	public void setObject2(TaskInformation object2) {
		this.object2 = object2;
	}

	public TaskInformation getTaskInformation() {
		return taskInformation;
	}

	public void setTaskInformation(TaskInformation taskInformation) {
		this.taskInformation = taskInformation;
	}
}