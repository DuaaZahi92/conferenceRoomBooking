package com.example.conferenceroombooking.interval;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalTime;

public interface Interval {
    @NotNull(message = "Interval Start time can't be null")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$\n", message = "Interval Start time Should match the format HH:MM")
    String getStart();
    @NotNull(message = "Interval End time can't be null")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$\n", message = "Interval End time Should match the format HH:MM")
    String getEnd();
}
