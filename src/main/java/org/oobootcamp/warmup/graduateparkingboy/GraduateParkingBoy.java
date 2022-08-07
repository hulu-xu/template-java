package org.oobootcamp.warmup.graduateparkingboy;

import org.oobootcamp.warmup.ParkingBoy;
import org.oobootcamp.warmup.parkinglot.Car;
import org.oobootcamp.warmup.parkinglot.ParkingLot;
import org.oobootcamp.warmup.parkinglot.Ticket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotSpaceIsFull;

import java.util.ArrayList;
import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {
    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super((ArrayList<ParkingLot>) parkingLots);
    }

    @Override
    public Ticket parking(Car car) {
        for (ParkingLot parkingLot: this.parkingLots) {
            if (parkingLot.hasSpace()){
                return parkingLot.parking(car);
            }
        }
        throw new ParkingLotSpaceIsFull();
    }
}
