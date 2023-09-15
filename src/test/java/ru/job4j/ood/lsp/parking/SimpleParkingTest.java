package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.vehicle.Vehicle;
import ru.job4j.ood.lsp.parking.vehicle.Car;
import ru.job4j.ood.lsp.parking.vehicle.Truck;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class SimpleParkingTest {

    @Test
    public void whenAdd3CarsAnd2TrucksThan2FreePlaces() {
        Parking simpleParking = new SimpleParking(5, 2);
        List<Vehicle> list = List.of(
                new Car(),
                new Car(),
                new Car(),
                new Truck(2),
                new Truck(2));
        for (Vehicle vehicle : list) {
            simpleParking.park(vehicle);
        }
        assertThat(simpleParking.getFreePlace()).isEqualTo(2);
    }

    @Test
    public void whenAdd3CarsAnd4TrucksThanParkingIsFull() {
        Parking simpleParking = new SimpleParking(5, 3);
        List<Vehicle> list = List.of(
                new Car(),
                new Car(),
                new Car(),
                new Truck(2),
                new Truck(2),
                new Truck(2),
                new Truck(2));
        for (Vehicle vehicle : list) {
            simpleParking.park(vehicle);
        }
        assertThat(simpleParking.getFreePlace()).isEqualTo(0);
    }
}