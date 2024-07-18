package com.example.conferenceroombooking.service;

import com.example.conferenceroombooking.exception.ConferenceRoomException;

public interface SchedulingService {
    void run() throws ConferenceRoomException;
}
