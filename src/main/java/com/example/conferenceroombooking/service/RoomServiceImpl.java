package com.example.conferenceroombooking.service;

import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.repository.RoomRepository;
import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;
import com.example.conferenceroombooking.room.selectionStrategy.RoomSelectionStrategy;
import com.example.conferenceroombooking.room.selectionStrategy.RoomSelectionStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Override
    public List<Room> getAvailableRooms() throws ConferenceRoomException {
        List<Room> rooms = roomRepository.getRoomList();
        return rooms;
    }

    @Override
    public void bookMeeting(Meeting meetingReq) throws ConferenceRoomException {
        RoomSelectionStrategy roomSelectionStrategy = RoomSelectionStrategyFactory.getRoomSelectionStrategy(meetingReq);
        List<Room> rooms = roomRepository.getRoomList();
        Room selectedRoom = roomSelectionStrategy.selectRoomForMeeting(rooms, meetingReq);
        selectedRoom.bookRoom(meetingReq);
    }

    @Override
    public void editRoomMeeting(Meeting meetingReq) {

    }

    @Override
    public void deleteRoomMeeting(String roomName, String meetingKey) throws ConferenceRoomException {
        List<Room> rooms = roomRepository.getRoomList();
        Room found = rooms.stream().filter(r -> r.getName().equalsIgnoreCase(roomName))
                .findFirst().orElse(null);
        if (found == null)
            throw new ConferenceRoomException(ConferenceRoomError.INVALID_VALUE, "Not able to find room with name " + roomName);
        found.getMeetingsOfTheDay().remove(meetingKey);
    }
}
