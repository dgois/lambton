package com.c0711561.mad3125.finalproject.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.c0711561.mad3125.finalproject.model.Problem;

import java.util.List;

/**
 * Created by macstudent on 2017-12-08.
 */

@Dao
public interface ProblemDao {

    @Query("SELECT * FROM problem")
    List<Problem> getAll();

    @Query("SELECT * FROM problem WHERE id = :id")
    Problem findById(int id);

    @Insert
    long insert(Problem problem);

    @Delete
    void delete(Problem problem);

    @Update
    int update(Problem problem);
}
