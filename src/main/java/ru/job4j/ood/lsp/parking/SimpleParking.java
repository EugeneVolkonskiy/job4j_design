package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

public class SimpleParking extends AbstractParking {

    private final CarParking carParking;
    private final TruckParking truckParking;
    private final static int SIZE = 1;

    public SimpleParking(int carNumber, int truckNumber) {
        carParking = new CarParking(carNumber);
        truckParking = new TruckParking(truckNumber);
    }

    @Override
    public void park(Vehicle vehicle) {
        if (vehicle.getSize() == SIZE) {
            carParking.park(vehicle);
        } else if (vehicle.getSize() > SIZE && truckParking.isFree()) {
            truckParking.park(vehicle);
        } else if (vehicle.getSize() > SIZE && vehicle.getSize() <= carParking.getFreePlace()) {
            carParking.park(vehicle);
        } else {
            System.out.println("Parking is full");
        }
    }

    @Override
    public int getFreePlace() {
        System.out.printf("Car parking free: %d, Truck parking free: %d", carParking.getFreePlace(), truckParking.getFreePlace());
        return carParking.getFreePlace() + truckParking.getFreePlace();
    }
}
