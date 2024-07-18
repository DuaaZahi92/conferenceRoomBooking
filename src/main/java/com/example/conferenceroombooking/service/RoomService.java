package com.example.conferenceroombooking.service;

import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.interval.TimeInterval;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAvailableRooms(TimeInterval interval) throws ConferenceRoomException;

    Room getRoomFromName(String roomName) throws ConferenceRoomException;

    Room bookMeeting(Meeting meetingReq) throws ConferenceRoomException;

    Room editMeeting(String roomName, String meetingKey, Meeting meetingReq) throws ConferenceRoomException;

    Room deleteMeeting(String roomName, String meetingKey) throws ConferenceRoomException;
}
