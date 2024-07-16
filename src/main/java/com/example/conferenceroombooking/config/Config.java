package com.example.conferenceroombooking.config;

import com.example.conferenceroombooking.room.roomFactory.ConferenceRoomFactory;
import com.example.conferenceroombooking.room.roomFactory.RoomFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    Properties properties;

    /**
     * Creates a room factory bean
     * @return a room factory
     */
    @Bean
    RoomFactory getRoomFactory() {
        return new ConferenceRoomFactory();
    }
}
