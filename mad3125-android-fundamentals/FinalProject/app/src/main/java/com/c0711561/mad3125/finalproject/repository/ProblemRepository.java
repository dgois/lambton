package com.c0711561.mad3125.finalproject.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.c0711561.mad3125.finalproject.BasicApp;
import com.c0711561.mad3125.finalproject.dao.ProblemDao;
import com.c0711561.mad3125.finalproject.model.Problem;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by macstudent on 2017-12-08.
 */

public class ProblemRepository implements ProblemDao {

    private ProblemDao problemDao;

    public ProblemRepository(Application application) {
        problemDao = ((BasicApp)application).getDatabase().problemDao();
    }

    @Override
    public List<Problem> getAll() {
        List<Problem> problems = null;
        try {
            problems = new AsyncTask<Void, Void, List<Problem>>() {

                @Override
                protected List<Problem> doInBackground(Void... voids) {
                    return problemDao.getAll();
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.e("REPOSITORY", e.getMessage());
        } catch (ExecutionException e) {
            Log.e("REPOSITORY", e.getMessage());
        }
        return problems;
    }

    @Override
    public Problem findById(int id) {
        Problem problem = null;
        try {
            problem = new AsyncTask<Integer, Void, Problem>() {

                @Override
                protected Problem doInBackground(Integer... params) {
                    return problemDao.findById(params[0]);
                }

            }.execute(id).get();
        } catch (InterruptedException e) {
            Log.e("REPOSITORY", e.getMessage());
        } catch (ExecutionException e) {
            Log.e("REPOSITORY", e.getMessage());
        }
        return problem;
    }

    @Override
    public long insert(Problem problem) {
        try {
            return new AsyncTask<Problem, Void, Long>() {

                @Override
                protected Long doInBackground(Problem... problems) {

                    return problemDao.insert(problems[0]);
                }
            }.execute(problem).get();
        } catch (InterruptedException e) {
            Log.e("REPOSITORY", e.getMessage());
        } catch (ExecutionException e) {
            Log.e("REPOSITORY", e.getMessage());
        }
        return 0;
    }

    @Override
    public void delete(Problem problem) {
        new AsyncTask<Problem, Void, Void>() {

            @Override
            protected Void doInBackground(Problem... problems) {
                problemDao.delete(problems[0]);
                return null;
            }
        }.execute(problem);
    }

    @Override
    public int update(Problem problem) {
        try {
            return new AsyncTask<Problem, Void, Integer>() {

                @Override
                protected Integer doInBackground(Problem... problems) {
                    return problemDao.update(problems[0]);
                }
            }.execute(problem).get();
        } catch (InterruptedException e) {
            Log.e("REPOSITORY", e.getMessage());
        } catch (ExecutionException e) {
            Log.e("REPOSITORY", e.getMessage());
        }
        return 0;
    }


}
