package com.example.conferenceroombooking.room.rooms;

import com.example.conferenceroombooking.interval.Interval;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "maxCapacity"})
public class KitchenRoom implements Room {
    Integer id;
    String name;
    Integer maxCapacity;
    String location;

    RoomType type;

    public KitchenRoom(String name, Integer maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.type = RoomType.KITCHEN;
    }

    @Override
    public Boolean isAvailable(Interval interval) {
        return true;
    }

    @Override
    public Boolean bookRoom(Interval interval) {
        return false;
    }
}
