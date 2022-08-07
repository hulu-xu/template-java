package org.oobootcamp.warmup;

import org.oobootcamp.warmup.parkinglot.Car;
import org.oobootcamp.warmup.parkinglot.ParkingLot;
import org.oobootcamp.warmup.parkinglot.Ticket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotInvalidTicket;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingBoy {
    protected final List<ParkingLot> parkingLots;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract Ticket parking(Car car);

    public Car pickup(Ticket ticket) {
        for (ParkingLot parkingLot: this.parkingLots) {
            if (parkingLot.hasCar(ticket)){
                return parkingLot.pickup(ticket);
            }
        }
        throw new ParkingLotInvalidTicket();
    }

    public boolean hasSpace() {
        for (ParkingLot parkingLot: this.parkingLots) {
            if (parkingLot.hasSpace()){
                return true;
            }
        }
        return  false;
    }

    public boolean hasCar(Ticket ticket) {
        for (ParkingLot parkingLot: this.parkingLots) {
            if (parkingLot.hasCar(ticket)){
                return true;
            }
        }
        return  false;
    }
}
