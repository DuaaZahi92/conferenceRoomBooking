package com.example.conferenceroombooking.room.roomFactory;

import com.example.conferenceroombooking.room.rooms.Room;
import com.example.conferenceroombooking.room.rooms.RoomType;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RoomsFactory implements RoomGroupFactory {
    private Map<RoomType, List<Room>> roomsPerType;
    public RoomsFactory(@NotNull Map<RoomType, List<Room>> roomsPerType) {
        this.roomsPerType = roomsPerType;
    }

    public List<Room> getAvailableRoomsPerType(RoomType roomType) {
        return roomsPerType.get(roomType);
    }
}
