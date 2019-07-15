package com.thoughtworks.tdd;

import sun.security.krb5.internal.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLotManager extends Paker{
    List<ParkingBoy> parkingBoys = new ArrayList<>();

    public ParkingLotManager(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
    }

    public ParkingTicket parkingCar(Car car) {
        for (ParkingBoy parkingBoy : parkingBoys){
            if (parkingBoy.getParkingLots().stream().allMatch(parkingLot -> parkingLot.isFull())){
               throw new NotEnoughPositionException();
            }
            else {
                return parkingBoy.parkCar(car);
            }
        }
        return parkCar(car);
    }

    public Car fetchingCar(ParkingTicket ticket){
        for(ParkingBoy parkingBoy : parkingBoys){
            if(parkingBoy.getParkingLots().stream().anyMatch(parkingLot -> parkingLot.getTiketsAndCar().containsKey(ticket))){
                return parkingBoy.fetchCar(ticket);
            }
        }
        return fetchCar(ticket);
    }
}
