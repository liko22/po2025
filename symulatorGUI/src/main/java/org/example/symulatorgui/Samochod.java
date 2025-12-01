package org.example.symulatorgui;

public class Samochod {
    private String model;
    private String registrationNumber;
    private double weight;
    private int maxSpeed;
    private String engineType;
    private String gearboxType;

    public Samochod(String model, String registrationNumber, double weight, int maxSpeed, String engineType, String gearboxType) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.engineType = engineType;
        this.gearboxType = gearboxType;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    @Override
    public String toString() {
        return model + " (" + registrationNumber + ")";
    }
}