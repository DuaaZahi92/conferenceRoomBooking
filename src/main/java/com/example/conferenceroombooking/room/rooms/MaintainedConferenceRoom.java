package com.example.conferenceroombooking.room.rooms;

import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.room.Meeting;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "maxCapacity", "maintenanceIntervals"})
public class MaintainedConferenceRoom extends ConferenceRoom {
    List<Interval> maintenanceIntervals;

    public MaintainedConferenceRoom(String name, Integer maxCapacity, List<Interval> maintenanceIntervals) {
        super(name, maxCapacity);
        this.maintenanceIntervals = maintenanceIntervals;
    }

    @Override
    public String toString() {
        return "Maintained Conference Room " + this.name + ", fits " + this.maxCapacity + " attendees. \n"
                + "Maintenance Intervals are: " + this.maintenanceIntervals;
    }

    @Override
    public Boolean isAvailable(Interval interval) {
        Boolean isAvailable = super.isAvailable(interval);
        if (!isAvailable) return false;
        for (Interval maintenanceInterval : maintenanceIntervals) {
            if (maintenanceInterval.isOverlaps(interval))
                return false;
        }
        return true;
    }

    @Override
    public void bookRoom(Meeting meeting) throws ConferenceRoomException {
        Boolean isAvailable = isAvailable(meeting.getInterval());
        if (!isAvailable)
            throw new ConferenceRoomException(ConferenceRoomError.NOT_ALLOWED, "Meeting overlaps with another meeting or maintenance time");
        this.getMeetingsOfTheDay().put(meeting.getKey(), meeting);
    }

}
