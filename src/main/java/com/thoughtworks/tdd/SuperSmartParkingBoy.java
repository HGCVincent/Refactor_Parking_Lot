package com.thoughtworks.tdd;

import java.util.Comparator;

public class SuperSmartParkingBoy extends Paker {

    public SuperSmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    public ParkingTicket parkCar(Car car) {
        if (getParkingLots().stream().allMatch(parkingLot -> parkingLot.isFull())){
            throw new NotEnoughPositionException();
        }
        ParkingLot parkingLot = getParkingLots().stream().max(Comparator.comparingDouble(ParkingLot::getAvailableRate)).orElse(null);
        ParkingTicket ticket = parkingLot.park(car);
        return ticket;
    }
}
