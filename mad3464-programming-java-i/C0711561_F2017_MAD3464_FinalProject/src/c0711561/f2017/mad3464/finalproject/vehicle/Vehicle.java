/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.vehicle;

/**
 *
 * @author macstudent
 */
public abstract class Vehicle {
    
    private String make;
    private String plate;

    public Vehicle(String make, String plate) {
        this.make = make;
        this.plate = plate;
    }

    public String getMake() {
        return make;
    }

    public String getPlate() {
        return plate;
    }
    
    public abstract String getVehicleType();
}
