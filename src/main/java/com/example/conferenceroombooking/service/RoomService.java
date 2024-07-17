package com.example.conferenceroombooking.service;

import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.List;
import java.util.Map;

public interface RoomService {
    List<Room> getAvailableRooms() throws ConferenceRoomException;

    Room getRoomFromName(String roomName) throws ConferenceRoomException;

    void bookMeeting(Meeting meetingReq) throws ConferenceRoomException;

    void editRoomMeeting(String roomName, String meetingKey, Meeting meetingReq) throws ConferenceRoomException;

    void deleteRoomMeeting(String roomName, String meetingKey) throws ConferenceRoomException;
}
