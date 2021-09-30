package com.mogawe.mosurvei.model.network.response.task;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Task.TaskPolyline;
import com.mogawe.mosurvei.model.bean.Task.Tasks;
import com.mogawe.mosurvei.model.db.entity.Ads;
import com.mogawe.mosurvei.model.db.entity.HistoryTaskDone;
import com.mogawe.mosurvei.model.db.entity.HomeContentV2;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.db.entity.TaskToday;
import com.mogawe.mosurvei.model.network.response.GaweanInstantDetailResponse;
import com.mogawe.mosurvei.model.network.response.GaweanInstantResponse;
import com.mogawe.mosurvei.model.network.response.GawenInformationResponse;
import com.mogawe.mosurvei.model.network.response.GaweanResponse;
import com.mogawe.mosurvei.model.network.response.GaweanResponseV2;
import com.mogawe.mosurvei.model.network.response.GenericResp;
import com.mogawe.mosurvei.model.network.response.GeoLocationSpotCheckResponse;
import com.mogawe.mosurvei.model.network.response.ItemResponse;
import com.mogawe.mosurvei.model.network.response.LocationResponse;
import com.mogawe.mosurvei.model.network.response.ProductResponse;
import com.mogawe.mosurvei.model.network.response.TaskToDoResponse;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;
import com.mogawe.mosurvei.model.network.response.timeline.TimelineActivityResponse;

import java.util.List;

public class MyTaskResponse extends GenericResp {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("object")
    @Expose
    private List<Task> task;
    @SerializedName("rowCount")
    @Expose
    private Integer row_count;
    @SerializedName("pageCount")
    @Expose
    private Integer page_count;
    @SerializedName("offset")
    @Expose

    private Integer offset;

    private List<Tasks> tasks;

    private List<TaskToday> taskTodayList;

    private List<Ads> adsList;

    private List<HomeContentV2> homeContents;

    private HomeContentsResponse homeContentsResponse;

    private List<HistoryTaskDone> historyTaskDone;

    private Task mtask;
    private String jobName;
    private TaskPolyline taskPolyline;
    private SectionSourceResponse sectionSourceResponse;
    private ItemResponse itemResponse;
    private GeoLocationSpotCheckResponse geoLocationSpotCheckResponse;
    private Task singleTask;
    private String uuidSession;
    private String type;
    private String activeUuidJobCategories;
    private GaweanResponse gaweanResponse;
    private GaweanResponseV2 gaweanResponseV2;
    private ProductResponse productResponse;
    private TimelineActivityResponse timelineActivityResponse;
    private GaweanInstantResponse gaweanInstantResponse;
    private LocationResponse locationResponse;
    private GawenInformationResponse gawenInformationResponse;
    private GaweanInstantDetailResponse gaweanInstantDetailResponse;
    private TaskToDoResponse taskToDoResponse;
    private long chatRoomId;
    @Ignore
    private String objectResponse;

    private RequestKey requestKey;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    public RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(RequestKey requestKey) {
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
        START_GAWEAN_PENDING,
        START_GAWEAN_OFFLINE,
        CLOSE_GAWEAN,
        UPLOAD_RESULT,
        UPLOAD_RESULT_GAMBAR,
        GET_TASK,
        GET_MY_TASK,
        GET_MY_TASK_TODAY,
        GET_MY_TASK_STARTING,
        GET_MY_TASK_STARTING_NULL,
        GET_TASK_POLYLINE,
        APPLY_GAWEAN,
        APPLY_GAWEAW_BATCH_SUCCESS,
        APPLY_GAWEAW_BATCH_PROGRESS,
        APPLY_GAWEAW_BATCH_FAILED,
        PIN_TASK,
        FAILED,
        CHAT_ROOM_ID_NOT_FOUND,
        GET_TASK_SPECIFIC_PROJECT,
        GET_SECTION,
        GET_DOWNLOAD_SECTION,
        GET_CONTENT_CATEGORIES,
        GET_WIDGET_ROW,
        GET_CONTENT_BY_CATEGORIES,
        CANCEL_GAWEAN,
        SEARCH_GAWEAN_RESULT,
        GET_ACTIVE_ORDER,
        SEARCH_GAWEAN_GRID_RESULT,
        PRODUCT_CATEGORY_RESULT,
        LOCATION_LIST_RESULT,
        SUB_PRODUCT_CATEGORY_RESULT,
        GET_ITEM_SEARCH,
        GET_GEO_LOCATION_SPOT_CHECK,
        GET_HISTORY_JOB_DONE,
        CONFIRM_ORDER,
        NOTHING,
        ALL_GAWEAN_LOADED,
        GET_TIMELINE_LIST,
        LIKE_TIMELINE_SUCCESS,
        UNLIKE_TIMELINE_SUCCESS,
        ACTIVATE_INSTANT_GAWEAN_SUCCESS,
        DEACTIVATE_INSTANT_GAWEAN_SUCCESS,
        ACCEPT_GAWEAN_OFFERING_SUCCESS,
        DECLINE_GAWEAN_OFFERING_SUCCESS,
        POST_STATUS_SUCCES,
        SUPPLIER_REGISTRATION_SUCCESS,
        SUPPLIER_CREATE_PRODUCT_SUCCESS,
        SUPPLIER_UPLOAD_IMAGE,
        SUPPLIER_GET_CATEGORY_PRODUCT,
        CONNECT_SUCCESS,
        DISCONNECT_SUCCESS,
        CHAT_ROOM_ID,
        CHAT_ROOM_SAVED,
        GOING_TO_SUCCESS,
        CANCEL_TO_SUCCESS,
        GET_GAWEAN_INFORMATION,
        UPDATE_SEQUENCE_SUCCESS,
        GET_TASK_TO_DO,
        GET_GAWEAN_INSTANT,
        GET_GAWEAN_INSTANT_INFORMATION,
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

    public List<HomeContentV2> getHomeContents() {
        return homeContents;
    }

    public void setHomeContents(List<HomeContentV2> homeContents) {
        this.homeContents = homeContents;
    }

    public List<Ads> getAdsList() {
        return adsList;
    }

    public void setAdsList(List<Ads> adsList) {
        this.adsList = adsList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getActiveUuidJobCategories() {
        return activeUuidJobCategories;
    }

    public void setActiveUuidJobCategories(String activeUuidJobCategories) {
        this.activeUuidJobCategories = activeUuidJobCategories;
    }

    public GaweanResponse getGaweanResponse() {
        return gaweanResponse;
    }

    public void setGaweanResponse(GaweanResponse gaweanResponse) {
        this.gaweanResponse = gaweanResponse;
    }

    public ProductResponse getProductResponse() {
        return productResponse;
    }

    public void setProductResponse(ProductResponse productResponse) {
        this.productResponse = productResponse;
    }

    public LocationResponse getLocationResponse() {
        return locationResponse;
    }

    public void setLocationResponse(LocationResponse locationResponse) {
        this.locationResponse = locationResponse;
    }

    public ItemResponse getItemResponse() {
        return itemResponse;
    }

    public void setItemResponse(ItemResponse itemResponse) {
        this.itemResponse = itemResponse;
    }

    public GeoLocationSpotCheckResponse getGeoLocationSpotCheckResponse() {
        return geoLocationSpotCheckResponse;
    }

    public void setGeoLocationSpotCheckResponse(GeoLocationSpotCheckResponse geoLocationSpotCheckResponse) {
        this.geoLocationSpotCheckResponse = geoLocationSpotCheckResponse;
    }

    public List<HistoryTaskDone> getHistoryTaskDone() {
        return historyTaskDone;
    }

    public void setHistoryTaskDone(List<HistoryTaskDone> historyTaskDone) {
        this.historyTaskDone = historyTaskDone;
    }

    public GaweanResponseV2 getGaweanResponseV2() {
        return gaweanResponseV2;
    }

    public void setGaweanResponseV2(GaweanResponseV2 gaweanResponseV2) {
        this.gaweanResponseV2 = gaweanResponseV2;
    }

    public TimelineActivityResponse getTimelineActivityResponse() {
        return timelineActivityResponse;
    }

    public void setTimelineActivityResponse(TimelineActivityResponse timelineActivityResponse) {
        this.timelineActivityResponse = timelineActivityResponse;
    }

    public HomeContentsResponse getHomeContentsResponse() {
        return homeContentsResponse;
    }

    public void setHomeContentsResponse(HomeContentsResponse homeContentsResponse) {
        this.homeContentsResponse = homeContentsResponse;
    }

    public long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public List<TaskToday> getTaskTodayList() {
        return taskTodayList;
    }

    public void setTaskTodayList(List<TaskToday> taskTodayList) {
        this.taskTodayList = taskTodayList;
    }

    public GawenInformationResponse getGawenInformationResponse() {
        return gawenInformationResponse;
    }

    public void setGawenInformationResponse(GawenInformationResponse gawenInformationResponse) {
        this.gawenInformationResponse = gawenInformationResponse;
    }

    public String getObjectResponse() {
        return objectResponse;
    }

    public void setObjectResponse(String objectResponse) {
        this.objectResponse = objectResponse;
    }

    public TaskToDoResponse getTaskToDoResponse() {
        return taskToDoResponse;
    }

    public void setTaskToDoResponse(TaskToDoResponse taskToDoResponse) {
        this.taskToDoResponse = taskToDoResponse;
    }

    public GaweanInstantResponse getGaweanInstantResponse() {
        return gaweanInstantResponse;
    }

    public void setGaweanInstantResponse(GaweanInstantResponse gaweanInstantResponse) {
        this.gaweanInstantResponse = gaweanInstantResponse;
    }

    public GaweanInstantDetailResponse getGaweanInstantDetailResponse() {
        return gaweanInstantDetailResponse;
    }

    public void setGaweanInstantDetailResponse(GaweanInstantDetailResponse gaweanInstantDetailResponse) {
        this.gaweanInstantDetailResponse = gaweanInstantDetailResponse;
    }
}
