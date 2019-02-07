package com.codecool.gochiarena.model;

import java.util.ArrayList;
import java.util.List;

public class Gotchi {

    private static List<Gotchi> availableGotchis = new ArrayList<>();
    private static int idCounter = 0;
    private int id;
    private String name;
    private GochiType type;
    private StatPoints statPoints;
    private Attack primaryAttack;
    private Attack secondaryAttack;
    private Action currentAction;

    static {
        availableGotchis.add(new Gotchi("Goczi1", GochiType.ROCK, new int[] {10, 10, 10, 10, 10, 10}));
        availableGotchis.add(new Gotchi("Goczi2", GochiType.SCISSORS, new int[] {10, 10, 10, 10, 10, 10}));
        availableGotchis.add(new Gotchi("Goczi3", GochiType.PAPER, new int[] {10, 10, 10, 10, 10, 10}));
    }

    public Gotchi(String name, GochiType type, int[] statPoints) {
        this.name = name;
        this.type = type;
        this.statPoints = new StatPoints(statPoints);
        this.id = idCounter++;
        this.primaryAttack = Attack.PRIMARY.ofType(type);
        this.secondaryAttack = Attack.SECONDARY.ofType(type.getSecondaryType());
        secondaryAttack.setType(type.getSecondaryType());
    }

    public static void addGotchi(Gotchi gotchi) {
        availableGotchis.add(gotchi);
    }

    public static List<Gotchi> getAvailableGotchis() {
        return availableGotchis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GochiType getType() {
        return type;
    }

    public void setType(GochiType type) {
        this.type = type;
    }

    public StatPoints getStatPoints() {
        return statPoints;
    }

    public void setStatPoints(StatPoints statPoints) {
        this.statPoints = statPoints;
    }

    public Attack getPrimaryAttack() {
        return primaryAttack;
    }

    public void setPrimaryAttack(Attack primaryAttack) {
        this.primaryAttack = primaryAttack;
    }

    public Attack getSecondaryAttack() {
        return secondaryAttack;
    }

    public void setSecondaryAttack(Attack secondaryAttack) {
        this.secondaryAttack = secondaryAttack;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    @Override
    public String toString() {
        return String.format("Gotchi %s of type %s, %s.", this.name, this.type, this.statPoints);
    }
}
