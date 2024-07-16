package com.example.conferenceroombooking.room.roomFactory;

import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.room.rooms.OfficeRoom;
import com.example.conferenceroombooking.room.rooms.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfficeFactory implements RoomFactory {
    public Room getRoom(String name, Integer maxCapacity, List<Interval> maintainedIntervals) {
        return new OfficeRoom(name, maxCapacity);
    }
}
