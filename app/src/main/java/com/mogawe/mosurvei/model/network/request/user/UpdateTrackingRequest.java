package com.mogawe.mosurvei.model.network.request.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 5/1/16.
 */
public class UpdateTrackingRequest implements Serializable {


    private Double latitude;
    private Double longitude;
    private Integer versionNumber;
    private String brand;
    private String device;
    private String model;
    private String idDevice;
    private String product;
    private String sdk;
    private String release;
    private String incremental;
    private String percentAvailableRam;
    private String availableRam;
    private String operatorName;

    public UpdateTrackingRequest() {
    }

    public UpdateTrackingRequest(Double latitude, Double longitude, Integer versionNumber, String brand, String device, String model, String idDevice, String product, String sdk, String release, String incremental, String percentAvailableRam, String availableRam, String operatorName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.versionNumber = versionNumber;
        this.brand = brand;
        this.device = device;
        this.model = model;
        this.idDevice = idDevice;
        this.product = product;
        this.sdk = sdk;
        this.release = release;
        this.incremental = incremental;
        this.percentAvailableRam = percentAvailableRam;
        this.availableRam = availableRam;
        this.operatorName = operatorName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getIncremental() {
        return incremental;
    }

    public void setIncremental(String incremental) {
        this.incremental = incremental;
    }

    public String getPercentAvailableRam() {
        return percentAvailableRam;
    }

    public void setPercentAvailableRam(String percentAvailableRam) {
        this.percentAvailableRam = percentAvailableRam;
    }

    public String getAvailableRam() {
        return availableRam;
    }

    public void setAvailableRam(String availableRam) {
        this.availableRam = availableRam;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
