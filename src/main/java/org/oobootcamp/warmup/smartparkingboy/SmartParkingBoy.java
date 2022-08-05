package org.oobootcamp.warmup.smartparkingboy;

import org.oobootcamp.warmup.ParkingBoy;
import org.oobootcamp.warmup.parkinglot.Car;
import org.oobootcamp.warmup.parkinglot.PackingLot;
import org.oobootcamp.warmup.parkinglot.Ticket;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoy  extends ParkingBoy {
    public SmartParkingBoy(List<PackingLot> parkingLots) {
        super((ArrayList<PackingLot>) parkingLots);
    }

    public Ticket parking(Car car) {
        int maxRestSpaceCount = 0;
        int parkingLotPosition = 0;
        for (PackingLot parkingLot: this.parkingLots) {
            if (parkingLot.restSpaceCount() > maxRestSpaceCount) {
                maxRestSpaceCount = parkingLot.restSpaceCount();
                parkingLotPosition = this.parkingLots.indexOf(parkingLot);
            }
        }
        PackingLot packingLot = this.parkingLots.get(parkingLotPosition);
        return packingLot.parking(car);
    }
}
