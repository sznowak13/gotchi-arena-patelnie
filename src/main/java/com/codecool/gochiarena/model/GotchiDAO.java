package com.codecool.gochiarena.model;

import java.util.ArrayList;
import java.util.List;

public class GotchiDAO {

    private static List<Gotchi> availableGotchis = new ArrayList<>();

    static {
        availableGotchis.add(new Gotchi("Goczi1", GochiType.ROCK, 10, 10, 10, 10));
        availableGotchis.add(new Gotchi("Goczi2", GochiType.SCISSORS, 10, 10, 10 ,10));
        availableGotchis.add(new Gotchi("Goczi3", GochiType.PAPER, 10, 10, 10, 10));
    }

    public static void addGotchi(Gotchi gotchi) {
        availableGotchis.add(gotchi);
    }

    public static Gotchi getRandomGotchi() {
        return availableGotchis.get((int) (Math.random() * availableGotchis.size()));
    }

    public static List<Gotchi> getAvailableGotchis() {
        return availableGotchis;
    }
}
