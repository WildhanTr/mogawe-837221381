package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.GenericBean;

import java.io.Serializable;

public class GenericResponse extends GenericBean implements Serializable {

    @SerializedName("returnValue")
    @Expose
    private String returnValue;
    @SerializedName("message")
    @Expose
    private String message;


    public GenericResponse() {
    }

    public GenericResponse(String returnValue, String message) {
        this.returnValue = returnValue;
        this.message = message;
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
        if (this.returnValue.equals("000"))
            return true;
        return false;
    }

    public Boolean isFailure() {
        if (this.returnValue.equals("001"))
            return true;
        return false;
    }

    public Boolean isInactive() {
        if (this.returnValue.equals("002"))
            return true;
        return false;
    }
}
