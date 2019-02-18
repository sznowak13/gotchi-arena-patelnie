package com.codecool.gochiarena.model;

public enum GochiType {
    WATER,
    GRASS,
    FIRE;

    static {
        WATER.setup(GRASS, FIRE);
        GRASS.setup(FIRE, WATER);
        FIRE.setup(WATER, GRASS);
    }

    private void setup(GochiType vulnerability, GochiType secondary) {
        this.vulnerability = vulnerability;
        this.secondary = secondary;
    }

    private GochiType vulnerability;
    private GochiType secondary;

    public GochiType getVulnerability() {
        return vulnerability;
    }

    public GochiType getSecondaryType() {
        return secondary;
    }

}
