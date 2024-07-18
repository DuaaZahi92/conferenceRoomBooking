package com.example.conferenceroombooking.room.observer;

import com.example.conferenceroombooking.room.rooms.Room;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomDisplay implements RoomObserver {
    @Override
    public void update(Room room, String meetingKey, String action) {
        log.info("Room {} has a meeting update at {}, action: {}", room.getName(), meetingKey, action);
    }
}
