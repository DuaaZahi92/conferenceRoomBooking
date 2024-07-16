package com.example.conferenceroombooking.repository;

import com.example.conferenceroombooking.room.rooms.Room;

public interface RoomRepository {
    Room roomSelector(Integer attendeeNumber);

    Room getRoomByName(String name);
}
