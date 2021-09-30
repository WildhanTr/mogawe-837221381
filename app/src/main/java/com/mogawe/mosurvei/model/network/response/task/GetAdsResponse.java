package com.mogawe.mosurvei.model.network.response.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.HomeContent;
import com.mogawe.mosurvei.model.bean.Task.TaskPolyline;
import com.mogawe.mosurvei.model.bean.Task.Tasks;
import com.mogawe.mosurvei.model.db.entity.Ads;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.network.response.GenericResp;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;

import java.util.List;

public class GetAdsResponse extends GenericResp {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("object")
    @Expose
    private List<Ads> ads;
    @SerializedName("rowCount")
    @Expose
    private Integer row_count;
    @SerializedName("pageCount")
    @Expose
    private Integer page_count;

    private List<Tasks> tasks;

    private List<HomeContent> homeContents;

    private Task mtask;
    private String jobName;
    private TaskPolyline taskPolyline;
    private SectionSourceResponse sectionSourceResponse;
    private Task singleTask;
    private String uuidSession;

    private MyTaskResponse.RequestKey requestKey;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Ads> getAds() {
        return ads;
    }

    public void setAds(List<Ads> ads) {
        this.ads = ads;
    }

    public MyTaskResponse.RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(MyTaskResponse.RequestKey requestKey) {
        this.requestKey = requestKey;
    }

    public Task getMtask() {
        return mtask;
    }

    public void setMtask(Task mtask) {
        this.mtask = mtask;
    }

    public enum RequestKey {
        START_GAWEAN,
        CLOSE_GAWEAN,
        UPLOAD_RESULT,
        UPLOAD_RESULT_GAMBAR,
        GET_TASK,
        GET_MY_TASK,
        GET_TASK_POLYLINE,
        APPLY_GAWEAN,
        APPLY_GAWEAW_BATCH_SUCCESS,
        APPLY_GAWEAW_BATCH_PROGRESS,
        APPLY_GAWEAW_BATCH_FAILED,
        PIN_TASK,
        FAILED,
        GET_TASK_SPECIFIC_PROJECT,
        GET_SECTION,
        GET_CONTENT_CATEGORIES,
        GET_CONTENT_BY_CATEGORIES
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public TaskPolyline getTaskPolyline() {
        return taskPolyline;
    }

    public void setTaskPolyline(TaskPolyline taskPolyline) {
        this.taskPolyline = taskPolyline;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public SectionSourceResponse getSectionSourceResponse() {
        return sectionSourceResponse;
    }

    public void setSectionSourceResponse(SectionSourceResponse sectionSourceResponse) {
        this.sectionSourceResponse = sectionSourceResponse;
    }

    public Task getSingleTask() {
        return singleTask;
    }

    public void setSingleTask(Task singleTask) {
        this.singleTask = singleTask;
    }

    public String getUuidSession() {
        return uuidSession;
    }

    public void setUuidSession(String uuidSession) {
        this.uuidSession = uuidSession;
    }

    public Integer getRow_count() {
        return row_count;
    }

    public void setRow_count(Integer row_count) {
        this.row_count = row_count;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public List<HomeContent> getHomeContents() {
        return homeContents;
    }

    public void setHomeContents(List<HomeContent> homeContents) {
        this.homeContents = homeContents;
    }
}
