package com.mogawe.mosurvei.model.bean;

import com.google.gson.Gson;

import java.io.Serializable;

import hugo.weaving.DebugLog;

@DebugLog
public class GenericBean implements Serializable {

    //@DebugLog
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
