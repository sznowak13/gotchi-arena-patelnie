package com.codecool.gochiarena.model;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Action {
    PRIMARY_ATTACK,
    SECONDARY_ATTACK,
    DEFEND,
    EVADE;

 public static Action getRandomAction() {
        final List<Action> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        Random random = new Random();
        return VALUES.get(random.nextInt(VALUES.size()));
    }
}
