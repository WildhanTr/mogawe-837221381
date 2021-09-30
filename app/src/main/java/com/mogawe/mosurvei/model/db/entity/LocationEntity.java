package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationEntity implements Serializable {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("uuidLocationCategory")
    @Expose
    private String uuidLocationCategory;
    @SerializedName("uuidLocationLevel")
    @Expose
    private String uuidLocationLevel;
    @SerializedName("uuidLocationType")
    @Expose
    private String uuidLocationType;
    @SerializedName("uuidParentLocation")
    @Expose
    private String uuidParentLocation;
    @SerializedName("locationTypeName")
    @Expose
    private String locationTypeName;
    @SerializedName("locationLevelName")
    @Expose
    private String locationLevelName;
    @SerializedName("locationCategoriesName")
    @Expose
    private String locationCategoriesName;
    @SerializedName("locationCategoriesSub")
    @Expose
    private String locationCategoriesSub;
    @SerializedName("latlngs")
    @Expose
    private Double latlngs;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("cities")
    @Expose
    private String cities;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("levelDescription")
    @Expose
    private String levelDescription;

    private Boolean isChecked;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidLocationCategory() {
        return uuidLocationCategory;
    }

    public void setUuidLocationCategory(String uuidLocationCategory) {
        this.uuidLocationCategory = uuidLocationCategory;
    }

    public String getUuidLocationLevel() {
        return uuidLocationLevel;
    }

    public void setUuidLocationLevel(String uuidLocationLevel) {
        this.uuidLocationLevel = uuidLocationLevel;
    }

    public String getUuidLocationType() {
        return uuidLocationType;
    }

    public void setUuidLocationType(String uuidLocationType) {
        this.uuidLocationType = uuidLocationType;
    }

    public String getUuidParentLocation() {
        return uuidParentLocation;
    }

    public void setUuidParentLocation(String uuidParentLocation) {
        this.uuidParentLocation = uuidParentLocation;
    }

    public String getLocationTypeName() {
        return locationTypeName;
    }

    public void setLocationTypeName(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }

    public String getLocationLevelName() {
        return locationLevelName;
    }

    public void setLocationLevelName(String locationLevelName) {
        this.locationLevelName = locationLevelName;
    }

    public String getLocationCategoriesName() {
        return locationCategoriesName;
    }

    public void setLocationCategoriesName(String locationCategoriesName) {
        this.locationCategoriesName = locationCategoriesName;
    }

    public String getLocationCategoriesSub() {
        return locationCategoriesSub;
    }

    public void setLocationCategoriesSub(String locationCategoriesSub) {
        this.locationCategoriesSub = locationCategoriesSub;
    }

    public Double getLatlngs() {
        return latlngs;
    }

    public void setLatlngs(Double latlngs) {
        this.latlngs = latlngs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public void setLevelDescription(String levelDescription) {
        this.levelDescription = levelDescription;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
