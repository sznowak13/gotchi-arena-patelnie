package com.codecool.gochiarena.model;

import java.util.HashMap;
import java.util.Map;

public class StatPoints {

    private Map<String ,Integer> points = new HashMap<>();

    public StatPoints(int attackPoints, int speedPoints, int defencePoints, int staminaPoints) {
        this.points.put("Attack", attackPoints);
        this.points.put("Speed", speedPoints);
        this.points.put("Defence", defencePoints);
        this.points.put("Stamina", staminaPoints);
        this.points.put("Health", 100);
    }

    public StatPoints(Map<String, Integer> points) {
        this.points = points;
        this.points.put("Health", 100);
    }

    public int getAttackPoints() {
        return this.points.get("Attack");
    }

    public void setAttackPoints(int attackPoints) {
        this.points.put("Attack", attackPoints);
    }

    public int getSpeedPoints() {
        return this.points.get("Speed");
    }

    public void setSpeedPoints(int speedPoints) {
        this.points.put("Speed", speedPoints);
    }

    public int getDefencePoints() {
        return this.points.get("Defence");
    }

    public void setDefencePoints(int defencePoints) {
        this.points.put("Defence", defencePoints);
    }

    public int getStaminaPoints() {
        return this.points.get("Stamina");
    }

    public void setStaminaPoints(int staminaPoints) {
        this.points.put("Stamina", staminaPoints);
    }

    public int getHealthPoints() {
        return this.points.get("Health");
    }

    public void setHealthPoints(int healthPoints) {
        this.points.put("Health", healthPoints);
    }

    public void decreaseHealthPoints(double dmg) {
        int previousHP = this.points.get("Health");
        int afterDmg = previousHP - dmg < 0 ? 0 : previousHP - (int) dmg;
        this.points.put("Health", afterDmg);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Attack: %s, ", this.points.get("Attack")));
        sb.append(String.format("Defence: %s, ", this.points.get("Defence")));
        sb.append(String.format("Health: %s, ", this.points.get("Health")));
        sb.append(String.format("Stamina: %s, ", this.points.get("Stamina")));
        sb.append(String.format("Speed: %s, ", this.points.get("Speed")));
        return sb.toString();
    }
}
