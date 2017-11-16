/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject.vehicle;

import c0711561.f2017.mad3464.finalproject.IPrintable;

/**
 *
 * @author macstudent
 */
public abstract class Vehicle implements IPrintable {
    
    private String make;
    private String model;
    private String plate;

    public Vehicle(String make, String model, String plate) {
        this.make = make;
        this.model = model;
        this.plate = plate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }
    
    public abstract String getVehicleType();

    @Override
    public String printMyData() {
        return String.format("   -Make: %s\n  -Model: %s\n -Plate: %s", make, model, plate);
    }
}
