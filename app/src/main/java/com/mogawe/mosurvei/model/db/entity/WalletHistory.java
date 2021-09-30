package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WalletHistory implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName(value="idMogawers", alternate = {"id_mogawers"})
    @Expose
    private String id_mogawers;
    @SerializedName(value="transactionType", alternate = {"transaction_type"})
    @Expose
    private String transaction_type;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("description")
    @Expose
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_mogawers() {
        return id_mogawers;
    }

    public void setId_mogawers(String id_mogawers) {
        this.id_mogawers = id_mogawers;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
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
