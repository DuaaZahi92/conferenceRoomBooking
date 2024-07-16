package com.example.conferenceroombooking.service;

import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;

import java.util.List;
import java.util.Map;

public interface RoomService {
    List<Room> getAvailableRooms();

    Map<Room,Meeting> getRoomsMeetings();

    void bookMeeting(Meeting meetingReq);

    void editRoomMeeting(Meeting meetingReq);

    void deleteRoomMeeting(Integer meetingId);
}
