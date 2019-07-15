package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket,Car> tiketsAndCar = new HashMap<>();
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Map<ParkingTicket, Car> getTiketsAndCar() {
        return tiketsAndCar;
    }

    public void setTiketsAndCar(Map<ParkingTicket, Car> tiketsAndCar) {
        this.tiketsAndCar = tiketsAndCar;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        tiketsAndCar.put(ticket,car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = tiketsAndCar.get(ticket);
        tiketsAndCar.remove(ticket);
        return car;
    }

    public boolean isFull(){
        if (tiketsAndCar.size() >= capacity){
            return true;
        }
        else{
            return false;
        }
    }

    public int getRestPosition() {
        return capacity - tiketsAndCar.size();
    }

    public double getAvailableRate(){
        double cap = (double) capacity;
        return (double) getRestPosition() / cap;
    }
}
