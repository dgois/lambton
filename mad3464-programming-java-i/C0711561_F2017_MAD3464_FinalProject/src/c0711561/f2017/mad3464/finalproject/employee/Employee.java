/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author macstudent
 */
public class Employee {
    
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public int calcBirthYear() {
        return LocalDate.now().getYear() - age;
    }
    
    public BigDecimal calcEarnings() {
        return new BigDecimal(1000);
    }
    
}
