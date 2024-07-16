package com.example.conferenceroombooking.interval;

import com.example.conferenceroombooking.room.rooms.RoomType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"start", "end"})
public class TimeInterval implements Interval{
    Integer id;
    String start;
    String end;

    public TimeInterval(String start, String end) {
        this.start = start;
        this.end = end;
    }
}
