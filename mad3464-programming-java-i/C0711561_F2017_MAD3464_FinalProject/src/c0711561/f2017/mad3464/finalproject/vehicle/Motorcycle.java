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
public class Motorcycle extends Vehicle {
    
    private static final String VEHICLE_TYPE = "Motorcycle";
    
    private int cylinderCapacity;
    private String type; //street, off-road, racing, and dual purpose
    private String ridingPosture; //sport, standard, cruiser

    public Motorcycle(String make, String plate, int cylinderCapacity, String type, String ridingPosture) {
        super(make, plate);
        this.cylinderCapacity = cylinderCapacity;
        this.type = type;
        this.ridingPosture = ridingPosture;
    }

    @Override
    public String getVehicleType() {
        return VEHICLE_TYPE;
    }
}
