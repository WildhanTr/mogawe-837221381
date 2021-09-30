package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutResponse extends GenericResp {


    @SerializedName("object")
    @Expose
    private String object;

    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
}
