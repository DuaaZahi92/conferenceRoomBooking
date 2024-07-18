package com.example.conferenceroombooking.exception;

import lombok.Data;

@Data
public class ConferenceRoomException extends Exception {
    private ConferenceRoomErrorEnum error;

    public ConferenceRoomException(ConferenceRoomErrorEnum error, String message) {
        super(message);
        this.error = error;
    }
}
