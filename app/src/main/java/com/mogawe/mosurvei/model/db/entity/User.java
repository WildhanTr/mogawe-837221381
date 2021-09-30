package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 4/4/17.
 */
@Entity
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idPK;

    @SerializedName(value = "id", alternate = {"id_mogawers"})
    @Expose
    private Integer id_mogawers;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName(value = "code", alternate = {"mogawers_code"})
    @Expose
    private String code;
    @SerializedName(value = "fullName", alternate = {"full_name"})
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName(value = "idDevice", alternate = {"id_device"})
    @Expose
    private String idDevice;
    @SerializedName(value = "profilePicture", alternate = {"profile_picture"})
    @Expose
    private String profilePicture;
    @SerializedName("gender")
    @Expose
    private String gender = "";
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("refCode")
    @Expose
    private String refCode;
    @SerializedName("edu")
    @Expose
    private String edu;
    @SerializedName(value = "socmedId", alternate = {"socmed_id"})
    @Expose
    private String socmedId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName(value = "isPhoneActive", alternate = {"is_phone_active"})
    @Expose
    private Boolean isPhoneActive;
    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName(value = "pendingPayment", alternate = {"pending_payment"})
    @Expose
    private Integer pending_payment;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName(value = "hasPassword", alternate = {"has_password"})
    @Expose
    private Boolean hasPassword;
    @SerializedName(value = "completedJob", alternate = {"completed_job", "gawean_selesai"})
    @Expose
    private Integer completedJob = 0;
    @SerializedName(value = "isTermsAgreed", alternate = {"is_terms_agreed"})
    @Expose
    private Boolean isTermsAgreed;
    @SerializedName("balance_latest_updated")
    @Expose
    private Long balanceLatestUpdated;

    @SerializedName("mogawersCode")
    @Expose
    private String mogawersCode;

    @SerializedName("status_timeline")
    @Expose
    private String status_timeline;

    private String password;
    private String activationCode;
    private Boolean loggedIn;
    private String storeName;
    private String storeAddress;
    private Double storeLat;
    private Double storeLng;
    private String provinceId;
    private String cityId;
    private String shipmentProvinceName;
    private String shipmentCityId;
    private String shipmentCityName;

    public User() {
    }

    @Ignore
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Ignore
    public User(String email, String fullName, String password, String refCode) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.refCode = refCode;
    }

    @Ignore
    public User(String email, String fullName, String refCode) {
        this.email = email;
        this.fullName = fullName;
        this.refCode = refCode;
    }

    public int getIdPK() {
        return idPK;
    }

    public void setIdPK(int idPK) {
        this.idPK = idPK;
    }

    public Integer getId_mogawers() {
        return id_mogawers;
    }

    public void setId_mogawers(Integer id_mogawers) {
        this.id_mogawers = id_mogawers;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getSocmedId() {
        return socmedId;
    }

    public void setSocmedId(String socmedId) {
        this.socmedId = socmedId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getPhoneActive() {
        return isPhoneActive;
    }

    public void setPhoneActive(Boolean phoneActive) {
        isPhoneActive = phoneActive;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Boolean getHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(Boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public Integer getCompletedJob() {
        return completedJob;
    }

    public void setCompletedJob(Integer completedJob) {
        this.completedJob = completedJob;
    }

    public Integer getPending_payment() {
        return pending_payment;
    }

    public void setPending_payment(Integer pending_payment) {
        this.pending_payment = pending_payment;
    }

    public Boolean getTermsAgreed() {
        return isTermsAgreed;
    }

    public void setTermsAgreed(Boolean termsAgreed) {
        isTermsAgreed = termsAgreed;
    }

    public Long getBalanceLatestUpdated() {
        return balanceLatestUpdated;
    }

    public void setBalanceLatestUpdated(Long balanceLatestUpdated) {
        this.balanceLatestUpdated = balanceLatestUpdated;
    }

    public String getMogawersCode() {
        return mogawersCode;
    }

    public void setMogawersCode(String mogawersCode) {
        this.mogawersCode = mogawersCode;
    }

    public String getStatus_timeline() {
        return status_timeline;
    }

    public void setStatus_timeline(String status_timeline) {
        this.status_timeline = status_timeline;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Double getStoreLat() {
        return storeLat;
    }

    public void setStoreLat(Double storeLat) {
        this.storeLat = storeLat;
    }

    public Double getStoreLng() {
        return storeLng;
    }

    public void setStoreLng(Double storeLng) {
        this.storeLng = storeLng;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getShipmentProvinceName() {
        return shipmentProvinceName;
    }

    public void setShipmentProvinceName(String shipmentProvinceName) {
        this.shipmentProvinceName = shipmentProvinceName;
    }

    public String getShipmentCityId() {
        return shipmentCityId;
    }

    public void setShipmentCityId(String shipmentCityId) {
        this.shipmentCityId = shipmentCityId;
    }

    public String getShipmentCityName() {
        return shipmentCityName;
    }

    public void setShipmentCityName(String shipmentCityName) {
        this.shipmentCityName = shipmentCityName;
    }
}
