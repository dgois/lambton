package com.example.macstudent.cricketplayerrank.model;

import android.util.Log;

import java.util.Date;

/**
 * Created by macstudent on 2017-12-01.
 */

public class Player {

    private int id;
    private String name;
    private String gender;
    private Date birthdate;
    private String category;
    private String country;
    private int numberOfTestMatch;
    private int numberOf1DayMatch;
    private int numberOfCatch;
    private int numberOfRuns;
    private int numberOfWicket;
    private int numberOfStuping;
    private int totalPoints;

    public Player(int id, String name, String gender, Date birthdate, String category, String country, int totalPoints) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.category = category;
        this.country = country;
        this.totalPoints = totalPoints;
    }

    public Player(String name, String gender, Date birthdate, String category, String country) {
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.category = category;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfTestMatch() {
        return numberOfTestMatch;
    }

    public void setNumberOfTestMatch(int numberOfTestMatch) {
        this.numberOfTestMatch = numberOfTestMatch;
    }

    public int getNumberOf1DayMatch() {
        return numberOf1DayMatch;
    }

    public void setNumberOf1DayMatch(int numberOf1DayMatch) {
        this.numberOf1DayMatch = numberOf1DayMatch;
    }

    public int getNumberOfCatch() {
        return numberOfCatch;
    }

    public void setNumberOfCatch(int numberOfCatch) {
        this.numberOfCatch = numberOfCatch;
    }

    public int getNumberOfRuns() {
        return numberOfRuns;
    }

    public void setNumberOfRuns(int numberOfRuns) {
        this.numberOfRuns = numberOfRuns;
    }

    public int getNumberOfWicket() {
        return numberOfWicket;
    }

    public void setNumberOfWicket(int numberOfWicket) {
        this.numberOfWicket = numberOfWicket;
    }

    public int getNumberOfStuping() {
        return numberOfStuping;
    }

    public void setNumberOfStuping(int numberOfStuping) {
        this.numberOfStuping = numberOfStuping;
    }

    public int calculateTotalPoints() {
        int total = (numberOfTestMatch * 5) + (numberOf1DayMatch * 2) + (numberOfCatch * 3) + (numberOfRuns) + (numberOfWicket * 5) + (numberOfStuping * 3);
        totalPoints = total;
        return total;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                ", numberOfTestMatch='" + numberOfTestMatch + '\'' +
                ", numberOf1DayMatch='" + numberOf1DayMatch + '\'' +
                ", numberOfCatch='" + numberOfCatch + '\'' +
                ", numberOfRuns='" + numberOfRuns + '\'' +
                ", numberOfWicket='" + numberOfWicket + '\'' +
                ", numberOfStuping='" + numberOfStuping + '\'' +
                '}';
    }
}
