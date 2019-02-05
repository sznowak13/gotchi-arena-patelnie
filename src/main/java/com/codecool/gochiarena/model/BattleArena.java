package com.codecool.gochiarena.model;

public class BattleArena {

    private Gotchi gotchi1;
    private Gotchi gotchi2;

    public void setGotchi1(Gotchi gotchi1) {
        this.gotchi1 = gotchi1;
    }

    public void setGotchi2(Gotchi gotchi2) {
        this.gotchi2 = gotchi2;
    }

    public void battle() {
        Gotchi attacker = this.calculateAttacker();
    }

    public Gotchi calculateAttacker() {
        if (this.gotchi1.getStatPoints().getSpeedPoints() > this.gotchi2.getStatPoints().getSpeedPoints()) {
            return this.gotchi1;
        } else {
            return this.gotchi2;
        }
    }
}
