package com.mogawe.mosurvei.model.bean.history;

import java.io.Serializable;

public class Payment implements Serializable {
    private String id_payment;
    private String imageUrl;
    private String description;

    public String getId_payment() {
        return id_payment;
    }

    public void setId_payment(String id_payment) {
        this.id_payment = id_payment;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
