package com.example.macstudent.googlemapsexample;

/**
 * Created by macstudent on 2017-12-05.
 */

public class Address {

    private String name;
    private double latitude;
    private double longitude;

    public Address(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
