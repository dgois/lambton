package com.c0711561.mad3125.finalproject;

import android.app.Application;

import com.c0711561.mad3125.finalproject.db.AppDatabase;
import com.c0711561.mad3125.finalproject.db.AppExecutors;

/**
 * Created by macstudent on 2017-12-08.
 */

public class BasicApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }
}
