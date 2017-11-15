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
public class FullTime extends Employee {
    
    private double salary;
    private double bonus;

    public FullTime(String name, int age, double salary, double bonus) {
        super(name, age);
        this.salary = salary;
        this.bonus = bonus;
    }

    @Override
    public BigDecimal calcEarnings() {
        return new BigDecimal(salary + bonus);
    }
    
    
}
