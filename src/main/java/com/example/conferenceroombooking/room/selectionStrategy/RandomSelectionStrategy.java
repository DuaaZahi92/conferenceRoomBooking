package com.example.conferenceroombooking.room.selectionStrategy;

import com.example.conferenceroombooking.room.rooms.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component("randomStrategy")
public class RandomSelectionStrategy implements SelectionStrategy<Random>{
    @Override
    public Room select(List<Room> list, Random rand) {
        List<Room> newList = new ArrayList<>(list);
        Collections.shuffle(newList, rand);
        return list.stream()
                .findFirst()
                .orElse(null);
    }
}
