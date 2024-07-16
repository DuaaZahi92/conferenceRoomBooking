package com.example.conferenceroombooking.room.rooms;

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
}
