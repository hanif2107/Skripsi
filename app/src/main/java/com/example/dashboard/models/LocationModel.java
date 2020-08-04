package com.example.dashboard.models;

import com.google.gson.annotations.SerializedName;


public class LocationModel {
    @SerializedName("nama_lokasi")
    private String imageLocationName;
    @SerializedName("latitude")
    private double latutide;
    @SerializedName("longitude")
    private double longitude;

    public LocationModel(String imageLocationName, double latutide, double longitude) {
        this.imageLocationName = imageLocationName;
        this.latutide = latutide;
        this.longitude = longitude;
    }

    public LocationModel() {
    }

    public String getImageLocationName() {
        return imageLocationName;
    }

    public void setImageLocationName(String imageLocationName) {
        this.imageLocationName = imageLocationName;
    }

    public double getLatutide() {
        return latutide;
    }

    public void setLatutide(double latutide) {
        this.latutide = latutide;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
