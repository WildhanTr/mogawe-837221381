package com.mogawe.mosurvei.model.network.request;

public class PostStatusRequest {
    private String uuidPostType;
    private String uuidMogawers;
    private String content;
    private Boolean isCommentable;
    private Boolean isShareable;
    private Boolean isLikeable;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCommentable() {
        return isCommentable;
    }

    public void setCommentable(Boolean commentable) {
        isCommentable = commentable;
    }

    public Boolean getShareable() {
        return isShareable;
    }

    public void setShareable(Boolean shareable) {
        isShareable = shareable;
    }

    public Boolean getLikeable() {
        return isLikeable;
    }

    public void setLikeable(Boolean likeable) {
        isLikeable = likeable;
    }
}
