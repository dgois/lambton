package com.c0711561.mad3125.finalproject.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

/**
 * Created by macstudent on 2017-12-07.
 */
@Entity
public class User {

    public enum roleTypes {REPORTER, SOLVER};

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String email;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private String role;

    public User() {}

    @Ignore
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Ignore
    public boolean isReporter() {
        return roleTypes.REPORTER.name().equals(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
