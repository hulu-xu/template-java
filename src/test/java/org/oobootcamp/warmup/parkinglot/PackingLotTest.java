package org.oobootcamp.warmup.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotInvalidTicket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotSpaceIsFull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PackingLotTest {

    private final PackingLot packingLot;
    private final Car car;

    public PackingLotTest() {
        this.packingLot = new PackingLot(1);
        this.car = new Car();
    }

    @Test
    void should_pack_and_get_a_ticket_when_parking_given_one_seat() {
        Ticket ticket = this.packingLot.parking(this.car);

        assertNotNull(ticket);
    }

    @Test
    void should_not_pack_when_parking_given_no_seats() {
        this.packingLot.parking(this.car);

        assertThrows(ParkingLotSpaceIsFull.class, () -> this.packingLot.parking(this.car));
    }

    @Test
    void should_get_a_car_when_pick_up_car_given_a_valid_ticket() {
        Ticket ticket = this.packingLot.parking(this.car);

        Car pickCar = this.packingLot.pickup(ticket);

        assertThat(pickCar).isEqualTo(this.car);
    }

    @Test
    void should_not_get_a_car_when_pick_up_car_given_a_valid_ticket_was_used_twice() {
        Ticket ticket = this.packingLot.parking(this.car);

        assertThrows(ParkingLotInvalidTicket.class, () -> {
            this.packingLot.pickup(ticket);
            this.packingLot.pickup(ticket);
        });
    }

    @Test
    void should_not_get_a_car_when_pick_up_car_given_a_invalid_ticket() {
        Ticket ticket = new Ticket();

        assertThrows(ParkingLotInvalidTicket.class, () -> this.packingLot.pickup(ticket));
    }


}
