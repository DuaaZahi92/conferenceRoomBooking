package com.example.conferenceroombooking.config;

import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.interval.TimeIntervalOfFifteenMinutes;
import com.example.conferenceroombooking.room.roomFactory.RoomFactoryFactory;
import com.example.conferenceroombooking.room.roomFactory.RoomsFactory;
import com.example.conferenceroombooking.room.roomFactory.RoomFactory;
import com.example.conferenceroombooking.room.rooms.Room;
import com.example.conferenceroombooking.room.rooms.RoomType;
import com.example.conferenceroombooking.room.selectionStrategy.NumberOfAttendeesSelectionStrategy;
import com.example.conferenceroombooking.room.selectionStrategy.SelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class Config {

    @Autowired
    Properties properties;

    SelectionStrategy getSelectionStrategy() {
        return new NumberOfAttendeesSelectionStrategy();
    }

    /**
     * Creates a room factory bean
     * @return a room factory
     */
    RoomsFactory getRoomGroupFactory() throws ConferenceRoomException {
        Map<String, String> availableRoomsList = properties.getAvailableRooms();
        if (availableRoomsList == null)
            throw new ConferenceRoomException(ConferenceRoomError.CONFIGURATION_ERROR, "Available rooms list configuration can't be null");
        Map<RoomType, List<Room>> roomsPerType = new ConcurrentHashMap<>();
        for (Map.Entry<String, String> entry : availableRoomsList.entrySet()) {
            if (entry.getValue().isEmpty())
                throw new ConferenceRoomException(ConferenceRoomError.CONFIGURATION_ERROR, "Room "+entry.getKey()+" configuration can't be null or empty");
            List<String> roomConfig = List.of(entry.getValue().split(","));
            if (roomConfig.size() < 2)
                throw new ConferenceRoomException(ConferenceRoomError.CONFIGURATION_ERROR, "Room "+entry.getKey()+" configuration should include capacity and room type");
            Integer capacity = Integer.parseInt(roomConfig.get(0));
            RoomType roomType = RoomType.getByCode(roomConfig.get(1));
            roomsPerType.computeIfAbsent(roomType, k -> new ArrayList<>());
            RoomFactory roomFactory = RoomFactoryFactory.getRoomFactoryPerType(roomType);

            List<Interval> maintenanceWindow = new ArrayList<>();
            if (roomConfig.size() == 3) {
                try {
                    String[] intervals = roomConfig.get(2).split("|");
                    for (String interval : intervals) {
                        String[] startEnd = interval.split("-");
                        String start = startEnd[0];
                        String end = startEnd[1];
                        maintenanceWindow.add(new TimeIntervalOfFifteenMinutes(start,end));
                    }
                } catch (Exception e) {
                    throw new ConferenceRoomException(ConferenceRoomError.CONFIGURATION_ERROR, "Room "+entry.getKey()+" has error while parsing maintenance intervals: " + e);
                }
            }

            roomsPerType.get(roomType).add(roomFactory.getRoom(entry.getKey(),capacity,maintenanceWindow));
        }
        return new RoomsFactory(roomsPerType);
    }
}
