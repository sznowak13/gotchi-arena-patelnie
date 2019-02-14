package com.codecool.gochiarena.model;

import java.util.HashMap;
import java.util.Map;

public class StatPoints {

    private Map<String ,Integer> points = new HashMap<>();

    public StatPoints(int attackPoints, int speedPoints, int defencePoints, int staminaPoints) {
        this.points.put("attack", attackPoints);
        this.points.put("speed", speedPoints);
        this.points.put("defence", defencePoints);
        this.points.put("stamina", staminaPoints);
        this.points.put("health", 100);
    }

    public int getAttackPoints() {
        return this.points.get("attack");
    }

    public void setAttackPoints(int attackPoints) {
        this.points.put("attack", attackPoints);
    }

    public int getSpeedPoints() {
        return this.points.get("speed");
    }

    public void setSpeedPoints(int speedPoints) {
        this.points.put("speed", speedPoints);
    }

    public int getDefencePoints() {
        return this.points.get("defence");
    }

    public void setDefencePoints(int defencePoints) {
        this.points.put("defence", defencePoints);
    }

    public int getStaminaPoints() {
        return this.points.get("stamina");
    }

    public void setStaminaPoints(int staminaPoints) {
        this.points.put("stamina", staminaPoints);
    }

    public int getHealthPoints() {
        return this.points.get("health");
    }

    public void setHealthPoints(int healthPoints) {
        this.points.put("health", healthPoints);
    }

    public void decreaseHealthPoints(double dmg) {
        int previousHP = this.points.get("health");
        int afterDmg = previousHP - dmg < 0 ? 0 : previousHP - (int) dmg;
        this.points.put("health", afterDmg);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Attack: %s, ", this.points.get("attack")));
        sb.append(String.format("Defence: %s, ", this.points.get("defence")));
        sb.append(String.format("Health: %s, ", this.points.get("health")));
        sb.append(String.format("Stamina: %s, ", this.points.get("stamina")));
        sb.append(String.format("Speed: %s, ", this.points.get("speed")));
        return sb.toString();
    }
}
