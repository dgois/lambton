/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.employee;

import c0711561.f2017.mad3464.finalproject.vehicle.Vehicle;
import java.math.BigDecimal;

/**
 *
 * @author macstudent
 */
public class PartTime extends Employee {
    
    private final double rate;
    private final int hoursWorked;

    public PartTime(String name, int age, double rate, int hoursWorked) {
        super(name, age);
        this.rate = rate;
        this.hoursWorked = hoursWorked;
    }
    
    public PartTime(String name, int age, double rate, int hoursWorked, Vehicle vehicle) {
        super(name, age, vehicle);
        this.rate = rate;
        this.hoursWorked = hoursWorked;
    }

    public double getRate() {
        return rate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    @Override
    public BigDecimal calcEarnings() {
        double earnings = rate * hoursWorked;
        return new BigDecimal(earnings);
    }

    @Override
    public String getTypeOfEmployee() {
        return "Part Time";
    }

    @Override
    public String printMyData() {
        return super.printMyData() 
                + "\n -Rate: " + rate
                + "\n -Hours Worked: " + hoursWorked;
    }
}
