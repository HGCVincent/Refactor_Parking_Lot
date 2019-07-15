package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paker {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public Paker(ParkingLot... parkingLot) {
        this.parkingLots.addAll(Arrays.asList(parkingLot));    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
}
