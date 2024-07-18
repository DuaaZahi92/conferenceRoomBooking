package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.exception.ConferenceRoomErrorEnum;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.Comparator;
import java.util.List;

public class NumberOfAttendeesRoomSelectionStrategy implements RoomSelectionStrategy {
    @Override
    public Room selectRoomForMeeting(List<Room> list, Meeting meeting) throws ConferenceRoomException {
        Room found = list.stream()
                .filter(room -> room.getMaxCapacity() >= meeting.getAttendeeNumber())
                .min(Comparator.comparingInt(Room::getMaxCapacity))
                .orElse(null);
        if (found == null)
            throw new ConferenceRoomException(ConferenceRoomErrorEnum.NOT_ALLOWED, "Can't find room that fits this number of attendees");
        return found;
    }
}
