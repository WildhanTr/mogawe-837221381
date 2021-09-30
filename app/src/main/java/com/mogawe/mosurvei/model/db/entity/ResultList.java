package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.converter.ResultListConverter;

import java.io.Serializable;
import java.util.List;

@Entity
public class ResultList implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("result_list")
    private List<Result> result_list;

    private int idJob;
    private String uuidResult;

    private Double latitude;
    private Double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @TypeConverters(ResultListConverter.class)
    public List<Result> getResult_list() {
        return result_list;
    }

    public void setResult_list(List<Result> result_list) {
        this.result_list = result_list;
    }

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;
    }

    public String getUuidResult() {
        return uuidResult;
    }

    public void setUuidResult(String uuidResult) {
        this.uuidResult = uuidResult;
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
}
