package com.example.conferenceroombooking.room.interval;

import com.example.conferenceroombooking.exception.ConferenceRoomErrorEnum;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class TimeInterval {
    @JsonCreator
    public TimeInterval(String start, String end) throws ConferenceRoomException {
        this.start = start;
        this.end = end;
        LocalTime startTime = LocalTime.parse(start,dtf);
        LocalTime endTime = LocalTime.parse(end,dtf);
        long minutesDifference = startTime.until(endTime, java.time.temporal.ChronoUnit.MINUTES);
        if (minutesDifference < 0)
            throw new ConferenceRoomException(ConferenceRoomErrorEnum.INVALID_VALUE, "End time has to be after start time");
    }
    @JsonIgnore
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    @JsonProperty("start")
    @NotNull(message = "Interval Start time can't be null")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$\n", message = "Interval Start time Should match the format HH:MM")
    protected String start;
    @JsonProperty("end")
    @NotNull(message = "Interval End time can't be null")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$\n", message = "Interval End time Should match the format HH:MM")
    protected String end;

    public Boolean isOverlaps(TimeInterval anotherTimeInterval) {
        LocalTime startTime1 = LocalTime.parse(this.start,dtf);
        LocalTime endTime1 = LocalTime.parse(this.end,dtf);
        LocalTime startTime2 = LocalTime.parse(anotherTimeInterval.start,dtf);
        LocalTime endTime2 = LocalTime.parse(anotherTimeInterval.end,dtf);

        // Check if interval1 overlaps interval2
        boolean interval1OverlapsInterval2 = startTime1.isBefore(endTime2) && endTime1.isAfter(startTime2);

        // Check if interval2 overlaps interval1
        boolean interval2OverlapsInterval1 = startTime2.isBefore(endTime1) && endTime2.isAfter(startTime1);

        return interval1OverlapsInterval2 || interval2OverlapsInterval1;
    }
}
