package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.room.rooms.Room;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * @param <T> Items to select from
 * @param <C> Criteria how to choose
 */
public interface SelectionStrategy<C> {

    Room select(@NotNull @NotEmpty List<Room> list, C criteria);
}
