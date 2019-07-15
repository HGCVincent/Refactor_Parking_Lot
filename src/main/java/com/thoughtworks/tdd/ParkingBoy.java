package com.thoughtworks.tdd;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ParkingLot... parkingLot){
        this.parkingLots.addAll(Arrays.asList(parkingLot));
    }

    public Car fetchCar(ParkingTicket ticket) {
        if (ticket == null){
            throw new MissingParkingTicketException();
        }else if (!parkingLots.stream().anyMatch(parkingLot -> parkingLot.getTiketsAndCar().containsKey(ticket))){
            throw new UnrecognizedParkingTicketException();
        }
        else {
            return parkingLots.stream().filter(parkingLot1 -> parkingLot1.getTiketsAndCar().containsKey(ticket)).findFirst().get().fetch(ticket);
        }
    }

    public ParkingTicket parkCar(Car car) {
        if (parkingLots.stream().allMatch(parkingLot -> parkingLot.isFull())){
            throw new NotEnoughPositionException();
        }
        else {
            return parkingLots.stream().filter(parkingLot1 -> !parkingLot1.isFull()).findFirst().get().park(car);
        }
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

}
