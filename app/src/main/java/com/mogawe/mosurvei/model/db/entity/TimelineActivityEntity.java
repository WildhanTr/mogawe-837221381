package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TimelineActivityEntity implements Serializable {

    @SerializedName(value="uuid")
    @Expose
    private String uuid;
    @SerializedName(value="uuidPostType")
    @Expose
    private String uuidPostType;
    @SerializedName(value="uuidMogawers")
    @Expose
    private String uuidMogawers;
    @SerializedName(value="fullName")
    @Expose
    private String fullName;
    @SerializedName(value="mogawersCode")
    @Expose
    private String mogawersCode;
    @SerializedName(value="phone")
    @Expose
    private String phone;
    @SerializedName(value="profilePicture")
    @Expose
    private String profilePicture;
    @SerializedName(value="content")
    @Expose
    private String content;
    @SerializedName(value="likesCount")
    @Expose
    private Integer likesCount;
    @SerializedName(value="isLiked")
    @Expose
    private Boolean isLiked;
    @SerializedName(value="isCommentable")
    @Expose
    private boolean isCommentable;
    @SerializedName(value="isShareable")
    @Expose
    private boolean isShareable;
    @SerializedName(value="isLikeable")
    @Expose
    private boolean isLikeable;
    @SerializedName(value="imageUrl")
    @Expose
    private String imageUrl = null;
    @SerializedName(value="isActive ")
    @Expose
    private boolean isActive;
    @SerializedName(value="relationshipStatus")
    @Expose
    private String relationshipStatus;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidPostType() {
        return uuidPostType;
    }

    public void setUuidPostType(String uuidPostType) {
        this.uuidPostType = uuidPostType;
    }

    public String getUuidMogawers() {
        return uuidMogawers;
    }

    public void setUuidMogawers(String uuidMogawers) {
        this.uuidMogawers = uuidMogawers;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMogawersCode() {
        return mogawersCode;
    }

    public void setMogawersCode(String mogawersCode) {
        this.mogawersCode = mogawersCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isCommentable() {
        return isCommentable;
    }

    public void setCommentable(boolean commentable) {
        isCommentable = commentable;
    }

    public boolean isShareable() {
        return isShareable;
    }

    public void setShareable(boolean shareable) {
        isShareable = shareable;
    }

    public boolean isLikeable() {
        return isLikeable;
    }

    public void setLikeable(boolean likeable) {
        isLikeable = likeable;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }
}
