package com.example.conferenceroombooking.room.rooms;

import com.example.conferenceroombooking.exception.ConferenceRoomErrorEnum;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.interval.TimeInterval;
import com.example.conferenceroombooking.room.Meeting;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "maxCapacity"})
public class ConferenceRoom implements Room {
    @Getter
    String name;
    @Getter
    Integer maxCapacity;
    @Getter
    String location;
    @Getter
    protected Map<String,Meeting> meetingsOfTheDay = new ConcurrentSkipListMap<>();

    public ConferenceRoom(String name, Integer maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "Conference Room "+ this.name + " at" + this.location + ", fits " + this.maxCapacity + " attendees.\n Today's meetings: " + this.meetingsOfTheDay;
    }

    @Override
    public Boolean isAvailable(TimeInterval timeInterval) {
        Map.Entry<String, Meeting> found = meetingsOfTheDay.entrySet().stream()
                .filter(entry -> entry.getValue().getInterval().isOverlaps(timeInterval))
                .findFirst()
                .orElse(null);
        return found == null;
    }

    @Override
    public void bookRoom(Meeting meeting) throws ConferenceRoomException {
        Boolean isAvailable = isAvailable(meeting.getInterval());
        if (!isAvailable)
            throw new ConferenceRoomException(ConferenceRoomErrorEnum.NOT_ALLOWED, "Meeting overlaps with another meeting");
        Meeting existingBooking = this.meetingsOfTheDay.putIfAbsent(meeting.getKey(), meeting);
        if (existingBooking != null) {
            log.warn("There is already an existing booking: {} in this slot", existingBooking.getKey());
            throw new ConferenceRoomException(ConferenceRoomErrorEnum.NOT_ALLOWED, "Meeting overlaps with another meeting");
        }
    }

    @Override
    public void removeMeeting(String meetingKey) throws ConferenceRoomException {
        Meeting removed = this.meetingsOfTheDay.remove(meetingKey);
        if (removed == null)
            throw new ConferenceRoomException(ConferenceRoomErrorEnum.INVALID_VALUE, "Meeting with key " + meetingKey + " doesn't exist in room: " + this.name);
    }

}
