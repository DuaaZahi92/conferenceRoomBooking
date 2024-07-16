package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomSelectionStrategy implements RoomSelectionStrategy {

    @Override
    public Room selectRoomForMeeting(List<Room> list, Meeting meeting) throws ConferenceRoomException {
        List<Room> newList = new ArrayList<>(list);
        Collections.shuffle(newList);
        Room found = list.stream()
                .findFirst()
                .orElse(null);
        if (found == null)
            throw new ConferenceRoomException(ConferenceRoomError.NOT_ALLOWED, "Can't find any room");
        return found;
    }
}
