package com.example.conferenceroombooking.interval;

import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"start", "end"})
public class TimeIntervalOfFifteenMinutes implements Interval {
    Integer id;
    String start;
    String end;

    public TimeIntervalOfFifteenMinutes(String start, String end) throws ConferenceRoomException {
        this.start = start;
        this.end = end;
        LocalTime startTime = LocalTime.parse(start);
        LocalTime endTime = LocalTime.parse(end);
        long minutesDifference = startTime.until(endTime, java.time.temporal.ChronoUnit.MINUTES);
        if (minutesDifference % 15 != 0)
            throw new ConferenceRoomException(ConferenceRoomError.INVALID_VALUE, "End time has to be multiples of 15 minutes of start time");
    }

}
