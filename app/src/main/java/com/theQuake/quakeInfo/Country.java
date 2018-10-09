package com.theQuake.quakeInfo;

public class Country {
    private String acronym;
    private long latitude;
    private long longitude;
    private String name;

    public Country(String acronym, long latitude, long longitude, String name){
        this.acronym = acronym;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
