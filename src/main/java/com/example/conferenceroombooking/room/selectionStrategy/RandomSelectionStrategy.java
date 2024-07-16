package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomSelectionStrategy implements RoomSelectionStrategy {

    @Override
    public Room selectRoomForMeeting(List<Room> list, Meeting meeting) {
        List<Room> newList = new ArrayList<>(list);
        Collections.shuffle(newList);
        return list.stream()
                .findFirst()
                .orElse(null);
    }
}
