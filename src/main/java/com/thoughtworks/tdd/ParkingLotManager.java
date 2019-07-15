package com.thoughtworks.tdd;

import sun.security.krb5.internal.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLotManager extends Paker implements Parkable{
    List<Paker> parkers = new ArrayList<>();

    public ParkingLotManager(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    public void addParkingBoy(Paker parkingBoy) {
        parkers.add(parkingBoy);
    }

    public ParkingTicket parkCar(Car car) {
        for (Paker parker : parkers){
            if (parker.getParkingLots().stream().allMatch(parkingLot -> parkingLot.isFull())){
               throw new NotEnoughPositionException();
            }
            else {
                return parker.parkCar(car);
            }
        }
        return parkCar(car);
    }

    public Car fetchingCar(ParkingTicket ticket){
        for(Paker parker : parkers){
            if(parker.getParkingLots().stream().anyMatch(parkingLot -> parkingLot.getTiketsAndCar().containsKey(ticket))){
                return parker.fetchCar(ticket);
            }
        }
        return fetchCar(ticket);
    }
}
