package com.c0711561.mad3125.finalproject.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by macstudent on 2017-12-07.
 */
public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
