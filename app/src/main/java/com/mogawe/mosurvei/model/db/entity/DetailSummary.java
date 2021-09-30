package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailSummary implements Serializable {

    @SerializedName("soldTotal")
    private Integer soldTotal;

    @SerializedName("soldByYou")
    private Integer soldByYou;

    public Integer getSoldTotal() {
        return soldTotal;
    }

    public void setSoldTotal(Integer soldTotal) {
        this.soldTotal = soldTotal;
    }

    public Integer getSoldByYou() {
        return soldByYou;
    }

    public void setSoldByYou(Integer soldByYou) {
        this.soldByYou = soldByYou;
    }
}
