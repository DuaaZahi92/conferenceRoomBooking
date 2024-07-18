package com.example.conferenceroombooking.room.rooms;

import com.example.conferenceroombooking.exception.ConferenceRoomErrorEnum;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.interval.TimeInterval;
import com.example.conferenceroombooking.room.Meeting;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "maxCapacity", "maintenanceIntervals"})
public class MaintainedConferenceRoom extends ConferenceRoom {
    @Getter
    List<TimeInterval> maintenanceTimeIntervals;

    public MaintainedConferenceRoom(String name, Integer maxCapacity, List<TimeInterval> maintenanceTimeIntervals) {
        super(name, maxCapacity);
        this.maintenanceTimeIntervals = maintenanceTimeIntervals;
    }

    @Override
    public String toString() {
        return "Maintained Conference Room " + this.name + ", fits " + this.maxCapacity + " attendees. \n"
                + "Maintenance Intervals are: " + this.maintenanceTimeIntervals;
    }

    @Override
    public Boolean isAvailable(TimeInterval timeInterval) {
        Boolean isAvailable = super.isAvailable(timeInterval);
        if (!isAvailable) return false;
        for (TimeInterval maintenanceTimeInterval : maintenanceTimeIntervals) {
            if (maintenanceTimeInterval.isOverlaps(timeInterval))
                return false;
        }
        return true;
    }

    @Override
    public void bookRoom(Meeting meeting) throws ConferenceRoomException {
        Boolean isAvailable = isAvailable(meeting.getInterval());
        if (!isAvailable)
            throw new ConferenceRoomException(ConferenceRoomErrorEnum.NOT_ALLOWED, "Meeting overlaps with another meeting or maintenance time");
        this.meetingsOfTheDay.put(meeting.getKey(), meeting);
    }

}
