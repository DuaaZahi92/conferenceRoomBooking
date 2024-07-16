package com.example.conferenceroombooking.controller;

import com.example.conferenceroombooking.controller.model.BaseResponse;
import com.example.conferenceroombooking.room.Meeting;
import com.example.conferenceroombooking.room.rooms.Room;
import com.example.conferenceroombooking.service.RoomService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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

    private RoomService roomService;


    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<BaseResponse> getRooms(@RequestHeader HttpHeaders headers) {
        List<Room> availableRooms = roomService.getAvailableRooms();
        return ResponseEntity.ok(BaseResponse.builder().data(availableRooms).build());
    }

    @PostMapping("/meeting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<BaseResponse> bookRoom(@RequestHeader HttpHeaders headers, Meeting meetingReq) {
        roomService.bookMeeting(meetingReq);
        return ResponseEntity.ok(BaseResponse.builder().build());
    }

    @PutMapping("/meeting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<BaseResponse> editRoomMeeting(@RequestHeader HttpHeaders headers, Meeting meetingReq) {
        roomService.editRoomMeeting(meetingReq);
        return ResponseEntity.ok(BaseResponse.builder().build());
    }

    @DeleteMapping("/meeting/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<BaseResponse> deleteMeeting(@RequestHeader HttpHeaders headers, @PathVariable Integer id) {
        roomService.deleteRoomMeeting(id);
        return ResponseEntity.ok(BaseResponse.builder().build());
    }

}
