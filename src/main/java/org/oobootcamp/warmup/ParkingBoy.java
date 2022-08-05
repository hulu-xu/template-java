package org.oobootcamp.warmup;

import org.oobootcamp.warmup.parkinglot.Car;
import org.oobootcamp.warmup.parkinglot.PackingLot;
import org.oobootcamp.warmup.parkinglot.Ticket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotInvalidTicket;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingBoy {
    protected final List<PackingLot> parkingLots;

    public ParkingBoy(ArrayList<PackingLot> packingLots) {
        this.parkingLots = packingLots;
    }

    public abstract Ticket parking(Car car);

    public Car pickup(Ticket ticket) {
        for (PackingLot parkingLot: this.parkingLots) {
            if (parkingLot.hasTicket(ticket)){
                return parkingLot.pickup(ticket);
            }
        }
        throw new ParkingLotInvalidTicket();
    }
}
