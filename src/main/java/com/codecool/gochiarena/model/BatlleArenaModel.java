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
        String result = chooseCorrectBattleScenario();
        resetGotchisStatus();
        return result;
    }

    public void resetGotchisStatus() {
        Gotchi gotchi1 = getGotchi1();
        Gotchi gotchi2 = getGotchi2();
        gotchi1.setReady(false);
        gotchi2.setReady(false);
    }



    public String applyAttackVsAttackScenario() {
        String result = "";
        setAttackerAndDefender();
        Gotchi attacker = fighters.get("Attacker");
        Gotchi defender = fighters.get("Defender");
        result += dealDamage(attacker, defender);
        if (isAlive(defender)) {
            result += dealDamage(defender, attacker);
        }
        return result;
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
        double calculatedRawDmg = (attacker.getStatPoints().getAttackPoints() * attackTypeModifier) * attackStrengthModifier;
        double dmgDealt = defender.takeDamage(calculatedRawDmg);
        return String.format("%s choose %s of type %s! Dealt %s damage to %s\n",
                attacker.getName(), attacker.getCurrentAction(), attackType, dmgDealt, defender.getName());
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

    public String applyAttackVsDefendScenario(Gotchi attacker, Gotchi defender) {
        String result = String.format("%s doubled its defence! \n", defender.getName());
        defender.buffDefence();
        result += dealDamage(attacker, defender);
        defender.resetDefence();
        return result;
    }

    public String applyAttackVsEvadeScenario(Gotchi attacker, Gotchi defender) {
        String result = String.format("%s used Evade!\n", defender.getName());
        double attackersSpeed = attacker.getStatPoints().getSpeedPoints();
        double defendersSpeed = defender.getStatPoints().getSpeedPoints();
        Random random = new Random();
        double randomValue1 = 0.75 + (1.25 - 0.75) * random.nextDouble();
        double randomValue2 = 0.75 + (1.25 - 0.75) * random.nextDouble();
        if ((attackersSpeed * randomValue1) - (defendersSpeed * randomValue2) > 0) {
            result += String.format("%s landed a hit!\n", attacker.getName());
            result += dealDamage(attacker, defender);
        } else {
            result += String.format("%s managed to evade the attack!", defender.getName());
        }

        return result;
    }

    public String chooseCorrectBattleScenario() {
        Action gotchis1action = getGotchi1().getCurrentAction();
        Action gotchis2action = getGotchi2().getCurrentAction();
        if (checkIfAttackVs(Action.PRIMARY_ATTACK, gotchis1action, gotchis2action) ||
                checkIfAttackVs(Action.SECONDARY_ATTACK, gotchis1action, gotchis2action)) {
            result.append("Both gotchis used attack!\n");
            result.append(applyAttackVsAttackScenario());
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

    public void setPlayersGotchiCurrentAction(Action action){
        System.out.println("Setter test"+ action);
        getGotchi1().setCurrentAction(action);
    }

    public void setEnemyCurrentAction(Action action){
        System.out.println("Setter test"+ action);
        getGotchi2().setCurrentAction(action);
    }



}
