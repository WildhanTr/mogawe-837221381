package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.io.Serializable;

public class HistoryResponse implements Serializable {
    private boolean startNewDate = false;

    @SerializedName("amount")
    private Double amount;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("description")
    private String description;

    @SerializedName("status")
    private String status;

    @SerializedName("category")
    private String category;

    public Double getAmount() {
        return amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public boolean isStartNewDate() {
        return startNewDate;
    }

    public void setStartNewDate(boolean startNewDate) {
        this.startNewDate = startNewDate;
    }
}
