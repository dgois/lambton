/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject;

import c0711561.f2017.mad3464.finalproject.employee.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macstudent
 */
public class Payroll {
    
    private List<Employee> employees;
    private double total;

    public Payroll() {
        employees = new ArrayList<>();
        total = 0;
    }
    
    public Payroll(List<Employee> employees) {
        this.employees = employees;
    }
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
        total += employee.calcEarnings();
    }
    
    public void print() {
        
        for (Employee e : employees) {
            System.out.println(e.printMyData());
        }
        
        System.out.println("\n-------------------------------------------");
        System.out.println("\n");
        System.out.println("TOTAL PAYROLL: " + Util.toCurrencyFormatFrom(total) + " Canadian Dollars\n");
    }
    
}
