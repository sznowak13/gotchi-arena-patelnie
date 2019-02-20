package com.codecool.gochiarena.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GotchiTest {

    @Test
    void testCreatingWithCorrectTypes() {
        Gotchi gotchi = new Gotchi("Test", GochiType.WATER, 10,10,10,10);
        assertEquals(GochiType.WATER, gotchi.getType());
    }

}