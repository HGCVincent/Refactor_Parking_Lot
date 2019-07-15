package com.thoughtworks.tdd;

import sun.security.krb5.internal.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLotManager extends ParkingBoy{
    List<ParkingLot> parkingLots = new ArrayList<>();
    List<ParkingBoy> parkingBoys = new ArrayList<>();

    public ParkingLotManager(ParkingLot... parkingLot) {
        this.parkingLots.addAll(Arrays.asList(parkingLot));
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
    }

    public ParkingTicket parkingCar(Car car) {
        for (ParkingBoy parkingBoy : parkingBoys){
            if (parkingBoy.parkingLots.stream().allMatch(parkingLot -> parkingLot.isFull())){
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
            if(parkingBoy.parkingLots.stream().anyMatch(parkingLot -> parkingLot.getTiketsAndCar().containsKey(ticket))){
                return parkingBoy.fetchCar(ticket);
            }
        }
        return fetchCar(ticket);
    }
}
