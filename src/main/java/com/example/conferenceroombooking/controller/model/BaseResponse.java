package com.example.conferenceroombooking.controller.model;


import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> implements Serializable {
    private T data;
    private ResponseStatus status = ResponseStatus.OPERATION_SUCCESS;
    private List<ConferenceRoomError> errors;
}