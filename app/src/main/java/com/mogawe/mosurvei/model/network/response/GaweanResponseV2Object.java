package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Task;

import java.io.Serializable;
import java.util.List;

public class GaweanResponseV2Object extends GenericResponse implements Serializable {

    @SerializedName("completeBonusFee")
    @Expose
    private Double completeBonusFee;
    @SerializedName("completeBonusPercentage")
    @Expose
    private Double completeBonusPercentage;
    @SerializedName("distanceBonusFee")
    @Expose
    private Double distanceBonusFee;
    @SerializedName("distanceBonus")
    @Expose
    private Double distanceBonus;
    @SerializedName("distanceActual")
    @Expose
    private Double distanceActual;
    @SerializedName("actualFee")
    @Expose
    private Double actualFee;
    @SerializedName("polylines")
    @Expose
    private String polylines;
    @SerializedName("totalFee")
    @Expose
    private Double totalFee;

    @SerializedName("tasks")
    @Expose
    private List<Task> tasks;

    @SerializedName("expiredDate")
    @Expose
    private Long expiredDate;

    public Double getCompleteBonusFee() {
        return completeBonusFee;
    }

    public void setCompleteBonusFee(Double completeBonusFee) {
        this.completeBonusFee = completeBonusFee;
    }

    public Double getCompleteBonusPercentage() {
        return completeBonusPercentage;
    }

    public void setCompleteBonusPercentage(Double completeBonusPercentage) {
        this.completeBonusPercentage = completeBonusPercentage;
    }

    public Double getDistanceBonusFee() {
        return distanceBonusFee;
    }

    public void setDistanceBonusFee(Double distanceBonusFee) {
        this.distanceBonusFee = distanceBonusFee;
    }

    public Double getDistanceBonus() {
        return distanceBonus;
    }

    public void setDistanceBonus(Double distanceBonus) {
        this.distanceBonus = distanceBonus;
    }

    public Double getDistanceActual() {
        return distanceActual;
    }

    public void setDistanceActual(Double distanceActual) {
        this.distanceActual = distanceActual;
    }

    public Double getActualFee() {
        return actualFee;
    }

    public void setActualFee(Double actualFee) {
        this.actualFee = actualFee;
    }

    public String getPolylines() {
        return polylines;
    }

    public void setPolylines(String polylines) {
        this.polylines = polylines;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Long getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Long expiredDate) {
        this.expiredDate = expiredDate;
    }
}
