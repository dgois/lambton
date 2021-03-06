package com.c0711561.mad3125.finalproject.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.c0711561.mad3125.finalproject.model.User;

import java.util.List;

/**
 * Created by macstudent on 2017-12-07.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE email LIKE :email")
    User findByEmail(String email);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
