package com.example.conferenceroombooking.room.roomFactory;

import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.List;

public interface RoomFactory {
    Room getRoom(String name, Integer maxCapacity, List<Interval> maintainedIntervals);
}
