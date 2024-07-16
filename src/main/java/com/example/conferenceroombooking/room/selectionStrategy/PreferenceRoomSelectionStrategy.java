package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.List;

public class PreferenceRoomSelectionStrategy implements RoomSelectionStrategy {
    @Override
    public Room selectRoomForMeeting(List<Room> list, Meeting meeting) {
        return list.stream()
                .filter(room -> room.getName().equalsIgnoreCase(meeting.getRoomPreference()))
                .findFirst()
                .orElse(null);
    }
}
