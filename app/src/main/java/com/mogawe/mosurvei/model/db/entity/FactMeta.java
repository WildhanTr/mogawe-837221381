package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FactMeta implements Serializable {
    @SerializedName("idMetaDisplay")
    @Expose
    private String id_meta_display;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    private String input = "";

    @Ignore
    String uuidAdapter;

    public String getId_meta_display() {
        return id_meta_display;
    }

    public void setId_meta_display(String id_meta_display) {
        this.id_meta_display = id_meta_display;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getUuidAdapter() {
        return uuidAdapter;
    }

    public void setUuidAdapter(String uuidAdapter) {
        this.uuidAdapter = uuidAdapter;
    }
}
