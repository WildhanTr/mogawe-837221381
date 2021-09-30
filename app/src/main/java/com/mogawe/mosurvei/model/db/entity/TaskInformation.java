package com.mogawe.mosurvei.model.db.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Certificate;

public class TaskInformation {

	@SerializedName("statusNotes")
	private String statusNotes;

	@SerializedName("uuidLocationType")
	private String uuidLocationType;

	@SerializedName("uuidTaskOrderJob")
	private String uuidTaskOrderJob;

	@SerializedName("resultCount")
	private Integer resultCount;

	@SerializedName("uuidOrder")
	private String uuidOrder;

	@SerializedName("endDate")
	private long endDate;

	@SerializedName("jobTypeName")
	private String jobTypeName;

	@SerializedName("howTo")
	private String howTo;

	@SerializedName("expiredDate")
	private long expiredDate;

	@SerializedName("fee")
	private int fee;

	@SerializedName("jobColor")
	private String jobColor;

	@SerializedName("type")
	private String type;

	@SerializedName("jobBanner")
	private String jobBanner;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("points")
	private int points;

	@SerializedName("jobPicture")
	private String jobPicture;

	@SerializedName("jobTimes")
	private List<JobTimes> jobTimes;

	@SerializedName("idTask")
	private String idTask;

	@SerializedName("uuidMogawers")
	private String uuidMogawers;

	@SerializedName("postMessage")
	private String postMessage;

	@SerializedName("limit")
	private int limit;

	@SerializedName("locationLevel")
	private String locationLevel;

	@SerializedName("id")
	private int id;

	@SerializedName("jobTextColor")
	private String jobTextColor;

	@SerializedName("radius")
	private int radius;

	@SerializedName("receivedDate")
	private long receivedDate;

	@SerializedName("minimumLevel")
	private int minimumLevel;

	@SerializedName("longitude")
	private Double longitude;

	@SerializedName("jobName")
	private String jobName;

	@SerializedName("defaulLimit")
	private int defaulLimit;

	@SerializedName("updatedBy")
	private long updatedBy;

	@SerializedName("uuidTaskOrder")
	private String uuidTaskOrder;

	@SerializedName("isHaveArea")
	private boolean isHaveArea;

	@SerializedName("uuidLocation")
	private String uuidLocation;

	@SerializedName("uuidLocationLevel")
	private String uuidLocationLevel;

	@SerializedName("jobId")
	private String jobId;

	@SerializedName("isAssigned")
	private boolean isAssigned;

	@SerializedName("certificates")
	private List<Certificate> certificates;

	@SerializedName("isAutoApproved")
	private boolean isAutoApproved;

	@SerializedName("uuidProject")
	private String uuidProject;

	@SerializedName("name")
	private String name;

	@SerializedName("jobDescription")
	private String jobDescription;

	@SerializedName("isDisplayable")
	private boolean isDisplayable;

	@SerializedName("isStarting")
	private boolean isStarting;

	@SerializedName("startDate")
	private long startDate;

	@SerializedName("groupTask")
	private String groupTask;

	@SerializedName("jobActions")
	private String jobActions;

	@SerializedName("code")
	private String code;

	@SerializedName("latitude")
	private Double latitude;

	@SerializedName("locationDescription")
	private String  locationDescription;

	@SerializedName("description")
	private String description;

	@SerializedName("locationAddress")
	private String locationAddress;

	@SerializedName("assignmentType")
	private String assignmentType;

	@SerializedName("isQcNeeded")
	private boolean isQcNeeded;

	@SerializedName("duration")
	private String duration;

	@SerializedName("uuidJob")
	private String uuidJob;

	@SerializedName("jobLimit")
	private String jobLimit;

	@SerializedName("tutorial")
	private String tutorial;

	@SerializedName("mogawersCode")
	private String mogawersCode;

	@SerializedName("isScreening")
	private String isScreening;

	@SerializedName("email")
	private String email;

	@SerializedName("statusTask")
	private String statusTask;

	@SerializedName("brief")
	private String brief;

	@SerializedName("locationName")
	private String locationName;

	@SerializedName("isPinned")
	private boolean isPinned;

	@SerializedName("isPublished")
	private boolean isPublished;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("target")
	private int target;

	@SerializedName("schedule")
	private String schedule;

	@SerializedName("createdDate")
	private long createdDate;

	@SerializedName("createdBy")
	private String createdBy;

	@SerializedName("idMogawers")
	private int idMogawers;

	@SerializedName("category")
	private String category;

	@SerializedName("uuidJobType")
	private String uuidJobType;

	@SerializedName("batchName")
	private String batchName;

	@SerializedName("isDirect")
	private boolean isDirect;

	public String getStatusNotes() {
		return statusNotes;
	}

	public void setStatusNotes(String statusNotes) {
		this.statusNotes = statusNotes;
	}

	public String getUuidLocationType() {
		return uuidLocationType;
	}

	public void setUuidLocationType(String uuidLocationType) {
		this.uuidLocationType = uuidLocationType;
	}

	public String getUuidTaskOrderJob() {
		return uuidTaskOrderJob;
	}

	public void setUuidTaskOrderJob(String uuidTaskOrderJob) {
		this.uuidTaskOrderJob = uuidTaskOrderJob;
	}

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}

	public String getUuidOrder() {
		return uuidOrder;
	}

	public void setUuidOrder(String uuidOrder) {
		this.uuidOrder = uuidOrder;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public String getJobTypeName() {
		return jobTypeName;
	}

	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}

	public String getHowTo() {
		return howTo;
	}

	public void setHowTo(String howTo) {
		this.howTo = howTo;
	}

	public long getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(long expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getJobColor() {
		return jobColor;
	}

	public void setJobColor(String jobColor) {
		this.jobColor = jobColor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJobBanner() {
		return jobBanner;
	}

	public void setJobBanner(String jobBanner) {
		this.jobBanner = jobBanner;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getJobPicture() {
		return jobPicture;
	}

	public void setJobPicture(String jobPicture) {
		this.jobPicture = jobPicture;
	}

	public List<JobTimes> getJobTimes() {
		return jobTimes;
	}

	public void setJobTimes(List<JobTimes> jobTimes) {
		this.jobTimes = jobTimes;
	}

	public String getIdTask() {
		return idTask;
	}

	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	public String getUuidMogawers() {
		return uuidMogawers;
	}

	public void setUuidMogawers(String uuidMogawers) {
		this.uuidMogawers = uuidMogawers;
	}

	public String getPostMessage() {
		return postMessage;
	}

	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getLocationLevel() {
		return locationLevel;
	}

	public void setLocationLevel(String locationLevel) {
		this.locationLevel = locationLevel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobTextColor() {
		return jobTextColor;
	}

	public void setJobTextColor(String jobTextColor) {
		this.jobTextColor = jobTextColor;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public long getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(long receivedDate) {
		this.receivedDate = receivedDate;
	}

	public int getMinimumLevel() {
		return minimumLevel;
	}

	public void setMinimumLevel(int minimumLevel) {
		this.minimumLevel = minimumLevel;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getDefaulLimit() {
		return defaulLimit;
	}

	public void setDefaulLimit(int defaulLimit) {
		this.defaulLimit = defaulLimit;
	}

	public long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUuidTaskOrder() {
		return uuidTaskOrder;
	}

	public void setUuidTaskOrder(String uuidTaskOrder) {
		this.uuidTaskOrder = uuidTaskOrder;
	}

	public boolean isHaveArea() {
		return isHaveArea;
	}

	public void setHaveArea(boolean haveArea) {
		isHaveArea = haveArea;
	}

	public String getUuidLocation() {
		return uuidLocation;
	}

	public void setUuidLocation(String uuidLocation) {
		this.uuidLocation = uuidLocation;
	}

	public String getUuidLocationLevel() {
		return uuidLocationLevel;
	}

	public void setUuidLocationLevel(String uuidLocationLevel) {
		this.uuidLocationLevel = uuidLocationLevel;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean assigned) {
		isAssigned = assigned;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	public boolean isAutoApproved() {
		return isAutoApproved;
	}

	public void setAutoApproved(boolean autoApproved) {
		isAutoApproved = autoApproved;
	}

	public String getUuidProject() {
		return uuidProject;
	}

	public void setUuidProject(String uuidProject) {
		this.uuidProject = uuidProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public boolean isDisplayable() {
		return isDisplayable;
	}

	public void setDisplayable(boolean displayable) {
		isDisplayable = displayable;
	}

	public boolean isStarting() {
		return isStarting;
	}

	public void setStarting(boolean starting) {
		isStarting = starting;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public String getGroupTask() {
		return groupTask;
	}

	public void setGroupTask(String groupTask) {
		this.groupTask = groupTask;
	}

	public String getJobActions() {
		return jobActions;
	}

	public void setJobActions(String jobActions) {
		this.jobActions = jobActions;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}

	public boolean isQcNeeded() {
		return isQcNeeded;
	}

	public void setQcNeeded(boolean qcNeeded) {
		isQcNeeded = qcNeeded;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getUuidJob() {
		return uuidJob;
	}

	public void setUuidJob(String uuidJob) {
		this.uuidJob = uuidJob;
	}

	public String getJobLimit() {
		return jobLimit;
	}

	public void setJobLimit(String jobLimit) {
		this.jobLimit = jobLimit;
	}

	public String getTutorial() {
		return tutorial;
	}

	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}

	public String getMogawersCode() {
		return mogawersCode;
	}

	public void setMogawersCode(String mogawersCode) {
		this.mogawersCode = mogawersCode;
	}

	public String getIsScreening() {
		return isScreening;
	}

	public void setIsScreening(String isScreening) {
		this.isScreening = isScreening;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatusTask() {
		return statusTask;
	}

	public void setStatusTask(String statusTask) {
		this.statusTask = statusTask;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean pinned) {
		isPinned = pinned;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean published) {
		isPublished = published;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getIdMogawers() {
		return idMogawers;
	}

	public void setIdMogawers(int idMogawers) {
		this.idMogawers = idMogawers;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUuidJobType() {
		return uuidJobType;
	}

	public void setUuidJobType(String uuidJobType) {
		this.uuidJobType = uuidJobType;
	}

	public boolean isDirect() {
		return isDirect;
	}

	public void setDirect(boolean direct) {
		isDirect = direct;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
}