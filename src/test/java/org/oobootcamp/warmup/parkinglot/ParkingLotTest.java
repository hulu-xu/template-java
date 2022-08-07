package org.oobootcamp.warmup.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotInvalidTicket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotSpaceIsFull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    private final ParkingLot parkingLot;
    private final Car car;

    public ParkingLotTest() {
        this.parkingLot = new ParkingLot(1);
        this.car = new Car();
    }

    @Test
    void should_pack_and_get_a_ticket_when_parking_given_one_seat() {
        Ticket ticket = this.parkingLot.parking(this.car);

        assertNotNull(ticket);
    }

    @Test
    void should_not_pack_when_parking_given_no_seats() {
        this.parkingLot.parking(this.car);

        assertThrows(ParkingLotSpaceIsFull.class, () -> this.parkingLot.parking(this.car));
    }

    @Test
    void should_get_a_car_when_pick_up_car_given_a_valid_ticket() {
        Ticket ticket = this.parkingLot.parking(this.car);

        Car pickCar = this.parkingLot.pickup(ticket);

        assertThat(pickCar).isEqualTo(this.car);
    }

    @Test
    void should_not_get_a_car_when_pick_up_car_given_a_valid_ticket_was_used_twice() {
        Ticket ticket = this.parkingLot.parking(this.car);

        assertThrows(ParkingLotInvalidTicket.class, () -> {
            this.parkingLot.pickup(ticket);
            this.parkingLot.pickup(ticket);
        });
    }

    @Test
    void should_not_get_a_car_when_pick_up_car_given_a_invalid_ticket() {
        Ticket ticket = new Ticket();

        assertThrows(ParkingLotInvalidTicket.class, () -> this.parkingLot.pickup(ticket));
    }


}
