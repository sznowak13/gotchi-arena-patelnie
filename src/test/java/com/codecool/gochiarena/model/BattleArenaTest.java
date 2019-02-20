package com.codecool.gochiarena.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleArenaTest {

    private Gotchi g1 = new Gotchi("G1", GochiType.WATER, 20, 20, 20, 20);
    private Gotchi g2 = new Gotchi("G2", GochiType.GRASS, 30, 0, 0, 10);
    private BatlleArenaModel battleArena = new BatlleArenaModel();

    @Test
    void testGetAttackModifier() {
        double mod = battleArena.getTypeModifier(GochiType.WATER, GochiType.FIRE);
        assertEquals(1.25, mod);
    }

    @Test
    void testDealDamageWeakerToStrongerWithPrimary() {
        g1.setCurrentAction(Action.PRIMARY_ATTACK);
        battleArena.dealDamage(g1, g2);
        assertEquals(85 , g2.getStatPoints().getHealthPoints());
    }

    @Test
    void testDealDamageWeakerToStrongerWithSecondary() {
        g1.setCurrentAction(Action.SECONDARY_ATTACK);
        battleArena.dealDamage(g1, g2);
        assertEquals(82 , g2.getStatPoints().getHealthPoints());
    }

    @Test
    void testDealDamageStrongerToWeakerWithPrimary() {
        g2.setCurrentAction(Action.PRIMARY_ATTACK);
        battleArena.dealDamage(g2, g1);
        assertEquals(83, g1.getStatPoints().getHealthPoints());
    }

    @Test
    void testDealDamageStrongerToWeakerWithSecondary() {
        g2.setCurrentAction(Action.SECONDARY_ATTACK);
        battleArena.dealDamage(g2, g1);
        assertEquals(98, g1.getStatPoints().getHealthPoints());
    }

    @Test
    void testApplyingAttackVsAttackScenario() {
        Gotchi g1 = new Gotchi("G1", GochiType.FIRE, 100, 10, 40, 50);
        Gotchi g2 = new Gotchi("G2", GochiType.WATER, 80, 30, 50, 40);
        battleArena.setGotchi1(g1);
        battleArena.setGotchi2(g2);
        g1.setCurrentAction(Action.SECONDARY_ATTACK);
        g2.setCurrentAction(Action.PRIMARY_ATTACK);
        battleArena.applyAttackVsAttackScenario();
        assertEquals(40, g1.getStatPoints().getHealthPoints());
        assertEquals(57, g2.getStatPoints().getHealthPoints());
        battleArena.applyAttackVsAttackScenario();
        assertEquals(0, g1.getStatPoints().getHealthPoints());
        assertEquals(57, g2.getStatPoints().getHealthPoints());
    }

    @Test
    void testApplyingAttackVsEvadeScenarioHighSpeed() {
        Gotchi g1 = new Gotchi("G1", GochiType.FIRE, 100, 10, 40, 50);
        Gotchi g2 = new Gotchi("G2", GochiType.GRASS, 50, 340, 70, 60);
        battleArena.setGotchi1(g1);
        battleArena.setGotchi2(g2);
        int evadersHealth = g2.getStatPoints().getHealthPoints();
        battleArena.applyAttackVsEvadeScenario(g1, g2);
        assertEquals(evadersHealth, g2.getStatPoints().getHealthPoints());
    }

    @Test
    void testApplyingAttackVsEvadeScenarioLowSpeed() {
        Gotchi g1 = new Gotchi("G1", GochiType.FIRE, 100, 100, 40, 40);
        Gotchi g2 = new Gotchi("G2", GochiType.FIRE, 100, 1, 40, 40);
        battleArena.setGotchi1(g1);
        battleArena.setGotchi2(g2);
        int evadersHealth = g2.getStatPoints().getHealthPoints();
        battleArena.applyAttackVsEvadeScenario(g1, g2);
        assertNotEquals(evadersHealth, g2.getStatPoints().getHealthPoints());
    }

}