package com.example.conferenceroombooking.room.roomFactory;

import com.example.conferenceroombooking.room.rooms.Room;
import com.example.conferenceroombooking.room.rooms.RoomType;

import java.util.List;

public interface RoomGroupFactory {
    List<Room> getAvailableRoomsPerType(RoomType roomType);
}
