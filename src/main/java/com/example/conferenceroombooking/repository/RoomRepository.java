package com.example.conferenceroombooking.repository;

import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.List;

public interface RoomRepository {
    List<Room> getRoomList() throws ConferenceRoomException;
}
