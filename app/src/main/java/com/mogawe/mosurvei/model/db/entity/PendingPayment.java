package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class PendingPayment {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName(value="idMogawers", alternate = {"id_mogawers"})
    @Expose
    private Integer id_mogawers;
    @SerializedName(value="transactionType", alternate = {"transaction_type"})
    @Expose
    private String transaction_type;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName(value="createdDate", alternate = {"created date"})
    @Expose
    private Long timestamp;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_mogawers() {
        return id_mogawers;
    }

    public void setId_mogawers(Integer id_mogawers) {
        this.id_mogawers = id_mogawers;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
