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
    private boolean ready;

    static {
        availableGotchis.add(new Gotchi("Goczi1", GochiType.ROCK, new StatPoints(new int[] {50, 10, 10, 10, 10, 10})));
        availableGotchis.add(new Gotchi("Goczi2", GochiType.SCISSORS, new StatPoints(new int[] {30, 10, 10, 10, 10, 10})));
        availableGotchis.add(new Gotchi("Goczi3", GochiType.PAPER, new StatPoints(new int[] {20, 10, 10, 10, 10, 10})));
    }

    public Gotchi(String name, GochiType type, StatPoints statPoints) {
        this.name = name;
        this.type = type;
        this.statPoints = statPoints;
        this.id = idCounter++;
        this.primaryAttack = Attack.PRIMARY;
        this.secondaryAttack = Attack.SECONDARY;
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

    public void setReady(boolean b) {
        this.ready = true;
    }

    public boolean isReady() {
        return this.ready;
    }

    public void takeDamage(double damage) {
        this.getStatPoints().decreaseHealthPoints(damage - this.getStatPoints().getDefencePoints());
    }
}
