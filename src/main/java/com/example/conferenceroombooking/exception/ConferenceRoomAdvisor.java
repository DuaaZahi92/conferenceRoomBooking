package com.example.conferenceroombooking.exception;

import com.example.conferenceroombooking.controller.model.BaseResponse;
import com.example.conferenceroombooking.controller.model.ResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@Order(Integer.MIN_VALUE)
@ControllerAdvice
public class ConferenceRoomAdvisor {

    @Autowired
    private ObjectMapper objectMapper;


    @ExceptionHandler({ConferenceRoomException.class})
    protected ResponseEntity<BaseResponse<Object>> handleConferenceErrorException(ConferenceRoomException ex) {
        BaseResponse<Object> response = BaseResponse.builder().build();
        response.setStatus(ResponseStatus.OPERATION_FAIL);
        response.setError(ex.getError());
        response.setMessage(ex.getMessage());
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getError().getHttpStatusCode()).body(response);
    }
}
