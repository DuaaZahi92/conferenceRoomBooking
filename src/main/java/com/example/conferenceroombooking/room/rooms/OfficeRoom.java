package com.example.conferenceroombooking.room.rooms;

import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.room.Meeting;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "maxCapacity"})
public class OfficeRoom implements Room {
    Integer id;
    String name;
    Integer maxCapacity;
    String location;

    RoomType type;

    public OfficeRoom(String name, Integer maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.type = RoomType.OFFICE;
    }

    @Override
    public Boolean isAvailable(Interval interval) {
        return null;
    }

    @Override
    public Boolean bookRoom(Meeting meeting) {
        return null;
    }
}
