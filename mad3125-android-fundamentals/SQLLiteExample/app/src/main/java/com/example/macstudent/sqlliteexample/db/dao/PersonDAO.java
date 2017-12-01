package com.example.macstudent.sqlliteexample.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.macstudent.sqlliteexample.db.DatabaseHandler;
import com.example.macstudent.sqlliteexample.model.Contact;
import com.example.macstudent.sqlliteexample.model.Person;

/**
 * Created by macstudent on 2017-11-30.
 */
public class PersonDAO {

    // Contacts table name
    public static final String TABLE_PEOPLE = "people";

    // Contacts Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";

    private DatabaseHandler databaseHandler;

    public PersonDAO(Context context) {
        databaseHandler = new DatabaseHandler(context);
    }

    public void addPerson(Person person) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());

        db.insert(TABLE_PEOPLE, null, values);
        db.close();
    }

    public Person getPerson(int id) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        Cursor cursor = db.query(TABLE_PEOPLE, new String[]{KEY_ID, KEY_NAME}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            Person person = new Person(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
            return person;
        }
        return null;
    }
}
