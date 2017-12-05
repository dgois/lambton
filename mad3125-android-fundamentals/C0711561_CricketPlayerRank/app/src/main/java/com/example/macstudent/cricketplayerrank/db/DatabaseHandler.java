package com.example.macstudent.cricketplayerrank.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.macstudent.cricketplayerrank.dao.PlayerDAO;

/**
 * Created by macstudent on 2017-12-01.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYERS_TABLE = "CREATE TABLE " + PlayerDAO.TABLE_PLAYER
                + "("
                + PlayerDAO.KEY_ID + " INTEGER PRIMARY KEY,"
                + PlayerDAO.KEY_NAME + " TEXT,"
                + PlayerDAO.KEY_GENDER + " TEXT,"
                + PlayerDAO.KEY_TEAM_CATEGORY + " TEXT,"
                + PlayerDAO.KEY_TEAM_COUNTRY + " TEXT,"
                + PlayerDAO.KEY_TOTAL_POINTS + " INTEGER"
                + ")";
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + PlayerDAO.TABLE_PLAYER);

        // Create tables again
        onCreate(db);
    }




}
