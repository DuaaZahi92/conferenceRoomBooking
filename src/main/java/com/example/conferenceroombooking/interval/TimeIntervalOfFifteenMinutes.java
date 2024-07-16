package com.example.conferenceroombooking.interval;

import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"start", "end"})
public class TimeIntervalOfFifteenMinutes extends TimeInterval {

    @JsonCreator
    public TimeIntervalOfFifteenMinutes(@JsonProperty("start") String start, @JsonProperty("end") String end) throws ConferenceRoomException {
        super(start,end);
        this.start = start;
        this.end = end;
        validateTimeInterval(start, end);
    }

    private void validateTimeInterval(String start, String end) throws ConferenceRoomException {
        LocalTime startTime = LocalTime.parse(start,dtf);
        LocalTime endTime = LocalTime.parse(end,dtf);
        long minutesDifference = startTime.until(endTime, java.time.temporal.ChronoUnit.MINUTES);
        //TODO not working
        if (minutesDifference % 15 != 0)
            throw new ConferenceRoomException(ConferenceRoomError.INVALID_VALUE, "End time has to be multiples of 15 minutes of start time");
    }

}
