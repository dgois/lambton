package com.example.macstudent.cricketplayerrank.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.macstudent.cricketplayerrank.db.DatabaseHandler;
import com.example.macstudent.cricketplayerrank.model.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by macstudent on 2017-12-01.
 */

public class PlayerDAO {

    // Contacts table name
    public static final String TABLE_PLAYER = "players";

    // Contacts Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_TEAM_CATEGORY = "category";
    public static final String KEY_TEAM_COUNTRY = "country";
    public static final String KEY_TOTAL_POINTS = "total_points";

    private DatabaseHandler databaseHandler;

    public PlayerDAO(Context context) {
        databaseHandler = new DatabaseHandler(context);
    }

    public void add(Player player) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.getName());
        values.put(KEY_GENDER, player.getGender());
        values.put(KEY_TEAM_CATEGORY, player.getCategory());
        values.put(KEY_TEAM_COUNTRY, player.getCountry());
        values.put(KEY_TOTAL_POINTS, player.calculateTotalPoints());

        Log.i("DENIS", "Total points before insert: " + player.getTotalPoints());
        db.insert(TABLE_PLAYER, null, values);
        db.close();
    }

    public Player get(int id) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        Cursor cursor = db.query(TABLE_PLAYER, new String[]{KEY_ID, KEY_NAME, KEY_GENDER, KEY_TEAM_CATEGORY, KEY_TEAM_COUNTRY, KEY_TOTAL_POINTS}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            Player player = new Player(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), new Date(), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)));
            //int id, String name, String gender, Date birthdate, String category, String country
            return player;
        }
        return null;
    }

    public List<Player> getAllPlayers() {
        List<Player> playerList = new ArrayList<Player>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PLAYER + " order by " + KEY_TOTAL_POINTS + " desc";

        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), new Date(), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)));
                playerList.add(player);
            } while (cursor.moveToNext());
        }

        // return contact list
        return playerList;
    }

    // Deleting single contact
    public void deleteAll() {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(TABLE_PLAYER, null,
                null);
        db.close();
    }
}
