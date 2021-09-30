package com.mogawe.mosurvei.model.network.request.user;

import java.io.Serializable;

public class ReportErrorRequest implements Serializable {

    private int versionNumber;
    private String error;
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
    private String activityName;
    private String signalStrength;
    private String battery;
    private String availableStorage;

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(String signalStrength) {
        this.signalStrength = signalStrength;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getAvailableStorage() {
        return availableStorage;
    }

    public void setAvailableStorage(String availableStorage) {
        this.availableStorage = availableStorage;
    }
}
