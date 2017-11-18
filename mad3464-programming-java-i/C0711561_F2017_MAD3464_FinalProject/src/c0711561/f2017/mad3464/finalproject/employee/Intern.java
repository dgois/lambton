/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.employee;

import c0711561.f2017.mad3464.finalproject.Util;
import c0711561.f2017.mad3464.finalproject.vehicle.Vehicle;

/**
 *
 * @author macstudent
 */
public class Intern extends Employee {

    private String schoolName;
    private double fixedSalary;

    public Intern(String name, int age, String schoolName, double fixedSalary) {
        super(name, age);
        this.schoolName = schoolName;
        this.fixedSalary = fixedSalary;
    }
    
    public Intern(String name, int age, String schoolName, double fixedSalary, Vehicle vehicle) {
        super(name, age, vehicle);
        this.schoolName = schoolName;
        this.fixedSalary = fixedSalary;
    }

    @Override
    public String getTypeOfEmployee() {
        return "Intern";
    }

    @Override
    public String printMyData() {
        return super.printMyData()
                + "\n - School Name: " + schoolName
                + "\n - Earnings: " + Util.toCurrencyFormatFrom(calcEarnings());
    }
}
