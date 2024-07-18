package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.exception.ConferenceRoomErrorEnum;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.List;

public class PreferenceRoomSelectionStrategy implements RoomSelectionStrategy {
    @Override
    public Room selectRoomForMeeting(List<Room> list, Meeting meeting) throws ConferenceRoomException {
        Room found = list.stream()
                .filter(room -> room.getName().equalsIgnoreCase(meeting.getRoomPreference()))
                .findFirst()
                .orElse(null);
        if (found == null)
            throw new ConferenceRoomException(ConferenceRoomErrorEnum.NOT_ALLOWED, "Can't find the preferred room");
        return found;
    }
}
