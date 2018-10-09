package com.theQuake.quakeInfo;

public class Country {
    private String country;
    private Double latitude;
    private Double longitude;
    private String name;

    public Country(String country, Double latitude, Double longitude, String name){
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
