package com.example.conferenceroombooking.room.rooms;

import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.room.Meeting;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "maxCapacity"})
public class ConferenceRoom implements Room {
    Integer id;
    String name;
    Integer maxCapacity;
    String location;

    RoomType type;

    List<Meeting> meetingOfTheDay;

    public ConferenceRoom(String name, Integer maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.type = RoomType.CONFERENCE;
    }

    @Override
    public String toString() {
        return "Conference Room "+ this.name + " at" + this.location + ", fits " + this.maxCapacity + " attendees";
    }

    @Override
    public Boolean isAvailable(Interval interval) {
        return null;
    }

    @Override
    public Boolean bookRoom(Interval interval) {
        return null;
    }
}
