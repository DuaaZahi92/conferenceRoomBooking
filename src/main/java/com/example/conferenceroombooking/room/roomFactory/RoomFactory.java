package com.example.conferenceroombooking.room.roomFactory;

import com.example.conferenceroombooking.room.interval.TimeInterval;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.List;

public interface RoomFactory {
    Room getRoom(String name, Integer maxCapacity, List<TimeInterval> maintainedTimeIntervals);
}
