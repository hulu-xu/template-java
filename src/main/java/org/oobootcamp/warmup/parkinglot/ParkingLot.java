package org.oobootcamp.warmup.parkinglot;

import org.oobootcamp.warmup.parkinglot.exception.ParkingLotInvalidTicket;
import org.oobootcamp.warmup.parkinglot.exception.ParkingLotSpaceIsFull;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;

    private final Map<Ticket, Car> ticketCarMap;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.ticketCarMap = new HashMap<>();
    }

    public Ticket parking(Car car) {
        if (!hasSpace()) {
            throw new ParkingLotSpaceIsFull();
        }

        Ticket ticket = new Ticket();
        this.ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pickup(Ticket ticket) {

        if (hasCar(ticket)) {
            return this.ticketCarMap.remove(ticket);
        }
        throw new ParkingLotInvalidTicket();
    }

    public boolean hasCar(Ticket ticket) {
        return this.ticketCarMap.containsKey(ticket);
    }


    public boolean hasSpace() {
        return this.restSpaceCount() != 0;
    }

    public int restSpaceCount() {
        return this.capacity - this.ticketCarMap.size();
    }
}
