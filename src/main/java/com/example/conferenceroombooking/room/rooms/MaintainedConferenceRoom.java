package com.example.conferenceroombooking.room.rooms;

import com.example.conferenceroombooking.interval.Interval;
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
}
