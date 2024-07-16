package com.example.conferenceroombooking.controller.model;


import com.example.conferenceroombooking.exception.ConferenceRoomError;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> implements Serializable {
    @JsonProperty("data")
    private T data;
    @JsonProperty("status")
    private ResponseStatus status = ResponseStatus.OPERATION_SUCCESS;
    @JsonProperty("error")
    private ConferenceRoomError error;
    @JsonProperty("message")
    private String message;
}
