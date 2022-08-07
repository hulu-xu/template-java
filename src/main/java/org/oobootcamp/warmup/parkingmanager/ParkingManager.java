package org.oobootcamp.warmup.parkingmanager;

import org.oobootcamp.warmup.ParkingBoy;
import org.oobootcamp.warmup.parkinglot.Car;
import org.oobootcamp.warmup.parkinglot.ParkingLot;
import org.oobootcamp.warmup.parkinglot.Ticket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotInvalidTicket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotSpaceIsFull;

import java.util.ArrayList;

public class ParkingManager extends ParkingBoy {
    private final ArrayList<ParkingBoy> parkingBoys;
    public ParkingManager(ArrayList<ParkingBoy> parkingBoys, ArrayList<ParkingLot> parkingLots) {
        super((ArrayList<ParkingLot>) parkingLots);
        this.parkingBoys = parkingBoys;
    }

    @Override
    public Ticket parking(Car car) {
        for (ParkingBoy parkingBoy: this.parkingBoys) {
            if (parkingBoy.hasSpace()) {
                return  parkingBoy.parking(car);
            }
        }

        for (ParkingLot parkingLot: this.parkingLots) {
            if (parkingLot.hasSpace()){
                return parkingLot.parking(car);
            }
        }

        throw new ParkingLotSpaceIsFull();
    }

    @Override
    public Car pickup(Ticket ticket) {
        for (ParkingBoy parkingBoy: this.parkingBoys) {
            if (parkingBoy.hasCar(ticket)) {
                return  parkingBoy.pickup(ticket);
            }
        }

        for (ParkingLot parkingLot: this.parkingLots) {
            if (parkingLot.hasCar(ticket)){
                return parkingLot.pickup(ticket);
            }
        }
        throw new ParkingLotInvalidTicket();
    }
}
