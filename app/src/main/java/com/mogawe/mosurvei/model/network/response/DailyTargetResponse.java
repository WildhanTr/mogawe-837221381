package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DailyTargetResponse implements Serializable {
    @SerializedName("targetRevenue")
    private Double targetRevenue;

    @SerializedName("todayRevenue")
    private Double todayRevenue;

    public Double getTargetRevenue() {
        return targetRevenue;
    }

    public Double getTodayRevenue() {
        return todayRevenue;
    }
}
