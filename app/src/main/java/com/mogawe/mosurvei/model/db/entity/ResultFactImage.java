package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ResultFactImage implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("uuid")
    @Expose
    private String id_result_fact_image;
    @SerializedName("uuidResultFact")
    @Expose
    private String id_result_fact;
    @SerializedName("value")
    @Expose
    private String value;

    private List<File> files = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_result_fact_image() {
        return id_result_fact_image;
    }

    public void setId_result_fact_image(String id_result_fact_image) {
        this.id_result_fact_image = id_result_fact_image;
    }

    public String getId_result_fact() {
        return id_result_fact;
    }

    public void setId_result_fact(String id_result_fact) {
        this.id_result_fact = id_result_fact;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
