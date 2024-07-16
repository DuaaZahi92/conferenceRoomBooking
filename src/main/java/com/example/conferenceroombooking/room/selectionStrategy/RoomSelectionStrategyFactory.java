package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.Meeting;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomSelectionStrategyFactory {
    public static RoomSelectionStrategy getRoomSelectionStrategy(Meeting meeting) throws ConferenceRoomException {
        if (meeting == null)
            throw new ConferenceRoomException(ConferenceRoomError.INVALID_VALUE, "Error while trying to find room selection strategy because meeting is null");
        if (meeting.getRoomPreference() != null) {
            return new PreferenceRoomSelectionStrategy();
        } else if (meeting.getAttendeeNumber() != null) {
            return new NumberOfAttendeesRoomSelectionStrategy();
        } else {
            log.warn("Couldn't find any suitable room selection strategy so we will choose randomly");
            return new RandomSelectionStrategy();
        }
    }
}
