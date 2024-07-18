package com.example.conferenceroombooking.exception;

import com.example.conferenceroombooking.controller.model.BaseResponse;
import com.example.conferenceroombooking.controller.model.ResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
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
        response.setErrors(List.of(conferenceRoomErrorFromEnum(ex.getError(), ex.getMessage())));
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getError().getHttpStatusCode()).body(response);
    }

    private ConferenceRoomError conferenceRoomErrorFromEnum(ConferenceRoomErrorEnum errorEnum, String message) {
        return ConferenceRoomError.builder().message(errorEnum.getMessage()).code(errorEnum.getCode()).description(message).build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleAnyException(MethodArgumentNotValidException ex) {
        BaseResponse<Object> response = BaseResponse.builder().build();
        response.setStatus(ResponseStatus.OPERATION_FAIL);
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<ConferenceRoomError> conferenceRoomErrors = allErrors.stream()
                .map(error -> conferenceRoomErrorFromEnum(ConferenceRoomErrorEnum.INVALID_VALUE, error.getDefaultMessage())).toList();
        response.setErrors(conferenceRoomErrors);
        log.error(ex.getMessage());
        return ResponseEntity.status(ConferenceRoomErrorEnum.GENERAL_ERROR.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<BaseResponse<Object>> handleAnyException(Exception ex) {
        BaseResponse<Object> response = BaseResponse.builder().build();
        response.setStatus(ResponseStatus.OPERATION_FAIL);
        response.setErrors(List.of(conferenceRoomErrorFromEnum(ConferenceRoomErrorEnum.GENERAL_ERROR, ex.getMessage())));
        log.error(ex.getMessage());
        return ResponseEntity.status(ConferenceRoomErrorEnum.GENERAL_ERROR.getHttpStatusCode()).body(response);
    }
}
