package com.thoughtworks.tdd;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy extends Paker implements Parkable{

    public ParkingBoy(ParkingLot... parkingLot){
        super(parkingLot);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        if(isFull()){
            throw new NotEnoughPositionException();
        }
        ParkingLot parkingLot = getParkingLots().stream().filter(parkingLot1 -> !parkingLot1.isFull()).findFirst().get();
        ParkingTicket ticket = parkingLot.park(car);

        return ticket;
    }

}
