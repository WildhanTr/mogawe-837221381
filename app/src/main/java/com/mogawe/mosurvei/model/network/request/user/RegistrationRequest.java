package com.mogawe.mosurvei.model.network.request.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 5/1/16.
 */
public class RegistrationRequest implements Serializable {

    private String email;
    private String fullName;
    private String password;
    private String refCode;

    private String storeName;
    private String storeAddress;
    private Double storeLat;
    private Double storeLng;
    private String shipmentProvinceId;
    private String shipmentProvinceName;
    private String shipmentCityId;
    private String shipmentCityName;

    public RegistrationRequest(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public RegistrationRequest(String email, String fullName, String password, String refCode) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.refCode = refCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Double getStoreLat() {
        return storeLat;
    }

    public void setStoreLat(Double storeLat) {
        this.storeLat = storeLat;
    }

    public Double getStoreLng() {
        return storeLng;
    }

    public void setStoreLng(Double storeLng) {
        this.storeLng = storeLng;
    }

    public String getShipmentProvinceId() {
        return shipmentProvinceId;
    }

    public void setShipmentProvinceId(String shipmentProvinceId) {
        this.shipmentProvinceId = shipmentProvinceId;
    }

    public String getShipmentProvinceName() {
        return shipmentProvinceName;
    }

    public void setShipmentProvinceName(String shipmentProvinceName) {
        this.shipmentProvinceName = shipmentProvinceName;
    }

    public String getShipmentCityId() {
        return shipmentCityId;
    }

    public void setShipmentCityId(String shipmentCityId) {
        this.shipmentCityId = shipmentCityId;
    }

    public String getShipmentCityName() {
        return shipmentCityName;
    }

    public void setShipmentCityName(String shipmentCityName) {
        this.shipmentCityName = shipmentCityName;
    }
}
