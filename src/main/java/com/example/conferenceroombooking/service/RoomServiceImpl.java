package com.example.conferenceroombooking.service;

import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.roomFactory.RoomFactory;
import com.example.conferenceroombooking.room.roomFactory.RoomGroupFactory;
import com.example.conferenceroombooking.room.rooms.Room;
import com.example.conferenceroombooking.room.rooms.RoomType;
import com.example.conferenceroombooking.room.selectionStrategy.NumberOfAttendeesSelectionStrategy;
import com.example.conferenceroombooking.room.selectionStrategy.PreferenceSelectionStrategy;
import com.example.conferenceroombooking.room.selectionStrategy.SelectionStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomGroupFactory factory;

    @Autowired
    SelectionStrategy selectionStrategy;
    @Override
    public List<Room> getAvailableRooms() {
        // builder pattern to create room beans
        List<Room> rooms = factory.getAvailableRoomsPerType(RoomType.CONFERENCE);
        return rooms;
    }

    @Override
    public Map<Room, Meeting> getRoomsMeetings() {
        return null;
    }

    @Override
    public void bookMeeting(Meeting meetingReq) {
        List<Room> rooms = factory.getAvailableRoomsPerType(RoomType.CONFERENCE);
        if (meetingReq.getRoomPreference() != null) {
            selectionStrategy = new PreferenceSelectionStrategy();
        } else {
            selectionStrategy = new NumberOfAttendeesSelectionStrategy();
        }
        Room selectedRoom = selectionStrategy.select(rooms, meetingReq.getAttendeeNumber());
        selectedRoom.bookRoom(meetingReq);
    }

    @Override
    public void editRoomMeeting(Meeting meetingReq) {

    }

    @Override
    public void deleteRoomMeeting(Integer meetingId) {

    }
}
