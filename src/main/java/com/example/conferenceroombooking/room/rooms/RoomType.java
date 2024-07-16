package com.example.conferenceroombooking.room.rooms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum RoomType {
    CONFERENCE("Conference"),
    OFFICE("Office Space"),
    KITCHEN("Kitchen");

    @JsonValue
    private String label;

    private RoomType(String label) {
        this.label = label;
    }

    private static final Map<String, RoomType> labelsMap = new ConcurrentHashMap<>();

    static {
        for (RoomType e : values()) {
            labelsMap.put(e.label.toLowerCase(Locale.ROOT), e);
        }
    }

    @JsonCreator
    public static RoomType getByCode(String label) {
        if (label != null) {
            return labelsMap.get(label.toLowerCase(Locale.ROOT));
        }
        return null;
    }


    @Override
    public String toString() {
        return this.label;
    }
}
