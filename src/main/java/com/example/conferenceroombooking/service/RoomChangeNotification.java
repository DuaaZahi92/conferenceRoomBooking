package com.example.conferenceroombooking.service;

import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.observer.RoomObserver;
import com.example.conferenceroombooking.room.rooms.Room;
import jakarta.validation.constraints.NotNull;

public interface RoomChangeNotification {
    void setRoomUpdates(@NotNull Room room, @NotNull String meetingKey, @NotNull String action);
    void addRoomObserver(@NotNull RoomObserver roomObserver);
    void removeRoomObserver(@NotNull RoomObserver roomObserver);

}
