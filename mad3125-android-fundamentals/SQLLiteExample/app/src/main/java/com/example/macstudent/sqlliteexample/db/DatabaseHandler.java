package com.example.macstudent.sqlliteexample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.macstudent.sqlliteexample.db.dao.ContactDAO;
import com.example.macstudent.sqlliteexample.db.dao.PersonDAO;
import com.example.macstudent.sqlliteexample.model.Contact;

/**
 * Created by macstudent on 2017-11-30.
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
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + ContactDAO.TABLE_CONTACTS + "("
                + ContactDAO.KEY_ID + " INTEGER PRIMARY KEY," + ContactDAO.KEY_NAME + " TEXT,"
                + ContactDAO.KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_PEOPLE_TABLE = "CREATE TABLE " + PersonDAO.TABLE_PEOPLE + "("
                + PersonDAO.KEY_ID + " INTEGER PRIMARY KEY," + PersonDAO.KEY_NAME + " TEXT)";
        db.execSQL(CREATE_PEOPLE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ContactDAO.TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + PersonDAO.TABLE_PEOPLE);

        // Create tables again
        onCreate(db);
    }


}
