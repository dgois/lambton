/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject;

import c0711561.f2017.mad3464.finalproject.employee.CommissionBasedPartTime;
import c0711561.f2017.mad3464.finalproject.employee.Employee;
import c0711561.f2017.mad3464.finalproject.employee.FixedBasedPartTime;
import c0711561.f2017.mad3464.finalproject.employee.FullTime;
import c0711561.f2017.mad3464.finalproject.employee.Intern;
import c0711561.f2017.mad3464.finalproject.vehicle.Car;
import c0711561.f2017.mad3464.finalproject.vehicle.Motorcycle;

/**
 *
 * @author macstudent
 */
public class C0711561_F2017_MAD3464_FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Motorcycle moto1 = new Motorcycle("Yamaha", "2017 YZF-R1M", "H5J4II1", 998, "Racing", "Sport");
        Motorcycle moto2 = new Motorcycle("Harley-Davidson", "750", "DEG134H", 998, "Street", "Standard");

        Car car1 = new Car("Ferrari", "458", "GGJD234", 180.32, "Automatic", 4);
        Car car2 = new Car("Porsche", "Carrera", "GGRT123", 150, "Automatic", 4);

        FullTime ft1 = new FullTime("Janet", 20, 50000, 2000, car1);
        Employee ft2 = new FullTime("Maria", 35, 60000, 6000);
        Employee pt1 = new CommissionBasedPartTime("Matthew", 30, 100, 20, 20, car1);
        Employee pt2 = new CommissionBasedPartTime("Mark", 22, 20, 80, 10, moto1);
        Employee pt3 = new FixedBasedPartTime("Tom", 27, 25, 85, 800, moto2);
        Employee pt4 = new FixedBasedPartTime("Vivian", 25, 70, 164, 1000);
        Employee it1 = new Intern("Loonie", 15, "WoofCenter", 1000.0, car2);
        Employee it2 = new Intern("Toonie", 15, "JKWoofCenter", 1100.0);
        
        Payroll payroll = new Payroll();
        
        payroll.addEmployee(ft1);
        payroll.addEmployee(ft2);
        payroll.addEmployee(pt1);
        payroll.addEmployee(pt2);
        payroll.addEmployee(pt3);
        payroll.addEmployee(pt4);
        payroll.addEmployee(it1);
        payroll.addEmployee(it2);

        payroll.print();
    }
}
