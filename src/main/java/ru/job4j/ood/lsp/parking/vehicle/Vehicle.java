package ru.job4j.ood.lsp.parking.vehicle;

public abstract class Vehicle {

    private int size;

    public Vehicle() {
    }

    public Vehicle(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
