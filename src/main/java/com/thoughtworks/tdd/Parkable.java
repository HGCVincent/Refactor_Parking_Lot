package com.thoughtworks.tdd;

public interface Parkable {
    ParkingTicket parkCar(Car car);

    Car fetchCar(ParkingTicket ticket);

    boolean isFull();

    boolean containTicket(ParkingTicket ticket);
}
