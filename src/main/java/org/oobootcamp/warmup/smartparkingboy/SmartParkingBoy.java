package org.oobootcamp.warmup.smartparkingboy;

import org.oobootcamp.warmup.ParkingBoy;
import org.oobootcamp.warmup.parkinglot.Car;
import org.oobootcamp.warmup.parkinglot.ParkingLot;
import org.oobootcamp.warmup.parkinglot.Ticket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotSpaceIsFull;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoy  extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super((ArrayList<ParkingLot>) parkingLots);
    }

    @Override
    public Ticket parking(Car car) {
        int maxRestSpaceCount = 0;
        ParkingLot availableParkingLot = null;
        for (ParkingLot parkingLot: this.parkingLots) {
            if (parkingLot.restSpaceCount() > maxRestSpaceCount) {
                maxRestSpaceCount = parkingLot.restSpaceCount();
                availableParkingLot = parkingLot;
            }
        }

        if(availableParkingLot == null) {
            throw new ParkingLotSpaceIsFull();
        }
        return availableParkingLot.parking(car);
    }
}
