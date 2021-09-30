package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.GenericBean;

import java.io.Serializable;

public class GenericResp extends GenericBean implements Serializable {

    @SerializedName("register_id")
    @Expose
    private String registerId = "0";
    @SerializedName("returnValue")
    @Expose
    private String returnValue;
    @SerializedName("message")
    @Expose
    private String message;


    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isSuccess() {
        if (this.returnValue.equals("000") || this.returnValue.equals("1")) {
            return true;
        }else{
            return false;
        }
    }
}
