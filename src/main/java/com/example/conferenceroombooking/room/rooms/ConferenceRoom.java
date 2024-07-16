package com.example.conferenceroombooking.room.rooms;

import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.room.Meeting;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "maxCapacity"})
public class ConferenceRoom implements Room {
    Integer id;
    String name;
    Integer maxCapacity;
    String location;
    Map<String,Meeting> meetingsOfTheDay = new ConcurrentHashMap<>();

    public ConferenceRoom(String name, Integer maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "Conference Room "+ this.name + " at" + this.location + ", fits " + this.maxCapacity + " attendees.\n Today's meetings: " + this.meetingsOfTheDay;
    }

    @Override
    public Boolean isAvailable(Interval interval) {
        Map.Entry<String, Meeting> found = meetingsOfTheDay.entrySet().stream()
                .filter(entry -> entry.getValue().getInterval().isOverlaps(interval))
                .findFirst()
                .orElse(null);
        return found == null;
    }

    @Override
    public void bookRoom(Meeting meeting) throws ConferenceRoomException {
        Boolean isAvailable = isAvailable(meeting.getInterval());
        if (!isAvailable)
            throw new ConferenceRoomException(ConferenceRoomError.NOT_ALLOWED, "Meeting overlaps with another meeting");
        this.getMeetingsOfTheDay().put(meeting.getKey(), meeting);
    }

}
