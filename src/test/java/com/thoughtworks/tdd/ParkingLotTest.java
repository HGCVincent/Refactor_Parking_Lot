package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.LockSupport;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingLotTest {
    @Test
    public void should_park_car_and_fetch_car(){
        Car car = new Car();
        ParkingLot parkingLot =  new ParkingLot(1);
        ParkingTicket ticket = parkingLot.park(car);
        Car fetchCar = parkingLot.fetch(ticket);
        assertSame(car,fetchCar);
    }

    @Test
    public void should_return_fetch_right_car_when_park_multiple_cars(){
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot =  new ParkingLot(2);

        ParkingTicket ticket1 = parkingLot.park(car1);
        ParkingTicket ticket2 = parkingLot.park(car2);
        Car fetchCar1 = parkingLot.fetch(ticket1);
        Car fetchCar2 = parkingLot.fetch(ticket2);

        assertSame(car1,fetchCar1);
        assertSame(car2,fetchCar2);
    }

    @Test
    public void should_return_no_car_when_parking_boy_park_car_given_no_ticket(){
        Car car = new Car();
        ParkingLot parkingLot =  new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.parkCar(car);

        Assertions.assertThrows(MissingParkingTicketException.class,() -> parkingBoy.fetchCar(null));
    }

    @Test
    public void should_return_no_car_when_parking_boy_fetch_car_given_used_ticket(){
        Car car = new Car();
        ParkingLot parkingLot =  new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket ticket = parkingBoy.parkCar(car);
        parkingBoy.fetchCar(ticket);

        Assertions.assertThrows(UnrecognizedParkingTicketException.class,() -> parkingBoy.fetchCar(ticket));
    }

    @Test
    public void should_return_no_ticket_when_parking_boy_park_car_given_parking_lot_is_full(){
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot =  new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.parkCar(car1);

        Assertions.assertThrows(NotEnoughPositionException.class,() -> parkingBoy.parkCar(car2));
    }

    @Test
    public void should_park_the_car_to_second_parking_lot_when_parking_boy_park_car_given_two_parking_lot_and_the_first_parking_lot_is_full(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1,parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();

        parkingBoy.parkCar(car1);
        ParkingTicket ticket = parkingBoy.parkCar(car2);

        assertSame(true,parkingLot2.getTiketsAndCar().containsKey(ticket));
    }

    @Test
    public void should_park_the_car_to_second_parking_lot_when_smart_parking_boy_park_car_given_two_parking_lot_and_the_second_parking_lot_has_more_empty_position(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1,parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();

        smartParkingBoy.parkCar(car1);
        ParkingTicket ticket = smartParkingBoy.parkCar(car2);

        assertSame(true,parkingLot2.getTiketsAndCar().containsKey(ticket));
    }

    @Test
    public void should_park_the_car_to_second_parking_lot_when_super_smart_parking_boy_park_car_given_two_parking_lot_and_the_second_parking_lot_has_larger_vailable_position_rate(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        SuperSmartParkingBoy smartParkingBoy = new SuperSmartParkingBoy(parkingLot1,parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();

        smartParkingBoy.parkCar(car1);
        ParkingTicket ticket = smartParkingBoy.parkCar(car2);

        assertSame(true,parkingLot2.getTiketsAndCar().containsKey(ticket));
    }

    @Test
    public void should_park_and_fetch_car_when_manage_specify_parkingBoy() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot);
        Paker smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.addParkingLot(parkingLot);
        parkingLotManager.addParkingBoy(smartParkingBoy);

        Car car = new Car();
        ParkingTicket ticket = parkingLotManager.parkCar(car);
        Car fetchCar = smartParkingBoy.fetchCar(ticket);

        Assertions.assertSame(car, fetchCar);
    }


}
