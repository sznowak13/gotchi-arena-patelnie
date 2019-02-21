package com.codecool.gochiarena.model;

import java.util.*;

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
        return chooseCorrectBattleScenario();

    }


    public String applyAttackVsAttackScenario() {
        setAttackerAndDefender();
        Gotchi attacker = fighters.get("Attacker");
        Gotchi defender = fighters.get("Defender");
        String msg = dealDamage(attacker, defender);
        if (isAlive(defender)) {
            String msg1 = dealDamage(defender, attacker);
            return msg1;
        }
        return msg;
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
    public String dealDamage(Gotchi attacker, Gotchi defender) {
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
        String msg = String.format("%s choose %s !, \n %s choose %s ! \n %s deal %s damage to %s",
                attacker.getName(), attacker.getCurrentAction(),
                defender.getName(), defender.getCurrentAction(),
                attacker.getName(), calculatedDmg, defender.getName());
        return msg;
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

    public void applyAttackVsDefendScenario(Gotchi attacker, Gotchi defender) {
        if (attacker.getCurrentAction().equals(Action.PRIMARY_ATTACK)) {
            double attackModifier = this.getTypeModifier(attacker.getType(), defender.getType());
            defender.takeDamage((attacker.getStatPoints().getAttackPoints() * attackModifier) - (defender.getStatPoints().getDefencePoints()));
        }
        else if (attacker.getCurrentAction().equals(Action.SECONDARY_ATTACK)) {
            double secondaryAttackModifier = this.getTypeModifier(attacker.getType().getSecondaryType(), defender.getType());
            defender.takeDamage((attacker.getStatPoints().getAttackPoints() * secondaryAttackModifier * Attack.SECONDARY.getModifier()) - (defender.getStatPoints().getDefencePoints()));
        }

    }

    public void applyAttackVsEvadeScenario(Gotchi attacker, Gotchi defender) {
        double attackersSpeed = attacker.getStatPoints().getSpeedPoints();
        double defendersSpeed = defender.getStatPoints().getSpeedPoints();
        Random random = new Random();
        double randomValue1 = 0.75 + (1.25 - 0.75) * random.nextDouble();
        double randomValue2 = 0.75 + (1.25 - 0.75) * random.nextDouble();
        if ((attackersSpeed * randomValue1) - (defendersSpeed * randomValue2) > 0) {
            dealDamage(attacker, defender);
        }

    }

    public String chooseCorrectBattleScenario() {
        Action gotchis1action = gotchis.get(0).getCurrentAction();
        Action gotchis2action = gotchis.get(1).getCurrentAction();
        if (gotchis1action.equals(Action.PRIMARY_ATTACK) && gotchis2action.equals(Action.PRIMARY_ATTACK)) {
            String msg = applyAttackVsAttackScenario();
            return msg;
        }
        if (checkIfAttackVs(Action.DEFEND, gotchis1action, gotchis2action)) {
            if (gotchis1action.equals(Action.PRIMARY_ATTACK) || gotchis1action.equals(Action.SECONDARY_ATTACK)) {
                applyAttackVsDefendScenario(gotchis.get(0), gotchis.get(1));
            }
            else {
                applyAttackVsDefendScenario(gotchis.get(1), gotchis.get(0));
            }

        }
        if (checkIfAttackVs(Action.EVADE, gotchis1action, gotchis2action)) {
            if (gotchis1action.equals(Action.PRIMARY_ATTACK) || gotchis1action.equals(Action.SECONDARY_ATTACK)) {
                applyAttackVsEvadeScenario(gotchis.get(0), gotchis.get(1));
            }
            else {
                applyAttackVsEvadeScenario(gotchis.get(1), gotchis.get(0));
            }
        }
        return "demo";
    }

    private boolean checkIfAttackVs(Action checkedAction, Action action1, Action action2) {
        boolean firstAttacks = (Action.PRIMARY_ATTACK.equals(action1) || Action.SECONDARY_ATTACK.equals(action1)) && checkedAction.equals(action2);
        boolean secondAttacks = (Action.PRIMARY_ATTACK.equals(action2) || Action.SECONDARY_ATTACK.equals(action2)) && checkedAction.equals(action1);
        return firstAttacks || secondAttacks;
    }
}
