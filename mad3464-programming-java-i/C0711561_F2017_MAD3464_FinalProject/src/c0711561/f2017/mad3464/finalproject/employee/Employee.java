/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.employee;

import c0711561.f2017.mad3464.finalproject.IPrintable;
import c0711561.f2017.mad3464.finalproject.vehicle.Vehicle;
import java.time.LocalDate;

/**
 *
 * @author macstudent
 */
public abstract class Employee implements IPrintable {
    
    private final String name;
    private final int age;
    private Vehicle vehicle;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public Employee(String name, int age, Vehicle vehicle) {
        this.name = name;
        this.age = age;
        this.vehicle = vehicle;
    }
    
    public int calcBirthYear() {
        return LocalDate.now().getYear() - age;
    }
    
    public double calcEarnings() {
        return 1000;
    }
    
    public abstract String getTypeOfEmployee();
    
    @Override
    public String printMyData() {
        String line = "\n-------------------------------------------";
        
        String personalInfo = String.format("\nName: %s\nAge: %d\nYear of Birth: %d\n", name, age, calcBirthYear());
        
        String vehicleInfo;
        if (employeeHasVehicle()) {
            vehicleInfo = String.format("\nEmployee has a %s\n%s", vehicle.getVehicleType(), vehicle.printMyData());
        } else {
            vehicleInfo = "\nEmployee has no vehicle registered";
        }
        
        String employeeInfo = String.format("\nEmployee is %s", getTypeOfEmployee());
        
        return new StringBuilder().append(line).append(personalInfo).append(vehicleInfo).append(employeeInfo).toString();
    }

    private boolean employeeHasVehicle() {
        return vehicle != null;
    }
}
