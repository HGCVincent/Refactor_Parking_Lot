package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends Paker implements Parkable {

    public SmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        if(isFull()){
            throw new NotEnoughPositionException();
        }
        ParkingLot parkingLot = getParkingLots().stream().max(Comparator.comparingInt(ParkingLot::getRestPosition)).get();
        ParkingTicket ticket = parkingLot.park(car);
        return ticket;
    }
}
