package com.codecool.gochiarena.model;

public class StatPoints {

    private static String[] statNames = new String[] {"Attack", "Evade", "Speed", "Defence", "Stamina"};

    private int evadePoints;
    private int attackPoints;
    private int speedPoints;
    private int defencePoints;
    private int staminaPoints;
    private int healthPoints;

    public StatPoints(int[] pointsInput) {
        this.attackPoints = pointsInput[0];
        this.evadePoints = pointsInput[1];
        this.speedPoints = pointsInput[2];
        this.defencePoints = pointsInput[3];
        this.staminaPoints = pointsInput[4];
        this.healthPoints = 100;
    }

    public static String[] getStatNames() {
        return statNames;
    }

    public int getEvadePoints() {
        return evadePoints;
    }

    public void setEvadePoints(int evadePoints) {
        this.evadePoints = evadePoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getSpeedPoints() {
        return speedPoints;
    }

    public void setSpeedPoints(int speedPoints) {
        this.speedPoints = speedPoints;
    }

    public int getDefencePoints() {
        return defencePoints;
    }

    public void setDefencePoints(int defencePoints) {
        this.defencePoints = defencePoints;
    }

    public int getStaminaPoints() {
        return staminaPoints;
    }

    public void setStaminaPoints(int staminaPoints) {
        this.staminaPoints = staminaPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void decreaseHealthPoints(double dmg) {
        this.healthPoints -= dmg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Attack: %s, ", this.attackPoints));
        sb.append(String.format("Defence: %s, ", this.defencePoints));
        sb.append(String.format("Health: %s, ", this.healthPoints));
        sb.append(String.format("Stamina: %s, ", this.staminaPoints));
        sb.append(String.format("Speed: %s, ", this.speedPoints));
        sb.append(String.format("Evade: %s", this.evadePoints));
        return sb.toString();
    }
}
