package com.example.conferenceroombooking.room.roomFactory;

import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.rooms.RoomType;

public abstract class RoomFactoryFactory {

    public static RoomFactory getRoomFactoryPerType(RoomType roomType) throws ConferenceRoomException {
        if (RoomType.CONFERENCE.equals(roomType)) {
            return new ConferenceRoomFactory();
        } else if(RoomType.KITCHEN.equals(roomType)) {
            return new KitchenFactory();
        } else if (RoomType.OFFICE.equals(roomType)) {
            return new OfficeFactory();
        }
        throw new ConferenceRoomException(ConferenceRoomError.CONFIGURATION_ERROR,"No Room Factory is configured for this roomType");
    }
}
