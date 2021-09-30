package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CatalogImage implements Serializable {


    @SerializedName("uuid")
    private String uuid;

    @SerializedName("uuidProduct")
    private String uuidProduct;

    @SerializedName("value")
    private String value;

    @SerializedName("mainPicture")
    private boolean mainPicture;

    @SerializedName("sequence")
    private int sequence;

    public CatalogImage() {
    }


    public CatalogImage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUuidProduct() {
        return uuidProduct;
    }

    public void setUuidProduct(String uuidProduct) {
        this.uuidProduct = uuidProduct;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(boolean mainPicture) {
        this.mainPicture = mainPicture;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}