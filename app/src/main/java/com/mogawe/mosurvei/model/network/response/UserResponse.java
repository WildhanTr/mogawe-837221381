package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.TimelineActivityEntity;
import com.mogawe.mosurvei.model.db.entity.User;

import java.io.Serializable;
import java.util.List;

public class UserResponse extends GenericResponse implements Serializable {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("object")
    @Expose
    private User user;
    @SerializedName("type")
    @Expose
    private String type;
    private RequestKey requestKey;

    @SerializedName("rowCount")
    @Expose
    private Integer row_count;
    @SerializedName("pageCount")
    @Expose
    private Integer page_count;
    @SerializedName("offset")
    @Expose
    private Integer offset;

//    @SerializedName("certification")
//    @Expose
//    private MoGawersCertification certification;

    @SerializedName("postTimelines")
    @Expose
    private List<TimelineActivityEntity> postTimelines;

    private List<User> myDownline;

    public UserResponse(){

    }

    public UserResponse(String returnValue, String message, RequestKey requestKey) {
        super(returnValue, message);
        this.requestKey = requestKey;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(RequestKey requestKey) {
        this.requestKey = requestKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRow_count() {
        return row_count;
    }

    public void setRow_count(Integer row_count) {
        this.row_count = row_count;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<User> getMyDownline() {
        return myDownline;
    }

    public void setMyDownline(List<User> myDownline) {
        this.myDownline = myDownline;
    }

    public enum RequestKey {
        ACTIVATION,
        REGISTRATION,
        LOGIN,
        LOGIN_SOCMED,
        PHOTO_SOCMED,
        SOCMED_WITHOUT_PASSWORD,
        RESEND_EMAIL,
        RESEND_SMS,
        PROFILE,
        RESET_PASSWORD,
        UPDATE_PHONE,
        UPDATE_PROFILE,
        UPDATE_PHOTO_PROFILE,
        TOKEN_EXPIRED,
        REQUEST_SUCCESS,
        REQUEST_FAILED,
        RESPONSE_OPEN_API,
        TERMS_READ,
        TERMS_NOT_READ,

        CHECK_DOWNLINE,
        CHECK_DOWNLINE_FAILED,
        GET_DOWNLINE,

        ADD_DOWNLINE,
        ADD_DOWNLINE_FAILED,
        GET_LAST_STATUS_TIMELINE,

        JOB_REF_CODE,
        JOB_ATTACHMENT,
    }

    public List<TimelineActivityEntity> getPostTimelines() {
        return postTimelines;
    }

    public void setPostTimelines(List<TimelineActivityEntity> postTimelines) {
        this.postTimelines = postTimelines;
    }
}
