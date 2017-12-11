package com.c0711561.mad3125.finalproject.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by macstudent on 2017-12-08.
 */
@Entity
public class Problem {

    public enum statusOptions { WAITING, STARTED, DONE, CANCELED };

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private String location;

    @ColumnInfo
    private double latitude;

    @ColumnInfo
    private double longitude;

    @ColumnInfo
    private String category;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @ColumnInfo
    private Date happenedOn;

    @ColumnInfo
    private String solverComments;

    @ColumnInfo
    private String status;

    public Problem() {}

    @Ignore
    public Problem(String title, String description, String location, double latitude, double longitude, String category, byte[] image, Date happenedOn) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.image = image;
        this.happenedOn = happenedOn;
        this.status = statusOptions.WAITING.name();
    }

    @Ignore
    public Problem(String title, String description, String location, double latitude, double longitude, String category, byte[] image, Date happenedOn, String solverComments) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.image = image;
        this.happenedOn = happenedOn;
        this.solverComments = solverComments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getHappenedOn() {
        return happenedOn;
    }

    public void setHappenedOn(Date happenedOn) {
        this.happenedOn = happenedOn;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSolverComments() {
        return solverComments;
    }

    public void setSolverComments(String solverComments) {
        this.solverComments = solverComments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", category='" + category + '\'' +
                ", happenedOn=" + happenedOn +
                ", solverComments='" + solverComments + '\'' +
                '}';
    }
}
