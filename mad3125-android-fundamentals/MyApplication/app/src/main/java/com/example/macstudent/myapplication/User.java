package com.example.macstudent.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by macstudent on 2017-12-05.
 */

@Entity
public class User {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "user_email")
    private String email;



}
