package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Notification implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName(value="title")
    @Expose
    private String title;
    @SerializedName(value="desc")
    @Expose
    private String desc;
    @SerializedName(value="timeStamp")
    @Expose
    private String timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
