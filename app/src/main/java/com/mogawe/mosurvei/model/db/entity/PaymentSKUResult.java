package com.mogawe.mosurvei.model.db.entity;

public class PaymentSKUResult {

    private int image;
    private String title;

    public PaymentSKUResult (int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}

