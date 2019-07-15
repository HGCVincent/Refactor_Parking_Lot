package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends Paker {

    public SmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    public ParkingTicket parkCar(Car car) {
        if (getParkingLots().stream().allMatch(parkingLot -> parkingLot.isFull())){
            throw new NotEnoughPositionException();
        }
        ParkingLot parkingLot = getParkingLots().stream().max(Comparator.comparingInt(ParkingLot::getRestPosition)).get();
        ParkingTicket ticket = parkingLot.park(car);
        return ticket;
    }
}
