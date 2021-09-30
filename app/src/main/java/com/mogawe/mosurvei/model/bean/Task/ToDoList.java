package com.mogawe.mosurvei.model.bean.Task;

import com.google.gson.annotations.SerializedName;

public class ToDoList {

    @SerializedName("sequence")
    private int sequence;

    @SerializedName("fee")
    private Double fee;

    @SerializedName("pictureUrl")
    private String pictureUrl;

    @SerializedName("uuidCategory")
    private String uuidCategory;

    @SerializedName("name")
    private String name;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("batchName")
    private String batchName;

    @SerializedName("expiredDate")
    private String expiredDate;

    @SerializedName("limit")
    private Integer limit;

    @SerializedName("resultCount")
    private Integer resultCount;

    @SerializedName("uuidRef")
    private String uuidRef;

    @SerializedName("statusJob")
    private String statusJob;

    @SerializedName("statusTask")
    private String statusTask;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUuidRef() {
        return uuidRef;
    }

    public void setUuidRef(String uuidRef) {
        this.uuidRef = uuidRef;
    }

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}

    public String getStatusJob() {
        return statusJob;
    }

    public void setStatusJob(String statusJob) {
        this.statusJob = statusJob;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }
}