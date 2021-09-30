package com.mogawe.mosurvei.model.db.entity;


import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import bolts.Bolts;

@Entity
public class SupplierProduct implements Serializable {

    @SerializedName("uuid")
    @Expose
    private String uuid;

    @SerializedName("uuidCategory")
    @Expose
    private String uuidCategory;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("brand")
    @Expose
    private String brand;

    @SerializedName("isDangerous")
    @Expose
    private Boolean isDangerous;

    //pengiriman
    @SerializedName("isShippingTakeaway")
    @Expose
    private Boolean isShippingTakeaway;

    @SerializedName("isShippingOwncourier")
    private Boolean isShippingOwncourier;

    @SerializedName("isShippingExpedition")
    private Boolean isShippingExpedition;

    @SerializedName("shippingExpeditionServices")
    private String shippingExpeditionServices;

    @SerializedName("isFreeOngkir")
    private Boolean isFreeOngkir;
    //pengiriman

    @SerializedName("weight")
    @Expose
    private String weight;

    @SerializedName("width")
    @Expose
    private String width;

    @SerializedName("height")
    @Expose
    private String height;

    @SerializedName("length")
    @Expose
    private String length;

    @SerializedName("condition")
    @Expose
    private String condition;

    @SerializedName("price")
    @Expose
    private Number price;

    @SerializedName("commission")
    @Expose
    private Number commission;

    @SerializedName("stock")
    @Expose
    private Boolean stock;

    @SerializedName("isPublished")
    @Expose
    private Number isPublished;

    @SerializedName("youtubeUrl")
    @Expose
    private String youtubeUrl;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getuidCategory() {
        return uuidCategory;
    }

    public void setUuidCategory(String uuidCategory) {
        this.uuidCategory = uuidCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public Boolean getIsDangerous() { return isDangerous; }

    public void setIsDangerous(Boolean isDangerous) { this.isDangerous = isDangerous; }

    public Boolean getIsShippingTakeaway() { return isShippingTakeaway; }

    public void setIsShippingTakeaway(Boolean isShippingTakeaway) { this.isShippingTakeaway = isShippingTakeaway; }

    public Boolean getIsShippingOwncourier() { return  isShippingOwncourier; }

    public void setIsShippingOwncourier(Boolean isShippingOwncourier) { this.isShippingOwncourier = isShippingOwncourier; }

    public Boolean getIsShippingExpedition() { return isShippingExpedition; }

    public void setIsShippingExpedition(Boolean isShippingExpedition) { this.isShippingExpedition = isShippingExpedition; }

    public String getShippingExpeditionServices() { return shippingExpeditionServices; }

    public void setShippingExpeditionServices(String shippingExpeditionServices) { this.shippingExpeditionServices = shippingExpeditionServices; }

    public String getWeight() { return weight; }

    public void setWeight(String weight) { this.weight = weight; }

    public String getWidth() { return width; }

    public void setWidth(String width) { this.width = width; }

    public String getHeight() { return height; }

    public void setHeight(String height) { this.height = height; }

    public String getLength() { return length; }

    public void setLength(String length) { this.length = length; }

    public String getCondition() { return condition; }

    public void setCondition(String condition) { this.condition = condition; }

    public Number getPrice() { return price; }

    public void setPrice(Number price) { this.price = price; }

    public Number getCommission() { return commission; }

    public void setCommission(Number commission) { this.commission = commission; }

    public Boolean getStock() { return stock; }

    public void setStock(Boolean stock) { this.stock = stock; }

    public Number getIsPublished() { return isPublished; }

    public void setIsPublished(Number isPublished) { this.isPublished = isPublished; }

    public String getYoutubeUrl() { return youtubeUrl; }

    public void setYoutubeUrl(String youtubeUrl) { this.youtubeUrl = youtubeUrl; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
