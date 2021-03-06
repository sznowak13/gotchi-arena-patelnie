package com.codecool.gochiarena.model;

import java.util.*;

public class BatlleArenaModel {

    private List<Gotchi> gotchis = new ArrayList<>();
    private Map<String, Gotchi> fighters = new HashMap<>();

    public String battle() {
        String result = chooseCorrectBattleScenario();
        resetGotchisStatus();
        return result;
    }

    private String chooseCorrectBattleScenario() {
        StringBuilder result = new StringBuilder("New round!\n");
        Action gotchis1action = getGotchi1().getCurrentAction();
        Action gotchis2action = getGotchi2().getCurrentAction();
        if (checkIfAttackVs(Action.PRIMARY_ATTACK, gotchis1action, gotchis2action) ||
                checkIfAttackVs(Action.SECONDARY_ATTACK, gotchis1action, gotchis2action)) {
            result.append("Both gotchis used attack!\n");
            result.append(applyAttackVsAttackScenario());
        }
        else if (checkIfAttackVs(Action.DEFEND, gotchis1action, gotchis2action)) {
            if (gotchis1action.equals(Action.PRIMARY_ATTACK) || gotchis1action.equals(Action.SECONDARY_ATTACK)) {
                result.append(applyAttackVsDefendScenario(gotchis.get(0), gotchis.get(1)));
            }
            else {
                result.append(applyAttackVsDefendScenario(gotchis.get(1), gotchis.get(0)));
            }

        }
        else if (checkIfAttackVs(Action.EVADE, gotchis1action, gotchis2action)) {
            if (gotchis1action.equals(Action.PRIMARY_ATTACK) || gotchis1action.equals(Action.SECONDARY_ATTACK)) {
                result.append(applyAttackVsEvadeScenario(gotchis.get(0), gotchis.get(1)));
            }
            else {
                result.append(applyAttackVsEvadeScenario(gotchis.get(1), gotchis.get(0)));
            }
        } else {
            result.append("None of the gotchis attacked! Nothing happens!");
        }
        return result.toString();
    }

    private void resetGotchisStatus() {
        Gotchi gotchi1 = getGotchi1();
        Gotchi gotchi2 = getGotchi2();
        gotchi1.setReady(false);
        gotchi2.setReady(false);
    }

    private boolean checkIfAttackVs(Action checkedAction, Action action1, Action action2) {
        boolean firstAttacks = (Action.PRIMARY_ATTACK.equals(action1) || Action.SECONDARY_ATTACK.equals(action1)) && checkedAction.equals(action2);
        boolean secondAttacks = (Action.PRIMARY_ATTACK.equals(action2) || Action.SECONDARY_ATTACK.equals(action2)) && checkedAction.equals(action1);
        return firstAttacks || secondAttacks;
    }

    /**
     * Applies scenario where both gotchis are attacking each other.
     *
     * First it calculates which Gotchi should attack first based on their speed,
     * Then it deals damage to the first defender, if defender still have healthPoints
     * defender deals damage to attacker.
     *
     * @see #dealDamage(Gotchi, Gotchi)
     * @see StatPoints
     * @see #setWhichAttacksFirst()
     * 
     * @return String that is representation of what happened in the scenario.
     */
    String applyAttackVsAttackScenario() {
        String result = "";
        setWhichAttacksFirst();
        Gotchi attacker = fighters.get("Attacker");
        Gotchi defender = fighters.get("Defender");
        result += dealDamage(attacker, defender);
        if (isAlive(defender)) {
            result += dealDamage(defender, attacker);
        }
        return result;
    }

    /**
     * Applies scenario where one Gotchi is attacking and second is used Defend action.
     * Before dealing damage, the defender doubles its defence by 2.
     * After that the damage is dealt and defender again resets its defence to normal.
     *
     * @see Action
     * @see #dealDamage(Gotchi, Gotchi)
     *
     * @param attacker Gotchi that's attacking
     * @param defender Gotchi that's defending
     * @return
     */
    String applyAttackVsDefendScenario(Gotchi attacker, Gotchi defender) {
        String result = String.format("%s doubled its defence! \n", defender.getName());
        defender.buffDefence();
        result += dealDamage(attacker, defender);
        defender.resetDefence();
        return result;
    }

    /**
     * Applies the scenario in which one gotchi is attacking and the second is evading.
     * Before attacker attacks its checked if the defender has successfully evaded the attack
     * by this formula:
     * attacker's speed * (random value between 0.75 and 1.25) -
     * defender's speed * (random value between 0.75 and 1.25)
     *
     * If defender was successful he takes no dmg, otherwise dmg is dealt using
     * dealDamage method.
     *
     * @see #dealDamage(Gotchi, Gotchi)
     *
     * @param attacker Gotchi that's attacking
     * @param defender Gotchi that's evading
     * @return String that is a representation of what exactly happened in this scenario
     */
    String applyAttackVsEvadeScenario(Gotchi attacker, Gotchi defender) {
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

    /**
     * Sets which gotchi is attacking first and which is defending
     * based on their speed, and puts them accordingly into the fighters map.
     * @see #fighters
     */
    private void setWhichAttacksFirst() {
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


    /**
     *  Method that takes attacker and deals damage to defender based on the attack type
     *  chosen by attacker.
     *  If attacker chose primary attack, the attack type is set to attacker
     *  base type (e.g Fire gotchi primary attack is Fire), if attacker chose secondary attack
     *  the attack type is set to secondary type (e.g Fire secondary attack is Grass).
     *
     *  Damage is calculated by this formula:
     *  (attacker's attack points * attackTypeModifier * attackStrengthModifier) - defender's defense point
     *
     *  attackTypeModifier is based on the attack type and defender base type.
     *  attackStrengthModifier is based on the attack strength
     *  @see #getTypeModifier(GochiType, GochiType)
     *  @see Attack
     *
     * @param attacker Gotchi that attacks
     * @param defender Gotchi that defends
     */
    String dealDamage(Gotchi attacker, Gotchi defender) {
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

    private boolean isAlive(Gotchi gotchi) {
        return gotchi.getStatPoints().getHealthPoints() > 0;
    }

    double getTypeModifier(GochiType attackType, GochiType defenderType) {
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

    public void setPlayersGotchiCurrentAction(Action action){
        System.out.println("Setter test"+ action);
        getGotchi1().setCurrentAction(action);
    }

    public void setEnemyCurrentAction(Action action){
        System.out.println("Setter test"+ action);
        getGotchi2().setCurrentAction(action);
    }

    public void setGotchi1(Gotchi gotchi1) {
        this.gotchis.add(0, gotchi1);
    }

    public void setGotchi2(Gotchi gotchi2) {
        this.gotchis.add(1, gotchi2);
    }



}
