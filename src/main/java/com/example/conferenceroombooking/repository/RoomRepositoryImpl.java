package com.example.conferenceroombooking.repository;

import com.example.conferenceroombooking.config.Properties;
import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.interval.TimeIntervalOfFifteenMinutes;
import com.example.conferenceroombooking.room.roomFactory.ConferenceRoomFactory;
import com.example.conferenceroombooking.room.roomFactory.RoomFactory;
import com.example.conferenceroombooking.room.rooms.Room;
import com.example.conferenceroombooking.room.rooms.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    Properties properties;

    @Autowired
    RoomFactory roomFactory;

    @Override
    public List<Room> getRoomList() throws ConferenceRoomException {
        List<Room> rooms = new ArrayList<>();
        Map<String, String> availableRoomsList = properties.getAvailableRooms();
        if (availableRoomsList == null)
            throw new ConferenceRoomException(ConferenceRoomError.CONFIGURATION_ERROR, "Available rooms list configuration can't be null");
        for (Map.Entry<String, String> entry : availableRoomsList.entrySet()) {
            if (entry.getValue().isEmpty())
                throw new ConferenceRoomException(ConferenceRoomError.CONFIGURATION_ERROR, "Room " + entry.getKey() + " configuration can't be null or empty");
            List<String> roomConfig = List.of(entry.getValue().split(","));
            if (roomConfig.size() < 2)
                throw new ConferenceRoomException(ConferenceRoomError.CONFIGURATION_ERROR, "Room " + entry.getKey() + " configuration should include capacity and interval");
            Integer capacity = Integer.parseInt(roomConfig.get(0));

            List<Interval> maintenanceWindow = new ArrayList<>();
            try {
                String[] intervals = roomConfig.get(2).split("|");
                for (String interval : intervals) {
                    String[] startEnd = interval.split("-");
                    String start = startEnd[0];
                    String end = startEnd[1];
                    maintenanceWindow.add(new TimeIntervalOfFifteenMinutes(start, end));
                }
            } catch (Exception e) {
                throw new ConferenceRoomException(ConferenceRoomError.CONFIGURATION_ERROR, "Room " + entry.getKey() + " has error while parsing maintenance intervals: " + e);
            }
            Room room = roomFactory.getRoom(entry.getKey(), capacity, maintenanceWindow);
            rooms.add(room);
        }
        return rooms;
    }
}
