/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.employee;

import c0711561.f2017.mad3464.finalproject.Util;
import c0711561.f2017.mad3464.finalproject.vehicle.Vehicle;
import java.math.BigDecimal;

/**
 *
 * @author macstudent
 */
public class FixedBasedPartTime extends PartTime {
    
    private double fixedAmount;

    public FixedBasedPartTime(String name, int age, double rate, int hoursWorked, double fixedAmount) {
        super(name, age, rate, hoursWorked);
        this.fixedAmount = fixedAmount;
    }
    
    public FixedBasedPartTime(String name, int age, double rate, int hoursWorked, double fixedAmount, Vehicle vehicle) {
        super(name, age, rate, hoursWorked, vehicle);
        this.fixedAmount = fixedAmount;
    }

    private double calcSalary() {
        return getRate() * getHoursWorked();
    }

    @Override
    public String getTypeOfEmployee() {
        return super.getTypeOfEmployee() + " / Fixed Amt";
    }
    
    @Override
    public double calcEarnings() {
        return fixedAmount + calcSalary();
    }

    @Override
    public String printMyData() {
        return super.printMyData() 
                + "\n - Fixed Amount: " + Util.toCurrencyFormatFrom(fixedAmount)
                + "\n - Earnings: " + Util.toCurrencyFormatFrom(calcEarnings()) + "(" + calcSalary() + " + " + fixedAmount + ")";
    }
}
