package com.example.conferenceroombooking.service;

import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.observer.RoomObserver;
import com.example.conferenceroombooking.room.rooms.Room;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomChangeNotificationImpl implements RoomChangeNotification {
    private List<RoomObserver> roomObserverList;

    public RoomChangeNotificationImpl() {
        roomObserverList = new ArrayList<>();
    }
    @Override
    public void setRoomUpdates(Room room, String meetingKey, String action) {
        for (RoomObserver roomObserver : roomObserverList) {
            roomObserver.update(room,meetingKey,action);
        }
    }

    @Override
    public void addRoomObserver(RoomObserver roomObserver) {
        this.roomObserverList.add(roomObserver);
    }

    @Override
    public void removeRoomObserver(RoomObserver roomObserver) {
        this.roomObserverList.remove(roomObserver);
    }
}
