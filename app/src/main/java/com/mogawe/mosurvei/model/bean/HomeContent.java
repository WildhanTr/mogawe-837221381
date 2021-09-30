package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Ads;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.db.entity.TimelineActivityEntity;
import com.mogawe.mosurvei.model.db.entity.WidgetEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeContent {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("name")
    @Expose
    private String content_name;
    @SerializedName("type")
    @Expose
    private String content_type;
    @SerializedName("contentCount")
    @Expose
    private Integer content_count;
    @SerializedName("uuidJobCategoryType")
    @Expose
    private String uuidJobCategoryType;
    @SerializedName("jobCategoryTypeName")
    @Expose
    private String jobCategoryTypeName;
    @SerializedName("sequence")
    @Expose
    private String sequence;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("jobCount")
    @Expose
    private Integer jobCount;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("actionValue")
    @Expose
    private String actionValue;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("jobs")
    @Expose
    private List<Task> tasks = new ArrayList<>();
    @SerializedName("postTimelines")
    @Expose
    private List<TimelineActivityEntity> postTimelines = new ArrayList<>();
    @SerializedName("widgets")
    @Expose
    private List<WidgetEntity> widgets = new ArrayList<>();

    private List<Ads> adverts = new ArrayList<>();
    private Integer rowCount;
    private Integer currentPage;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getContent_name() {
        return content_name;
    }

    public void setContent_name(String content_name) {
        this.content_name = content_name;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public Integer getContent_count() {
        return content_count;
    }

    public void setContent_count(Integer content_count) {
        this.content_count = content_count;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Ads> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Ads> adverts) {
        this.adverts = adverts;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getUuidJobCategoryType() {
        return uuidJobCategoryType;
    }

    public void setUuidJobCategoryType(String uuidJobCategoryType) {
        this.uuidJobCategoryType = uuidJobCategoryType;
    }

    public String getJobCategoryTypeName() {
        return jobCategoryTypeName;
    }

    public void setJobCategoryTypeName(String jobCategoryTypeName) {
        this.jobCategoryTypeName = jobCategoryTypeName;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
