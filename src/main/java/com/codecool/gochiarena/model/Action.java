package com.codecool.gochiarena.model;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Action {
    PRIMARY_ATTACK,
    SECONDARY_ATTACK,
    DEFEND,
    EVADE;

 public static Object getRandomAction() {
        final List<Action> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        Random random = new Random();
        return VALUES.get(random.nextInt(VALUES.size()));
    }
}
