/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.employee;

import c0711561.f2017.mad3464.finalproject.employee.PartTime;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author macstudent
 */
public class CommissionBasedPartTime extends PartTime {
    
    private double commission;

    public CommissionBasedPartTime(String name, int age, double rate, int hoursWorked, double commission) {
        super(name, age, rate, hoursWorked);
        this.commission = commission;
    }

    @Override
    public BigDecimal calcEarnings() {
        return new BigDecimal(commission).add(super.calcEarnings());
    }
    
    
}
