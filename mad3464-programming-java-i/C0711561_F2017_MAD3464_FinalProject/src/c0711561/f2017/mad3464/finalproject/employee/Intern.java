/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.employee;

/**
 *
 * @author macstudent
 */
public class Intern extends Employee {
    
    private String schoolName;

    public Intern(String name, int age, String schoolName) {
        super(name, age);
        this.schoolName = schoolName;
    }
}
