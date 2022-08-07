package org.oobootcamp.warmup.parkingmanager;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.graduateparkingboy.GraduateParkingBoy;
import org.oobootcamp.warmup.parkinglot.Car;
import org.oobootcamp.warmup.parkinglot.ParkingLot;
import org.oobootcamp.warmup.parkinglot.Ticket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotInvalidTicket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotSpaceIsFull;
import org.oobootcamp.warmup.smartparkingboy.SmartParkingBoy;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingManagerTest {
    public SmartParkingBoy smartParkingBoy;
    public GraduateParkingBoy graduateParkingBoy;
    public ParkingManager parkingManager;
    public ParkingLot parkingLotL1;
    public ParkingLot parkingLotL2;
    public ParkingLot parkingLotL3;

    public ParkingManagerTest() {
        this.parkingLotL1 = new ParkingLot(1);
        this.parkingLotL2 = new ParkingLot(1);
        this.parkingLotL3 = new ParkingLot(1);
        this.graduateParkingBoy = new GraduateParkingBoy(
                Lists.newArrayList(parkingLotL2));
        this.smartParkingBoy = new SmartParkingBoy(
                Lists.newArrayList(parkingLotL3));
        this.parkingManager = new ParkingManager(Lists.newArrayList(this.graduateParkingBoy, this.smartParkingBoy), Lists.newArrayList(this.parkingLotL1));
    }

    @Test
    void should_park_to_parking_lot_l2_when_parking_given_parking_manager_has_graduate_parking_boy_a_and_smart_parking_boy_b_and_l1_parking_lot_and_parking_boy_a_has_available_space() {

        Car myCar = new Car();
        Ticket ticket = this.parkingManager.parking(myCar);

        assertEquals(myCar, this.parkingLotL2.pickup(ticket));
    }

    @Test
    void should_park_to_parking_lot_l3_when_parking_given_parking_manager_has_graduate_parking_boy_a_and_smart_parking_boy_b_and_l1_parking_lot_and_parking_boy_b_has_available_space() {

        this.parkingManager.parking(new Car());

        Car myCar = new Car();
        Ticket ticket = this.parkingManager.parking(myCar);

        assertEquals(myCar, this.parkingLotL3.pickup(ticket));
    }

    @Test
    void should_park_to_parking_lot_l1_when_parking_given_parking_manager_has_graduate_parking_boy_a_and_smart_parking_boy_b_and_l1_parking_lot_and_manager_has_available_space() {
        this.parkingManager.parking(new Car());
        this.parkingManager.parking(new Car());

        Car myCar = new Car();
        Ticket ticket = this.parkingManager.parking(myCar);

        assertEquals(myCar, this.parkingLotL1.pickup(ticket));
    }

    @Test
    void should_not_parking_when_parking_given_all_parking_lots_are_full() {
        this.parkingManager.parking(new Car());
        this.parkingManager.parking(new Car());
        this.parkingManager.parking(new Car());

        assertThrows(ParkingLotSpaceIsFull.class, () -> this.parkingManager.parking(new Car()));
    }

    @Test
    void should_pick_up_my_car_when_park_up_given_parking_manager_has_graduate_parking_boy_a_and_smart_parking_boy_b_and_l1_parking_lot_and_parking_boy_a_has_my_car() {

        Car myCar = new Car();
        Ticket ticket = this.parkingManager.parking(myCar);

        assertEquals(myCar, this.parkingManager.pickup(ticket));
    }

    @Test
    void should_raise_invalid_ticket_error_message_when_pick_up_given_invalid_ticket() {
        Ticket ticket = new Ticket();

        assertThrows(ParkingLotInvalidTicket.class, () -> this.parkingManager.pickup(ticket));
    }

    @Test
    void should_raise_invalid_ticket_error_message_when_pick_up_given_a_valid_ticket_was_used_twice() {
        Ticket ticket = this.parkingLotL1.parking(new Car());
        this.parkingManager.pickup(ticket);

        assertThrows(ParkingLotInvalidTicket.class, () -> this.parkingManager.pickup(ticket));
    }
}
