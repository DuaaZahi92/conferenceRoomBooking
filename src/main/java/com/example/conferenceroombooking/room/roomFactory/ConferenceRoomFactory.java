package com.example.conferenceroombooking.room.roomFactory;

import com.example.conferenceroombooking.room.interval.TimeInterval;
import com.example.conferenceroombooking.room.rooms.ConferenceRoom;
import com.example.conferenceroombooking.room.rooms.MaintainedConferenceRoom;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.List;

public class ConferenceRoomFactory implements RoomFactory{

    public Room getRoom(String name, Integer maxCapacity, List<TimeInterval> maintainedTimeIntervals) {
        if (maintainedTimeIntervals != null) {
            return new MaintainedConferenceRoom(name, maxCapacity, maintainedTimeIntervals);
        }
        return new ConferenceRoom(name, maxCapacity);
    }
}
