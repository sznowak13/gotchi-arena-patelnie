package com.codecool.gochiarena.model;

public enum Attack {
    PRIMARY(1),
    SECONDARY(0.75);

    private double damageModifier;

    Attack(double damageModifier) {
        this.damageModifier = damageModifier;
    }

    public double getModifier() {
        return damageModifier;
    }
}
