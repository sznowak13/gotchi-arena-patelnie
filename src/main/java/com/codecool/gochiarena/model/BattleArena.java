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

    public String battle() {
        Action action1 = gotchi1.getCurrentAction();
        Action action2 = gotchi2.getCurrentAction();
        if (action1.equals(Action.PRIMARY_ATTACK) && action2.equals(Action.PRIMARY_ATTACK)){
            Gotchi attacker = this.setAttacker();
            Gotchi defender = this.setDefender();
            this.dealDamage(attacker, defender);
            if (defender.getStatPoints().getHealthPoints() <= 0) {
                return String.format("%s is dead!", defender.getName());
            }
            this.dealDamage(defender, attacker);
            if (attacker.getStatPoints().getHealthPoints() <= 0) {
                return String.format("%s is dead", attacker.getName());
            }
        }

        return "Cos tam sie wydzialo";
    }

    public void dealDamage(Gotchi attacker, Gotchi defender) {
        double attackModifier = this.getModifier(attacker.getType(), defender.getType());
        defender.takeDamage(attacker.getStatPoints().getAttackPoints() * attackModifier);
    }

    public double getModifier(GochiType attackType, GochiType defenderType) {
        if (attackType.equals(defenderType.getVulnerability())) {
            return 1.25;
        } else if (attackType.equals(defenderType.getSecondaryType())) {
            return 0.75;
        } else {
            return 1;
        }
    }

    public void setGotchiReady(Gotchi gotchi) {
        gotchi.setReady(true);
        if (gotchi1.isReady() && gotchi2.isReady()) {
            this.battle();
        }
    }

    public Gotchi setAttacker() {
        if (this.gotchi1.getStatPoints().getSpeedPoints() > this.gotchi2.getStatPoints().getSpeedPoints()) {
            return this.gotchi1;
        } else {
            return this.gotchi2;
        }
    }

    public Gotchi setDefender() {
        if (this.gotchi1.getStatPoints().getSpeedPoints() > this.gotchi2.getStatPoints().getSpeedPoints()) {
            return this.gotchi2;
        } else {
            return this.gotchi1;
        }
    }

    public Gotchi getGotchi1() {
        return gotchi1;
    }

    public Gotchi getGotchi2() {
        return this.gotchi2;
    }
}
