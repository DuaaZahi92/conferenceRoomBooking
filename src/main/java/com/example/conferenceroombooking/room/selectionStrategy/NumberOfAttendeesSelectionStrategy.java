package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.room.rooms.Room;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("numberOfAttendeesStrategy")
public class NumberOfAttendeesSelectionStrategy implements SelectionStrategy<Integer>{
    @Override
    public Room select(List<Room> list, Integer numberOfAttendees) {
        return list.stream()
                .filter(room -> room.getMaxCapacity() >= numberOfAttendees)
                .min(Comparator.comparingInt(Room::getMaxCapacity))
                .orElse(null);
    }

}
