package com.example.conferenceroombooking.room.roomFactory;

import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.room.rooms.ConferenceRoom;
import com.example.conferenceroombooking.room.rooms.MaintainedConferenceRoom;
import com.example.conferenceroombooking.room.rooms.Room;
import org.springframework.stereotype.Component;

import java.util.List;

public class ConferenceRoomFactory implements RoomFactory{

    public Room getRoom(String name, Integer maxCapacity, List<Interval> maintainedIntervals) {
        if (maintainedIntervals != null) {
            return new MaintainedConferenceRoom(name, maxCapacity, maintainedIntervals);
        }
        return new ConferenceRoom(name, maxCapacity);
    }
}
