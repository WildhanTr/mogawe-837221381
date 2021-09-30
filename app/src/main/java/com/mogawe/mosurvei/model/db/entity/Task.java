package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Certificate;
import com.mogawe.mosurvei.model.bean.Task.TaskActivities;
import com.mogawe.mosurvei.model.db.converter.JobActivitiesConverter;
import com.mogawe.mosurvei.model.db.converter.JobCertificatesConverter;
import com.mogawe.mosurvei.model.db.converter.JobTimeConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName(value = "idTask", alternate = {"id_task", "uuid"})
    @Expose
    private String id_task;
    @SerializedName(value = "groupName", alternate = {"group_name"})
    @Expose
    private String group_name;
    @SerializedName(value = "idJob", alternate = {"id_job", "uuidJob"})
    @Expose
    private String id_job;
    @SerializedName(value = "idJobType", alternate = {"id_job_type", "uuidJobType"})
    @Expose
    private String id_job_type;
    @SerializedName(value = "latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName(value = "locationName", alternate = {"location_name"})
    @Expose
    private String location_name;
    @SerializedName("tutorial")
    @Expose
    private String tutorial;
    @SerializedName(value = "jobTypeName", alternate = {"job_type_name"})
    @Expose
    private String job_type_name;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("jobName")
    @Expose
    private String job_name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("brief")
    @Expose
    private String brief;
    @SerializedName(value = "howTo", alternate = {"how_to"})
    @Expose
    private String how_to;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName(value = "minimumLevel", alternate = {"minimum_level"})
    @Expose
    private String minimum_level;
    @SerializedName("limit")
    @Expose
    private Integer limits;
    @SerializedName("fee")
    @Expose
    private Integer fee;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("target")
    @Expose
    private Integer target;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName(value = "isScreening", alternate = {"is_screening"})
    @Expose
    private Boolean isScreening;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName(value = "statusTask", alternate = {"status_task"})
    @Expose
    private String status_task;
    @SerializedName(value = "statusNotes", alternate = {"status_notes"})
    @Expose
    private String status_notes;
    @SerializedName(value = "isHaveArea", alternate = {"is_have_area"})
    @Expose
    private boolean is_have_area;
    @SerializedName(value = "isPinned", alternate = {"is_pinned"})
    @Expose
    private boolean is_pinned;
    @SerializedName(value = "isQcNeeded", alternate = {"is_qc_needed"})
    @Expose
    private boolean is_qc_needed;
    @SerializedName(value = "isAutoApproved", alternate = {"is_auto_approved"})
    @Expose
    private boolean is_auto_approved;
    @SerializedName(value = "isDirect", alternate = {"is_direct"})
    @Expose
    private boolean is_direct;
    @SerializedName(value = "jobPicture", alternate = {"job_picture"})
    @Expose
    private String job_picture;
    @SerializedName(value = "jobBanner", alternate = {"job_banner"})
    @Expose
    private String job_banner;
    @SerializedName(value = "jobColor", alternate = {"job_color"})
    @Expose
    private String job_color;
    @SerializedName(value = "jobTextColor", alternate = {"job_text_color"})
    @Expose
    private String job_text_color;
    @SerializedName(value = "radius")
    @Expose
    private Integer radius;
    @SerializedName(value = "uuidLocationLevel")
    @Expose
    private String location_level;

    @SerializedName(value = "locationLevel")
    @Expose
    private String locationLevel;

    @SerializedName(value = "jobActions", alternate = {"job_actions"})
    @Expose
    private List<TaskActivities> job_activities;

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;

    @SerializedName("jobId")
    @Expose
    private String jobId;

    @SerializedName("createdDate")
    @Expose
    private Long startDate;

    @SerializedName("expiredDate")
    @Expose
    private Long endDate;

    @SerializedName(value = "locationAddress", alternate = {"location_address"})
    @Expose
    private String locationAdress;

    @SerializedName("jobTimes")
    @Expose
    private List<JobTimes> jobTimes;

    @SerializedName("certificates")
    private List<Certificate> certificates;

//    @SerializedName("notes")
//    @Expose
//    private String notes;

//    @SerializedName(value = "createdDate", alternate = {"created_date"})
//    @Expose
//    private int created_date;

//    @SerializedName(value="status")
//    @Expose
//    private String status;

    @SerializedName(value = "uuidQueue")
    @Expose
    private String uuidQueue;

    @SerializedName(value = "uuidProject")
    @Expose
    private String uuidProject;

    @SerializedName(value = "projectName")
    @Expose
    private String projectName;

    @SerializedName(value = "postMessage")
    @Expose
    private String postMessage;

    @SerializedName(value = "projectDescription")
    @Expose
    private String projectDescription;

    private boolean isAssigned;
    private boolean isFavorite;

    private Float distanceLocation;
    private String distance;

    private String distanceFromDirections;

    private String uuidResult;

    @Ignore
    private List<Task> taskList = new ArrayList<>();

    public String getUuidQueue() {
        return uuidQueue;
    }

    public void setUuidQueue(String uuidQueue) {
        this.uuidQueue = uuidQueue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getHow_to() {
        return how_to;
    }

    public void setHow_to(String how_to) {
        this.how_to = how_to;
    }

    public String getMinimum_level() {
        return minimum_level;
    }

    public void setMinimum_level(String minimum_level) {
        this.minimum_level = minimum_level;
    }

    public Integer getLimits() {
        return limits;
    }

    public void setLimits(Integer limits) {
        this.limits = limits;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId_job_type() {
        return id_job_type;
    }

    public void setId_job_type(String id_job_type) {
        this.id_job_type = id_job_type;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_job() {
        return id_job;
    }

    public void setId_job(String id_job) {
        this.id_job = id_job;
    }

    public String getJob_type_name() {
        return job_type_name;
    }

    public void setJob_type_name(String job_type_name) {
        this.job_type_name = job_type_name;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Boolean getScreening() {
        return isScreening;
    }

    public void setScreening(Boolean screening) {
        isScreening = screening;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public boolean isIs_qc_needed() {
        return is_qc_needed;
    }

    public void setIs_qc_needed(boolean is_qc_needed) {
        this.is_qc_needed = is_qc_needed;
    }

    public boolean isIs_auto_approved() {
        return is_auto_approved;
    }

    public void setIs_auto_approved(boolean is_auto_approved) {
        this.is_auto_approved = is_auto_approved;
    }

    public String getJob_picture() {
        return job_picture;
    }

    public void setJob_picture(String job_picture) {
        this.job_picture = job_picture;
    }

    public String getJob_banner() {
        return job_banner;
    }

    public void setJob_banner(String job_banner) {
        this.job_banner = job_banner;
    }

    public String getJob_color() {
        return job_color;
    }

    public void setJob_color(String job_color) {
        this.job_color = job_color;
    }

    public String getJob_text_color() {
        return job_text_color;
    }

    public void setJob_text_color(String job_text_color) {
        this.job_text_color = job_text_color;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus_task() {
        return status_task;
    }

    public void setStatus_task(String status_task) {
        this.status_task = status_task;
    }

    public String getStatus_notes() {
        return status_notes;
    }

    public void setStatus_notes(String status_notes) {
        this.status_notes = status_notes;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Float getDistanceLocation() {
        return distanceLocation;
    }

    public void setDistanceLocation(Float distanceLocation) {
        this.distanceLocation = distanceLocation;
    }

    public boolean isIs_have_area() {
        return is_have_area;
    }

    public void setIs_have_area(boolean is_have_area) {
        this.is_have_area = is_have_area;
    }

    @TypeConverters(JobActivitiesConverter.class)
    public List<TaskActivities> getJob_activities() {
        return job_activities;
    }

    public void setJob_activities(List<TaskActivities> job_activities) {
        this.job_activities = job_activities;
    }

    public String getLocation_level() {
        return location_level;
    }

    public void setLocation_level(String location_level) {
        this.location_level = location_level;
    }

    public String getLocationLevel() {
        return locationLevel;
    }

    public void setLocationLevel(String locationLevel) {
        this.locationLevel = locationLevel;
    }

    public boolean isIs_pinned() {
        return is_pinned;
    }

    public void setIs_pinned(boolean is_pinned) {
        this.is_pinned = is_pinned;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public boolean isIs_direct() {
        return is_direct;
    }

    public void setIs_direct(boolean is_direct) {
        this.is_direct = is_direct;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getLocationAdress() {
        return locationAdress;
    }

    public void setLocationAdress(String locationAdress) {
        this.locationAdress = locationAdress;
    }

    @TypeConverters(JobTimeConverter.class)
    public List<JobTimes> getJobTimes() {
        return jobTimes;
    }

    public void setJobTimes(List<JobTimes> jobTimes) {
        this.jobTimes = jobTimes;
    }

    public String getDistanceFromDirections() {
        return distanceFromDirections;
    }

    public void setDistanceFromDirections(String distanceFromDirections) {
        this.distanceFromDirections = distanceFromDirections;
    }

    public String getUuidResult() {
        return uuidResult;
    }

    public void setUuidResult(String uuidResult) {
        this.uuidResult = uuidResult;
    }

//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }

//    public int getCreated_date() {
//        return created_date;
//    }
//
//    public void setCreated_date(int created_date) {
//        this.created_date = created_date;
//    }


//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getUuidProject() {
        return uuidProject;
    }

    public void setUuidProject(String uuidProject) {
        this.uuidProject = uuidProject;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    //Sementara tambahan Glide
//    public String getJobPictureNextGawean() {
//        return job_picture;
//    }

    @TypeConverters(JobCertificatesConverter.class)
    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}
