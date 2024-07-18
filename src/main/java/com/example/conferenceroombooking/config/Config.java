package com.example.conferenceroombooking.config;

import com.example.conferenceroombooking.room.observer.RoomDisplay;
import com.example.conferenceroombooking.room.observer.RoomObserver;
import com.example.conferenceroombooking.room.roomFactory.ConferenceRoomFactory;
import com.example.conferenceroombooking.room.roomFactory.RoomFactory;
import com.example.conferenceroombooking.service.RoomChangeNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    Properties properties;
    @Bean
    RoomFactory getRoomFactory() {
        return new ConferenceRoomFactory();
    }

    void registerRoomDisplay(RoomChangeNotification roomChangeNotification) {
        RoomObserver roomObserver = new RoomDisplay();
        roomChangeNotification.addRoomObserver(roomObserver);
    }
}
