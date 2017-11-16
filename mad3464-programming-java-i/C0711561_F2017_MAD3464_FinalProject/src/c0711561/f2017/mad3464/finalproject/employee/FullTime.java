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
public class FullTime extends Employee {
    
    private final double salary;
    private final double bonus;

    public FullTime(String name, int age, double salary, double bonus) {
        super(name, age);
        this.salary = salary;
        this.bonus = bonus;
    }
    
        public FullTime(String name, int age, double salary, double bonus, Vehicle vehicle) {
        super(name, age, vehicle);
        this.salary = salary;
        this.bonus = bonus;
    }

    @Override
    public BigDecimal calcEarnings() {
        return new BigDecimal(salary + bonus);
    }

    @Override
    public String getTypeOfEmployee() {
        return "Full Time";
    }

    @Override
    public String printMyData() {
        String data = super.printMyData() 
                + "\n -Salary: " + Util.toCurrencyFormatFrom(salary) 
                + "\n -Bonus: " + Util.toCurrencyFormatFrom(bonus) 
                + "\n -Earnings: " + Util.toCurrencyFormatFrom(calcEarnings());
        
        return data;
    }
    
    
    
}
