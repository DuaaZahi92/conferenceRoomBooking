package com.example.conferenceroombooking.room.observer;

import com.example.conferenceroombooking.room.rooms.Room;
import jakarta.validation.constraints.NotNull;

public interface RoomObserver {
    void update(@NotNull  Room room,@NotNull String meetingKey,@NotNull String action);
}
