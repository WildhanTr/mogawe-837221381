package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

@Entity
public class DealsTransactions implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("refNo")
    @Expose
    private String refNo;
    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("updatedDate")
    @Expose
    private Long updatedDate;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("paymentMethod")
    @Expose
    private String paymentMethod;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("needApproval")
    @Expose
    private Boolean needApproval;

    @SerializedName("idMogawers")
    @Expose
    private Integer idMogawers;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sentDate")
    @Expose
    private Long sentDate;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("howTo")
    @Expose
    private String howTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getNeedApproval() {
        return needApproval;
    }

    public void setNeedApproval(Boolean needApproval) {
        this.needApproval = needApproval;
    }

    public Integer getIdMogawers() {
        return idMogawers;
    }

    public void setIdMogawers(Integer idMogawers) {
        this.idMogawers = idMogawers;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSentDate() {
        return sentDate;
    }

    public void setSentDate(Long sentDate) {
        this.sentDate = sentDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHowTo() {
        return howTo;
    }

    public void setHowTo(String howTo) {
        this.howTo = howTo;
    }
}
