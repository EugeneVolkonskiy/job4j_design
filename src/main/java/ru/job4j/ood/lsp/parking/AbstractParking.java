package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

public class AbstractParking implements Parking {

    protected int vehicleNumber;
    protected int vehicleCount = 0;
    protected Vehicle[] vehicleParking;

    public AbstractParking() {

    }

    public AbstractParking(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        vehicleParking = new Vehicle[vehicleNumber];
    }

    public boolean isFree() {
        return vehicleParking.length != vehicleCount;
    }

    @Override
    public int getFreePlace() {
        return vehicleParking.length - vehicleCount;
    }

    @Override
    public void park(Vehicle vehicle) {
        if (isFree()) {
            vehicleParking[vehicleCount] = vehicle;
        } else {
            System.out.println("Parking is full");
        }
    }
}
