package com.mogawe.mosurvei.model.bean.shipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ShippingCost implements Serializable {

    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cost")
    @Expose
    private List<ShippingCostDetail> details;

    private String serviceName;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ShippingCostDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ShippingCostDetail> details) {
        this.details = details;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
