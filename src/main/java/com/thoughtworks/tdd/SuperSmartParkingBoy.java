package com.thoughtworks.tdd;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        if (parkingLots.stream().allMatch(parkingLot -> parkingLot.isFull())){
            throw new NotEnoughPositionException();
        }
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparingDouble(ParkingLot::getAvailableRate)).orElse(null);
        ParkingTicket ticket = parkingLot.park(car);
        return ticket;
    }
}
