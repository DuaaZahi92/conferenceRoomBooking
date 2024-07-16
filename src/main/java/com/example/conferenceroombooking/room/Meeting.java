package com.example.conferenceroombooking.room;

import com.example.conferenceroombooking.interval.Interval;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "interval", "attendeeNumber", "roomPreference"})
public class Meeting implements Serializable {
    Integer id;
    @NotNull(message = "Meeting interval can't be null or empty")
    private Interval interval;
    @NotNull(message = "Attendee number can't be null")
    @Min(value = 1, message = "Attendee number should have minimum of 1")
    private Integer attendeeNumber;
    private String roomPreference;
}
