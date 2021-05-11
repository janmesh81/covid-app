package com.covid.dashboard.covidapp.model;

/**
 * This is the object representing the response for containment zone service.
 * **/
public class CovidContainmentZone {
    private String country;
    private String state;
    private String latitude;
    private String longitude;
    public CovidContainmentZone(){}
    public CovidContainmentZone(String country, String state, String latitude, String longitude){
        this.country = country;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
