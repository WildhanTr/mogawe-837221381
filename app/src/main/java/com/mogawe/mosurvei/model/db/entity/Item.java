package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {
    @SerializedName("idItemDisplay")
    @Expose
    private String id_item_display;
    @SerializedName("uuid")
    @Expose
    private String id_item;
    @SerializedName("itemName")
    @Expose
    private String item_name;

    @SerializedName("uuidMeta")
    @Expose
    private String uuidMeta;
    @SerializedName("metaName")
    @Expose
    private String metaName;
    @SerializedName("metaCode")
    @Expose
    private String metaCode;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("pictureUrl")
    @Expose
    private String pictureUrl;
    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;
    @SerializedName("hintName")
    @Expose
    private String hintName;

    @SerializedName("uuidBrand")
    @Expose
    private String uuidBrand;
    @SerializedName("itemPicture")
    @Expose
    private String itemPicture;
    @SerializedName("itemColor")
    @Expose
    private String itemColor;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("width")
    @Expose
    private Double width;
    @SerializedName("height")
    @Expose
    private Double height;

    @Ignore
    Integer idItem;

    @Ignore
    String uuidAdapter;

    private String input = "";

    public String getUuidMeta() {
        return uuidMeta;
    }

    public void setUuidMeta(String uuidMeta) {
        this.uuidMeta = uuidMeta;
    }

    public String getMetaName() {
        return metaName;
    }

    public void setMetaName(String metaName) {
        this.metaName = metaName;
    }

    public String getMetaCode() {
        return metaCode;
    }

    public void setMetaCode(String metaCode) {
        this.metaCode = metaCode;
    }

    public String getId_item_display() {
        return id_item_display;
    }

    public void setId_item_display(String id_item_display) {
        this.id_item_display = id_item_display;
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getHintName() {
        return hintName;
    }

    public void setHintName(String hintName) {
        this.hintName = hintName;
    }

    public String getUuidBrand() {
        return uuidBrand;
    }

    public void setUuidBrand(String uuidBrand) {
        this.uuidBrand = uuidBrand;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getUuidAdapter() {
        return uuidAdapter;
    }

    public void setUuidAdapter(String uuidAdapter) {
        this.uuidAdapter = uuidAdapter;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
