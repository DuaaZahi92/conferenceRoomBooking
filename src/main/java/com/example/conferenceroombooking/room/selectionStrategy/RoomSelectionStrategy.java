package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public interface RoomSelectionStrategy {

    Room selectRoomForMeeting(@NotNull @NotEmpty List<Room> list, Meeting meeting);
}
