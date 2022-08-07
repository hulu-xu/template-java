package org.oobootcamp.warmup.smartparkingboy;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.parkinglot.Car;
import org.oobootcamp.warmup.parkinglot.ParkingLot;
import org.oobootcamp.warmup.parkinglot.Ticket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotInvalidTicket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotSpaceIsFull;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {

    public SmartParkingBoy smartParkingBoy;
    public ParkingLot parkingLotA;
    public ParkingLot parkingLotB;

    public SmartParkingBoyTest() {
        this.parkingLotA = new ParkingLot(2);
        this.parkingLotB = new ParkingLot(2);
        this.smartParkingBoy = new SmartParkingBoy(
                Lists.newArrayList(parkingLotA, parkingLotB));
    }

    @Test
    void should_park_to_parking_lot_B_when_parking_given_parking_lot_B_has_most_available_space() {
        this.parkingLotA.parking(new Car());

        Car car = new Car();
        Ticket ticket = this.smartParkingBoy.parking(car);

        assertNotNull(ticket);
        assertEquals(car, this.parkingLotB.pickup(ticket));
    }

    @Test
    void should_park_to_parking_lot_A_when_parking_given_both_parking_lot_A_and_B_have_most_available_space() {
        this.parkingLotA.parking(new Car());
        this.parkingLotB.parking(new Car());

        Car car = new Car();
        Ticket ticket = this.smartParkingBoy.parking(car);

        assertNotNull(ticket);
        assertEquals(car, this.parkingLotA.pickup(ticket));
    }

    @Test
    void should_raise_parking_lots_are_full_error_message_when_parking_given_boy_given_all_parking_lot_are_full() {
        this.parkingLotA.parking(new Car());
        this.parkingLotA.parking(new Car());
        this.parkingLotB.parking(new Car());
        this.parkingLotB.parking(new Car());

        assertThrows(ParkingLotSpaceIsFull.class, () -> this.smartParkingBoy.parking(new Car()));
    }

    @Test
    void should_pick_up_car_when_pick_up_given_valid_ticket_of_parking_lot_A() {
        Car myCar = new Car();
        Ticket ticket = this.parkingLotA.parking(myCar);

        Car car = this.smartParkingBoy.pickup(ticket);

        assertEquals(car, myCar);
    }

    @Test
    void should_raise_invalid_ticket_error_message_when_pick_up_given_invalid_ticket() {
        Ticket ticket = new Ticket();

        assertThrows(ParkingLotInvalidTicket.class, () -> this.smartParkingBoy.pickup(ticket));
    }

    @Test
    void should_raise_invalid_ticket_error_message_when_pick_up_given_a_valid_ticket_was_used_twice() {
        Ticket ticket = this.parkingLotA.parking(new Car());
        this.smartParkingBoy.pickup(ticket);

        assertThrows(ParkingLotInvalidTicket.class, () -> this.smartParkingBoy.pickup(ticket));
    }
}
