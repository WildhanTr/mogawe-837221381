package com.mogawe.mosurvei.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mobiletech on 5/21/17.
 */

public class MapPlace implements Serializable {

    private String id;
    private String name;
    private String desc;
    private List<Double> latLng;
    private List<List<Double>> areas;
    private Boolean isArea = false;
    private int icon = 0;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Double> getLatLng() {
        return latLng;
    }

    public void setLatLng(List<Double> latLng) {
        this.latLng = latLng;
    }

    public List<List<Double>> getAreas() {
        return areas;
    }

    public void setAreas(List<List<Double>> areas) {
        this.areas = areas;
    }

    public Boolean hasArea() {
        return isArea;
    }

    public void setArea(Boolean area) {
        isArea = area;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
