package com.example.conferenceroombooking.exception;

import com.example.conferenceroombooking.controller.model.BaseResponse;
import com.example.conferenceroombooking.controller.model.ResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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
        response.setErrors(List.of(ex.getError()));
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getError().getHttpStatusCode()).body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<BaseResponse<Object>> handleAnyException(MethodArgumentNotValidException ex) {
        BaseResponse<Object> response = BaseResponse.builder().build();
        response.setStatus(ResponseStatus.OPERATION_FAIL);
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<ConferenceRoomError> conferenceRoomErrors = allErrors.stream().map(error -> {
            ConferenceRoomError invalidValueError = ConferenceRoomError.INVALID_VALUE;
            invalidValueError.setMessage(error.getDefaultMessage());
            return invalidValueError;
        }).toList();
        response.setErrors(conferenceRoomErrors);
        log.error(ex.getMessage());
        return ResponseEntity.status(ConferenceRoomError.GENERAL_ERROR.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<BaseResponse<Object>> handleAnyException(Exception ex) {
        BaseResponse<Object> response = BaseResponse.builder().build();
        response.setStatus(ResponseStatus.OPERATION_FAIL);
        response.setErrors(List.of(ConferenceRoomError.GENERAL_ERROR));
        log.error(ex.getMessage());
        return ResponseEntity.status(ConferenceRoomError.GENERAL_ERROR.getHttpStatusCode()).body(response);
    }
}
