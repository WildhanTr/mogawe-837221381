package com.mogawe.mosurvei.model.network.request;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 5/1/16.
 */
public class RegisterReq extends GenericReq implements Serializable {

    private String email;
    private String nama_lengkap;
    private String no_handphone;
    private String gender;
    private String birth_date;
    private String education;
    private String research_exp;
    private String biz_location;
    private String prefer_location;
    private String password;
    private String lat;
    private String lng;
    private String city;
    private String address;
    private String q1;
    private String q2;
    private String q3;
    private String q4;


    public RegisterReq() {
    }

    public RegisterReq(String email, String nama_lengkap, String no_handphone, String gender, String birth_date, String education, String research_exp, String biz_location, String prefer_location, String password, String lat, String lng, String city, String address, String q1, String q2, String q3, String q4) {
        this.email = email;
        this.nama_lengkap = nama_lengkap;
        this.no_handphone = no_handphone;
        this.gender = gender;
        this.birth_date = birth_date;
        this.education = education;
        this.research_exp = research_exp;
        this.biz_location = biz_location;
        this.prefer_location = prefer_location;
        this.password = password;
        this.lat = lat;
        this.lng = lng;
        this.city = city;
        this.address = address;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
    }

    public RegisterReq(String toString, String toString1, String toString2) {

    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNo_handphone() {
        return no_handphone;
    }

    public void setNo_handphone(String no_handphone) {
        this.no_handphone = no_handphone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getResearch_exp() {
        return research_exp;
    }

    public void setResearch_exp(String research_exp) {
        this.research_exp = research_exp;
    }

    public String getBiz_location() {
        return biz_location;
    }

    public void setBiz_location(String biz_location) {
        this.biz_location = biz_location;
    }

    public String getPrefer_location() {
        return prefer_location;
    }

    public void setPrefer_location(String prefer_location) {
        this.prefer_location = prefer_location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}