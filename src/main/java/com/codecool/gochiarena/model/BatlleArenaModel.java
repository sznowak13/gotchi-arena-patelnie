package com.codecool.gochiarena.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatlleArenaModel {

    private List<Gotchi> gotchis = new ArrayList<>();

    private Map<String, Gotchi> fighters = new HashMap<>();

    public void setGotchi1(Gotchi gotchi1) {
        this.gotchis.add(0, gotchi1);
    }

    public void setGotchi2(Gotchi gotchi2) {
        this.gotchis.add(1, gotchi2);
    }

    public String battle() {
        Action action1 = gotchis.get(0).getCurrentAction();
        Action action2 = gotchis.get(1).getCurrentAction();
        if (action1.equals(Action.PRIMARY_ATTACK) && action2.equals(Action.PRIMARY_ATTACK)){
            setAttackerAndDefender();
            Gotchi attacker = this.fighters.get("Attacker");
            Gotchi defender = this.fighters.get("Defender");
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

    public void applyAttackVsAttackScenario() {
        setAttackerAndDefender();
        Gotchi attacker = fighters.get("Attacker");
        Gotchi defender = fighters.get("Defender");
        dealDamage(attacker, defender);
        if (isAlive(defender)) {
            dealDamage(defender, attacker);
        }
    }

    private boolean isAlive(Gotchi gotchi) {
        return gotchi.getStatPoints().getHealthPoints() > 0;
    }

    /**
     *  Method that takes attacker and deals damage to defender based on the attack type
     *  chosen by attacker.
     *  If attacker chose primary attack, the attack type is set to attacker
     *  base type (e.g Fire gotchi primary attack is Fire), if attacker chose secondary attack
     *  the attack type is set to secondary type (e.g Fire secondary attack is Grass).
     *
     *  attackTypeModifier is based on the attack type and defender base type.
     *  attackStrengthModifier is based on the attack strength
     *  @see #getTypeModifier(GochiType, GochiType)
     *  @see Attack
     *
     * @param attacker Gotchi that attacks
     * @param defender Gotchi that defends
     */
    public void dealDamage(Gotchi attacker, Gotchi defender) {
        GochiType attackType;
        double attackStrengthModifier;
        if (Action.PRIMARY_ATTACK.equals(attacker.getCurrentAction())){
            attackType = attacker.getType();
            attackStrengthModifier = attacker.getPrimaryAttack().getModifier();
        } else {
            attackType = attacker.getType().getSecondaryType();
            attackStrengthModifier = attacker.getSecondaryAttack().getModifier();
        }
        double attackTypeModifier = this.getTypeModifier(attackType, defender.getType());
        double calculatedDmg = (attacker.getStatPoints().getAttackPoints() * attackTypeModifier) * attackStrengthModifier;
        defender.takeDamage(calculatedDmg);
    }

    public double getTypeModifier(GochiType attackType, GochiType defenderType) {
        if (attackType.equals(defenderType.getVulnerability())) {
            return 1.25;
        } else if (attackType.equals(defenderType.getSecondaryType())) {
            return 0.75;
        } else {
            return 1;
        }
    }

    public void setGotchiReady(int playerNum) {
        gotchis.get(playerNum).setReady(true);
    }

    public boolean gotchisReady() {
        return gotchis.get(0).isReady() && gotchis.get(1).isReady();
    }

    public void setAttackerAndDefender() {
        Gotchi gotchi1 = gotchis.get(0);
        Gotchi gotchi2 = gotchis.get(1);
        if (gotchi1.getStatPoints().getSpeedPoints() > gotchi2.getStatPoints().getSpeedPoints()) {
            fighters.put("Attacker", gotchi1);
            fighters.put("Defender", gotchi2);
        } else {
            fighters.put("Attacker", gotchi2);
            fighters.put("Defender", gotchi1);
        }
    }

    public Gotchi getGotchi1() {
        return gotchis.get(0);
    }

    public Gotchi getGotchi2() {
        return gotchis.get(1);
    }

    public void setupGotchis(Gotchi playersGotchi, Gotchi enemy) {
        this.gotchis.add(playersGotchi);
        this.gotchis.add(enemy);
    }

    public void chooseCorrectBattleScenario() {
        Action gotchis1action = gotchis.get(0).getCurrentAction();
        Action gotchis2action = gotchis.get(1).getCurrentAction();
        if (gotchis1action.equals(Action.PRIMARY_ATTACK) && gotchis2action.equals(Action.PRIMARY_ATTACK)) {
            applyAttackVsAttackScenario();
        }
        if (gotchis1action.equals(Action.PRIMARY_ATTACK) && gotchis2action.equals(Action.DEFEND) || gotchis1action.equals(Action.DEFEND) && gotchis2action.equals(Action.PRIMARY_ATTACK)) {
            if (gotchis1action.equals(Action.PRIMARY_ATTACK) && gotchis2action.equals(Action.DEFEND)) {
                applyAttackVsDefendScenario(gotchis.get(0), gotchis.get(1));
            }
            else {
                applyAttackVsDefendScenario(gotchis.get(1), gotchis.get(0));
            }

        }
        if (gotchis1action.equals(Action.PRIMARY_ATTACK) && gotchis2action.equals(Action.EVADE) || gotchis1action.equals(Action.EVADE) && gotchis2action.equals(Action.PRIMARY_ATTACK)) {
            if (gotchis1action.equals(Action.PRIMARY_ATTACK) && gotchis2action.equals(Action.EVADE)) {
                applyAttackVsEvadeScenario(gotchis.get(0), gotchis.get(1));
            }
            else {
                applyAttackVsEvadeScenario(gotchis.get(1), gotchis.get(0));
            }
        }

    }
}
