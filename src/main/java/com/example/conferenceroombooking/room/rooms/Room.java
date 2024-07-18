package com.example.conferenceroombooking.room.rooms;

import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.interval.TimeInterval;
import com.example.conferenceroombooking.room.Meeting;
import jakarta.validation.constraints.*;

import java.util.Map;

public interface Room {

    @NotNull(message = "Room Name can't be null")
    @NotBlank(message = "Room Name can't be blank")
    @NotEmpty(message = "Room Name can't be empty")
    String getName();

    @Min(value = 1, message = "Room max capacity should be minimum of 1")
    @NotNull(message = "Room max capacity can't be null")
    Integer getMaxCapacity();

    Map<String,Meeting> getMeetingsOfTheDay();

    @Max(255)
    String getLocation();

    Boolean isAvailable(TimeInterval timeInterval) throws ConferenceRoomException;

    void bookRoom(Meeting meeting) throws ConferenceRoomException;

    void removeMeeting(String meetingKey) throws ConferenceRoomException;
}
