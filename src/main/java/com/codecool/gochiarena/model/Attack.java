package com.codecool.gochiarena.model;

public enum Attack {
    PRIMARY(1),
    SECONDARY(0.75);

    private double damageModifier;
    private GochiType type;

    Attack(double damageModifier) {
        this.damageModifier = damageModifier;
    }

    public void setType(GochiType type) {
        this.type = type;
    }


    public Attack ofType(GochiType type) {
        this.setType(type);
        return this;
    }

}
