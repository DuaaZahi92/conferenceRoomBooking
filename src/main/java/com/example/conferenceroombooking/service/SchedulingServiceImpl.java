package com.example.conferenceroombooking.service;

import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.Attendee;
import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.interval.TimeIntervalOfFifteenMinutes;
import com.example.conferenceroombooking.room.rooms.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SchedulingServiceImpl implements SchedulingService {
    @Autowired
    RoomService roomService;

    @Scheduled(fixedRate = 60000)
    @Async
    public void run() throws ConferenceRoomException {
        log.debug("Running job every minute");
        meetingReminder();
        LocalDateTime now = LocalDateTime.now();
        now.withHour(0);
        now.withMinute(0);
        if (now.getHour() == 0 && now.getMinute() == 0) {
            log.debug("It's start of new day. clear the meetings lists in all rooms");
            clearRoomsMeetings();
        }
    }

    private void meetingReminder() throws ConferenceRoomException {
        List<Room> availableRooms = roomService.getAvailableRooms();
        for (Room availableRoom : availableRooms) {
            for (Map.Entry<String, Meeting> meetingEntry : availableRoom.getMeetingsOfTheDay().entrySet()) {
                TimeIntervalOfFifteenMinutes interval = meetingEntry.getValue().getInterval();
                String afterTenMin = LocalTime.now().withSecond(0).withNano(0).plusMinutes(10).format(interval.getDtf());
                if (interval.getStart().equals(afterTenMin)) {
                    List<Attendee> attendeeList = meetingEntry.getValue().getAttendeeList();
                    log.info("Reminding {} of meeting: {} in room: {} after 10 mins",
                            attendeeList, meetingEntry.getValue().getTitle(), availableRoom.getName());
                }
            }
        }
    }

    private void clearRoomsMeetings() throws ConferenceRoomException {
        List<Room> availableRooms = roomService.getAvailableRooms();
        for (Room availableRoom : availableRooms) {
            availableRoom.getMeetingsOfTheDay().clear();
            log.info("Cleared room {} of meetings.", availableRoom.getName());
        }
    }
}
