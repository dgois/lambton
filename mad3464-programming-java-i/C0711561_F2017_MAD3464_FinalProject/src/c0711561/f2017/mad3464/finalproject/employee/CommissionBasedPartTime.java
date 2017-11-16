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
public class CommissionBasedPartTime extends PartTime {
    
    private final double commission;

    public CommissionBasedPartTime(String name, int age, double rate, int hoursWorked, double commission) {
        super(name, age, rate, hoursWorked);
        this.commission = commission;
    }
    
    public CommissionBasedPartTime(String name, int age, double rate, int hoursWorked, double commission, Vehicle vehicle) {
        super(name, age, rate, hoursWorked, vehicle);
        this.commission = commission;
    }
    
    private double calcSalary() {
        return getRate() * getHoursWorked();
    }
    
    private double calcCommission() {
        return calcSalary() * (commission / 100);
    }

    @Override
    public String getTypeOfEmployee() {
        return super.getTypeOfEmployee() + " / Commissioned";
    }

    @Override
    public BigDecimal calcEarnings() {
        return new BigDecimal(commission).add(super.calcEarnings());
    }

    @Override
    public String printMyData() {
        return super.printMyData()
                + "\n  -Commission: " + commission
                + "\n  -Earnings: " + Util.toCurrencyFormatFrom(calcEarnings())
                + " " + calcSalary() + " + " + commission + "%" + " of " + calcSalary();
    }
}
