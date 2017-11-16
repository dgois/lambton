/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.employee;

import c0711561.f2017.mad3464.finalproject.employee.PartTime;
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

    @Override
    public BigDecimal calcEarnings() {
        return new BigDecimal(fixedAmount).add(super.calcEarnings());
    }
}
