package com.codecool.gochiarena.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Gotchi {


    private static int idCounter = 0;
    private int id;
    private String name;
    private GochiType type;
    private StatPoints statPoints;
    private Action currentAction;
    private boolean ready;


    public Gotchi(String name, GochiType type, int attack, int speed, int defence, int stamina) {
        this(name, type, new StatPoints(attack, speed, defence, stamina));
    }

    public Gotchi(String name, GochiType type, Map<String, Integer> points) {
        this(name, type, new StatPoints(points));
    }

    private Gotchi(String name, GochiType type, StatPoints statPoints) {
        this.name = name;
        this.type = type;
        this.id = idCounter++;
        this.statPoints = statPoints;
    }

    public Attack getPrimaryAttack() {
        return Attack.PRIMARY;
    }

    public Attack getSecondaryAttack() {
        return Attack.SECONDARY;
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

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isReady() {
        return this.ready;
    }

    public void takeDamage(double damage) {
        double dmgAfterDefence = damage - this.getStatPoints().getDefencePoints();
        if (!(dmgAfterDefence < 0))
            this.getStatPoints().decreaseHealthPoints(dmgAfterDefence);
    }
}
