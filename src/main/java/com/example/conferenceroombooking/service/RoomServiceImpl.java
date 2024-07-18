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

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomChangeNotification roomChangeNotification;

    @Override
    public List<Room> getAvailableRooms() throws ConferenceRoomException {
        List<Room> rooms = roomRepository.getRoomList();
        return rooms;
    }

    @Override
    public Room getRoomFromName(String roomName) throws ConferenceRoomException {
        List<Room> rooms = roomRepository.getRoomList();
        Room found = rooms.stream().filter(r -> r.getName().equalsIgnoreCase(roomName))
                .findFirst().orElse(null);
        if (found == null)
            throw new ConferenceRoomException(ConferenceRoomError.INVALID_VALUE, "Not able to find room with name " + roomName);
        return found;
    }

    @Override
    public void bookMeeting(Meeting meetingReq) throws ConferenceRoomException {
        RoomSelectionStrategy roomSelectionStrategy = RoomSelectionStrategyFactory.getRoomSelectionStrategy(meetingReq);
        List<Room> rooms = roomRepository.getRoomList();
        Room selectedRoom = roomSelectionStrategy.selectRoomForMeeting(rooms, meetingReq);
        selectedRoom.bookRoom(meetingReq);
        roomChangeNotification.setRoomUpdates(selectedRoom,meetingReq.getKey(),"bookMeeting");
    }

    @Override
    public void editMeeting(String roomName, String meetingKey, Meeting meetingReq) throws ConferenceRoomException {
        Room found = getRoomFromName(roomName);
        // book the new meeting
        bookMeeting(meetingReq);
        // remove the old meeting
        deleteMeeting(found.getName(), meetingReq.getKey());
    }

    @Override
    public void deleteMeeting(String roomName, String meetingKey) throws ConferenceRoomException {
        Room found = getRoomFromName(roomName);
        found.removeMeeting(meetingKey);
        roomChangeNotification.setRoomUpdates(found,meetingKey,"deleteMeeting");
    }
}
