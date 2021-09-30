package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import clojure.lang.Obj;

public class Chart implements Serializable {

    @SerializedName(value="name")
    @Expose
    private String name;
    @SerializedName(value="value")
    @Expose
    private Object value;
    @SerializedName(value="value2")
    @Expose
    private Object value2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(TaskInformation value) {
        this.value = value;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(TaskInformation value2) {
        this.value2 = value2;
    }
}
