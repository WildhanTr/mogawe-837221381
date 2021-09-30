package com.mogawe.mosurvei.model.network.request.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 5/1/16.
 */
public class UpdateDeviceIdRequest implements Serializable {


//    @SerializedName("id_device")
    private String idDevice;

    public UpdateDeviceIdRequest() {
    }

    public UpdateDeviceIdRequest(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }
}
