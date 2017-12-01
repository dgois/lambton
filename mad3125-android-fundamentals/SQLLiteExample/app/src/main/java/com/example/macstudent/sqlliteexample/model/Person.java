package com.example.macstudent.sqlliteexample.model;

/**
 * Created by macstudent on 2017-11-30.
 */

public class Person {

    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
