package com.codecool.gochiarena.model;

public enum GochiType {
    ROCK,
    PAPER,
    SCISSORS;

    public GochiType getVulnerability() {
        if (this.equals(GochiType.ROCK)) {
            return GochiType.PAPER;
        } else if (this.equals(GochiType.SCISSORS)) {
            return GochiType.ROCK;
        } else {
            return GochiType.SCISSORS;
        }
    }

    public GochiType getSecondaryType() {
        if (this.equals(GochiType.ROCK)) {
            return GochiType.SCISSORS;
        } else if (this.equals(GochiType.SCISSORS)) {
            return GochiType.PAPER;
        } else {
            return GochiType.ROCK;
        }
    }

}
