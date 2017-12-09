package com.c0711561.mad3125.finalproject.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.c0711561.mad3125.finalproject.BasicApp;
import com.c0711561.mad3125.finalproject.dao.UserDao;
import com.c0711561.mad3125.finalproject.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by macstudent on 2017-12-08.
 */

public class UserRepository implements UserDao {

    private UserDao userDao;

    public UserRepository(Application application) {
        userDao = ((BasicApp)application).getDatabase().userDao();
    }

    @Override
    public List<User> getAll() {
        List<User> users = null;
        try {
            users = new AsyncTask<Void, Void, List<User>>() {

                @Override
                protected List<User> doInBackground(Void... voids) {
                    return userDao.getAll();
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.e("REPOSITORY", e.getMessage());
        } catch (ExecutionException e) {
            Log.e("REPOSITORY", e.getMessage());
        }
        return users;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            user = new AsyncTask<String, Void, User>() {

                @Override
                protected User doInBackground(String... params) {
                    return userDao.findByEmail(params[0]);
                }

            }.execute(email).get();
        } catch (InterruptedException e) {
            Log.e("REPOSITORY", e.getMessage());
        } catch (ExecutionException e) {
            Log.e("REPOSITORY", e.getMessage());
        }
        return user;
    }

    @Override
    public void insertAll(User... users) {
        new AsyncTask<User, Void, Void>() {

            @Override
            protected Void doInBackground(User... usersParam) {
                userDao.insertAll(usersParam);
                return null;
            }
        }.execute(users);
    }

    @Override
    public void delete(User user) {
        new AsyncTask<User, Void, Void>() {

            @Override
            protected Void doInBackground(User... users) {
                userDao.delete(users[0]);
                return null;
            }
        }.execute(user);
    }
}
