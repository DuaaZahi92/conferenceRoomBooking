package com.example.conferenceroombooking.room;

import com.example.conferenceroombooking.interval.Interval;
import com.example.conferenceroombooking.interval.TimeIntervalOfFifteenMinutes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"key", "interval", "attendeeNumber", "roomPreference", "title", "description"})
public class Meeting implements Serializable {
    @JsonProperty("key")
    private String key;
    @JsonProperty("interval")
    @NotNull(message = "Meeting interval can't be null or empty")
    private TimeIntervalOfFifteenMinutes interval;
    @JsonProperty("attendeeNumber")
    @NotNull(message = "Attendee number can't be null")
    @Min(value = 1, message = "Attendee number should have minimum of 1")
    private Integer attendeeNumber;
    @JsonProperty("roomPreference")
    private String roomPreference;
    @JsonProperty("title")
    @NotNull(message = "Meeting title can't be null")
    private String title;
    @JsonProperty("description")
    private String description;

    public String getKey() {
        return this.key = this.interval.getStart() + "_" + this.interval.getEnd();
    }
}
