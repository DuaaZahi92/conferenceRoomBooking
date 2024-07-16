package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.room.rooms.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("preferenceStrategy")
public class PreferenceSelectionStrategy implements SelectionStrategy<String>{
    @Override
    public Room select(List<Room> list, String roomName) {
        return list.stream()
                .filter(room -> room.getName().equalsIgnoreCase(roomName))
                .findFirst()
                .orElse(null);
    }
}
