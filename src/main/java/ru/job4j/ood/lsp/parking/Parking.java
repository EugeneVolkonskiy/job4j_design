package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

public interface Parking {

    void park(Vehicle vehicle);

    int getFreePlace();
}

