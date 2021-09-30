package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategorySupplierProduct implements Serializable {

    @SerializedName("uuid")
    @Expose
    private String uuid;

    @SerializedName("name")
    @Expose
    private String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String storeName) {
        this.name = name;
    }

}
