package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.converter.JobActivitiesConverter;

@Entity
public class Catalog implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("uuidSupplier")
    private String uuidSupplier;

    @SerializedName("isDangerous")
    private boolean isDangerous;

    @SerializedName("isPublished")
    private boolean isPublished;

    @SerializedName("length")
    private double length;

    @SerializedName("description")
    private String description;

    @SerializedName("weight")
    private double weight;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("condition")
    private String condition;

    @SerializedName("uuidJob")
    private String uuidJob;

    @SerializedName("price")
    private int price;

    @SerializedName("youtubeUrl")
    private String youtubeUrl;

    @SerializedName("commissions")
    private List<CommissionsItem> commissions;

    @SerializedName("images")
    private List<CatalogImage> images;

    @SerializedName("uuidCategory")
    private String uuidCategory;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("width")
    private double width;

    @SerializedName("stock")
    private boolean stock;

    @SerializedName("brand")
    private String brand;

    @SerializedName("height")
    private double height;

    @SerializedName("commission")
    private double commission;

	@SerializedName("isFavorite")
	private boolean isFavorite;

	@SerializedName("minimumOrder")
	private Integer minimumOrder;

	@SerializedName("isShippingTakeaway")
    private boolean isShippingTakeaway;

	@SerializedName("isShippingOwncourier")
    private boolean isShippingOwncourier;

	@SerializedName("isShippingExpedition")
    private boolean isShippingExpedition;

	@SerializedName("shippingExpeditionServices")
    private String shippingExpeditionServices;

	@SerializedName("isFreeOngkir")
    private boolean isFreeOngkir;

    public int getId() {
		return id;
	}

    public void setId(int id) {
        this.id = id;
    }

    public String getUuidSupplier() {
        return uuidSupplier;
    }

    public void setUuidSupplier(String uuidSupplier) {
        this.uuidSupplier = uuidSupplier;
    }

    public boolean isDangerous() {
        return isDangerous;
    }

    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    public boolean isShippingTakeaway() { return isShippingTakeaway; }

    public void setShippingTakeaway(boolean shippingTakeaway) { isShippingTakeaway = shippingTakeaway; }

    public boolean isShippingOwncourier() { return isShippingOwncourier; }

    public void setShippingOwncourier(boolean shippingOwncourier) { isShippingOwncourier = shippingOwncourier; }

    public boolean isShippingExpedition() { return isShippingExpedition; }

    public void setShippingExpedition(boolean shippingExpedition) { isShippingExpedition = shippingExpedition; }

    public boolean isFreeOngkir() { return  isFreeOngkir; }

    public void setFreeOngkir(boolean freeOngkir) { isFreeOngkir = freeOngkir; }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShippingExpeditionServices() { return  shippingExpeditionServices; }

    public void setShippingExpeditionServices(String shippingExpeditionServices) { this.shippingExpeditionServices = shippingExpeditionServices; }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getUuidJob() {
        return uuidJob;
    }

    public void setUuidJob(String uuidJob) {
        this.uuidJob = uuidJob;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    @TypeConverters(CommissionsItem.class)
    public List<CommissionsItem> getCommissions() {
        return commissions;
    }

    public void setCommissions(List<CommissionsItem> commissions) {
        this.commissions = commissions;
    }

    public String getUuidCategory() {
        return uuidCategory;
    }

    public void setUuidCategory(String uuidCategory) {
        this.uuidCategory = uuidCategory;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @TypeConverters(CatalogImage.class)
    public List<CatalogImage> getImages() {
        return images;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
	public void setImages(List<CatalogImage> images) {
		this.images = images;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean favorite) {
		isFavorite = favorite;
	}

	public Integer getMinimumOrder() {
		return minimumOrder;
	}

	public void setMinimumOrder(Integer minimumOrder) {
		this.minimumOrder = minimumOrder;
	}
}