package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

public class CarParking extends AbstractParking {

    public CarParking(int carNumber) {
        super(carNumber);
    }

    @Override
    public void park(Vehicle vehicle) {
        if (isFree()) {
            vehicleParking[vehicleCount] = vehicle;
            vehicleCount = vehicleCount + vehicle.getSize();
        } else {
            System.out.println("Car parking is full");
        }
    }
}
