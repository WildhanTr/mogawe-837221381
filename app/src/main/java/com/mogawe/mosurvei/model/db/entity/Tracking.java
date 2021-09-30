package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.converter.TimestampConverter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by glenrynaldi on 4/4/17.
 */
@Entity
public class Tracking implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("mogawers_code")
    @Expose
    private String code;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("timestamp")
    @Expose
    @TypeConverters(TimestampConverter.class)
    private Date timestamp;
    @Ignore
    @SerializedName("synced")
    @Expose
    private Boolean synced;
    @SerializedName("versionNumber")
    @Expose
    private Integer versionNumber;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("device")
    @Expose
    private String device;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("idDevice")
    @Expose
    private String idDevice;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("sdk")
    @Expose
    private String sdk;
    @SerializedName("release")
    @Expose
    private String release;
    @SerializedName("incremental")
    @Expose
    private String incremental;
    @SerializedName("percentAvailableRam")
    @Expose
    private String percentAvailableRam;
    @SerializedName("availableRam")
    @Expose
    private String availableRam;
    @SerializedName("operatorName")
    @Expose
    private String operatorName;

    public Tracking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean isSynced() {
        return synced;
    }

    public void setSynced(Boolean synced) {
        this.synced = synced;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Boolean getSynced() {
        return synced;
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
