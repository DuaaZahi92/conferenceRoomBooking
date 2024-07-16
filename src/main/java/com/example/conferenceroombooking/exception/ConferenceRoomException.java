package com.example.conferenceroombooking.exception;

import lombok.Data;

@Data
public class ConferenceRoomException extends Exception {
    private ConferenceRoomError error;

    public ConferenceRoomException(ConferenceRoomError error, String message) {
        super(message);
        this.error = error;
    }
}
