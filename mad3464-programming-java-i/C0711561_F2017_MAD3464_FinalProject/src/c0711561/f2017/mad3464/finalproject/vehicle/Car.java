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
public class Car extends Vehicle {
    
    private static final String VEHICLE_TYPE = "Car";
    
    private double trunkSize;
    private String gear;
    private int doorsNumber;

    public Car(String make, String plate, double trunkSize, String gear, int doorsNumber) {
        super(make, plate);
        this.trunkSize = trunkSize;
        this.gear = gear;
        this.doorsNumber = doorsNumber;
    }

    @Override
    public String getVehicleType() {
        return VEHICLE_TYPE;
    }
}
