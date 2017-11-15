/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.employee;

import java.math.BigDecimal;

/**
 *
 * @author macstudent
 */
public class PartTime extends Employee {
    
    private double rate;
    private int hoursWorked;

    public PartTime(String name, int age, double rate, int hoursWorked) {
        super(name, age);
        this.rate = rate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public BigDecimal calcEarnings() {
        double earnings = rate * hoursWorked;
        return new BigDecimal(earnings);
    }
    
}
