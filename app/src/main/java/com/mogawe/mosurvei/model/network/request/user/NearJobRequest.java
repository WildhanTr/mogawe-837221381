package com.mogawe.mosurvei.model.network.request.user;

public class NearJobRequest {
    private long id_mogawers;
    private double lat;
    private double lng;

    public long getId_mogawers() {
        return id_mogawers;
    }

    public void setId_mogawers(long id_mogawers) {
        this.id_mogawers = id_mogawers;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
