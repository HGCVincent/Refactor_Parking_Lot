package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        if (parkingLots.stream().allMatch(parkingLot -> parkingLot.isFull())){
            throw new NotEnoughPositionException();
        }
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparingInt(ParkingLot::getRestPosition)).get();
        ParkingTicket ticket = parkingLot.park(car);
        return ticket;
    }
}
