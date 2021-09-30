package com.mogawe.mosurvei.model.bean.shipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShippingCostDetail implements Serializable {

    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("etd")
    @Expose
    private String etd;
    @SerializedName("note")
    @Expose
    private String note;



    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
