package com.example.conferenceroombooking.controller;

import com.example.conferenceroombooking.controller.model.BaseResponse;
import com.example.conferenceroombooking.exception.ConferenceRoomException;
import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.interval.TimeInterval;
import com.example.conferenceroombooking.room.rooms.Room;
import com.example.conferenceroombooking.service.RoomService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("${config.api-prefix}/room")
@Validated
@Tag(name = "Room Controller", description = "This api is responsible to create meetings in conference rooms")
public class RoomController extends BaseController {

    @Autowired
    private RoomService roomService;


    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<BaseResponse> getRooms(@RequestHeader HttpHeaders headers,
                                                 @RequestParam(value = "interval",required = false) @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]_([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Interval Start time Should match the format HH:MM_HH:MM") String interval) throws ConferenceRoomException {
        TimeInterval timeInterval = null;
        if (interval != null) {
            String start = interval.split("_")[0];
            String end = interval.split("_")[1];
            timeInterval = new TimeInterval(start,end);
        }
        List<Room> availableRooms = roomService.getAvailableRooms(timeInterval);
        return ResponseEntity.ok(BaseResponse.builder().data(availableRooms).build());
    }

    @GetMapping("/{roomName}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<BaseResponse> getRoom(@RequestHeader HttpHeaders headers, @PathVariable String roomName) throws ConferenceRoomException {
        return ResponseEntity.ok(BaseResponse.builder().data(roomService.getRoomFromName(roomName)).build());
    }

    @PostMapping("/meeting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<BaseResponse> bookRoom(@RequestHeader HttpHeaders headers, @NotNull @Valid @RequestBody Meeting meetingReq) throws ConferenceRoomException {
        Room booked = roomService.bookMeeting(meetingReq);
        return ResponseEntity.ok(BaseResponse.builder().data(booked).build());
    }

    @PutMapping("/{roomName}/meeting/{meetingKey}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<BaseResponse> editRoomMeeting(@RequestHeader HttpHeaders headers, @PathVariable String roomName, @PathVariable String meetingKey, @NotNull @Valid @RequestBody Meeting meetingReq) throws ConferenceRoomException {
        Room modified = roomService.editMeeting(roomName, meetingKey, meetingReq);
        return ResponseEntity.ok(BaseResponse.builder().data(modified).build());
    }

    @DeleteMapping("/{roomName}/meeting/{meetingKey}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<BaseResponse> deleteMeeting(@RequestHeader HttpHeaders headers, @PathVariable String roomName, @PathVariable String meetingKey) throws ConferenceRoomException {
        Room modified = roomService.deleteMeeting(roomName, meetingKey);
        return ResponseEntity.ok(BaseResponse.builder().data(modified).build());
    }

}
