package com.example.conferenceroombooking.interval;

import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"start", "end"})
public class TimeInterval extends Interval{

    @JsonCreator
    public TimeInterval(String start, String end) throws ConferenceRoomException {
        super(start,end);
    }

}
