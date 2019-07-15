package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paker implements Parkable{
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public Paker(ParkingLot... parkingLot) {
        this.parkingLots.addAll(Arrays.asList(parkingLot));    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.getParkingLots().add(parkingLot);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        if (getParkingLots().stream().allMatch(parkingLot -> parkingLot.isFull())){
            throw new NotEnoughPositionException();
        }
        else {
            return getParkingLots().stream().filter(parkingLot1 -> !parkingLot1.isFull()).findFirst().get().park(car);
        }
    }

    @Override
    public Car fetchCar(ParkingTicket ticket) {
        if (ticket == null){
            throw new MissingParkingTicketException();
        }else if (!getParkingLots().stream().anyMatch(parkingLot -> parkingLot.getTiketsAndCar().containsKey(ticket))){
            throw new UnrecognizedParkingTicketException();
        }
        else {
            return getParkingLots().stream().filter(parkingLot1 -> parkingLot1.getTiketsAndCar().containsKey(ticket)).findFirst().get().fetch(ticket);
        }
    }

    @Override
    public boolean isFull() {
        return parkingLots.stream().allMatch(parkingLot1 -> parkingLot1.isFull());
    }

    @Override
    public boolean containTicket(ParkingTicket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.getTiketsAndCar().containsKey(ticket));
    }
}
