package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

public class TruckParking extends AbstractParking {

    public TruckParking(int truckNumber) {
        super(truckNumber);
    }

    @Override
    public void park(Vehicle vehicle) {
        if (isFree()) {
            vehicleParking[vehicleCount] = vehicle;
            vehicleCount++;
        } else {
            System.out.println("Truck parking is full");
        }
    }
}
