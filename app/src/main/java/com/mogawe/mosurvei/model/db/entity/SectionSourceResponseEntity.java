package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.converter.SectionListConverter;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;

import java.io.Serializable;
import java.util.List;

@Entity
public class SectionSourceResponseEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("uuidSession")
    @Expose
    private String uuidSession;
    @SerializedName("object")
    @Expose
    private List<Section> sections;
    @Ignore
    private SectionSourceResponse.RequestKey requestKey;
    @Ignore
    private Result result;
    @Ignore
    private Result resultImages;
    @Ignore
    private Result resultVideos;
    private Integer indexStart;
    private Integer indexCount;

    private Integer imageStart;
    private Integer imageCount;

    private Integer videoStart;
    private Integer videoCount;

    private Integer videoProgressPercentage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @TypeConverters(SectionListConverter.class)
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public SectionSourceResponse.RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(SectionSourceResponse.RequestKey requestKey) {
        this.requestKey = requestKey;
    }

    public String getUuidSession() {
        return uuidSession;
    }

    public void setUuidSession(String uuidSession) {
        this.uuidSession = uuidSession;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResultImages() {
        return resultImages;
    }

    public void setResultImages(Result resultImages) {
        this.resultImages = resultImages;
    }

    public Integer getIndexStart() {
        return indexStart;
    }

    public void setIndexStart(Integer indexStart) {
        this.indexStart = indexStart;
    }

    public Integer getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(Integer indexCount) {
        this.indexCount = indexCount;
    }

    public Integer getImageStart() {
        return imageStart;
    }

    public void setImageStart(Integer imageStart) {
        this.imageStart = imageStart;
    }

    public Integer getImageCount() {
        return imageCount;
    }

    public void setImageCount(Integer imageCount) {
        this.imageCount = imageCount;
    }

    public Result getResultVideos() {
        return resultVideos;
    }

    public void setResultVideos(Result resultVideos) {
        this.resultVideos = resultVideos;
    }

    public Integer getVideoStart() {
        return videoStart;
    }

    public void setVideoStart(Integer videoStart) {
        this.videoStart = videoStart;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    public Integer getVideoProgressPercentage() {
        return videoProgressPercentage;
    }

    public void setVideoProgressPercentage(Integer videoProgressPercentage) {
        this.videoProgressPercentage = videoProgressPercentage;
    }

    public enum RequestKey {
        RESULT_UPLOADED,
        UPLOAD_FAILED,
        GET_HISTORY_RESULT,

        RESULT_CREATED,
        RESULT_FACT_UPLOAD_ON_PROGRESS,
        RESULT_FACT_UPLOAD_DONE,
        RESULT_FACT_IMAGES_UPLOAD_ON_PROGRESS,
        RESULT_FACT_IMAGES_UPLOAD_DONE,
        RESULT_FACT_VIDEOS_UPLOAD_ON_UPDATE_PERCENTAGE,
        RESULT_FACT_VIDEOS_UPLOAD_ON_PROGRESS,
        RESULT_FACT_VIDEOS_UPLOAD_DONE
    }
}
