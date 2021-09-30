package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SupplierDashboardCount implements Serializable {

    @SerializedName("products")
    @Expose
    private Integer products;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("sold")
    @Expose
    private Integer sold;
    @SerializedName("canceled")
    @Expose
    private Integer canceled;

    public Integer getProducts() {
        return products;
    }

    public void setProducts(Integer products) {
        this.products = products;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
        this.canceled = canceled;
    }
}
