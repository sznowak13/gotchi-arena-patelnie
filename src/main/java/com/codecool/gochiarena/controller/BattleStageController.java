package com.codecool.gochiarena.controller;

import com.codecool.gochiarena.model.BattleArena;
import com.codecool.gochiarena.model.Gotchi;
import com.codecool.gochiarena.model.GotchiDAO;
import com.codecool.gochiarena.view.BattleStage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

public class BattleStageController implements PropertyChangeListener {

    private BattleStage battleStage;
    private BattleArena battleArena;

    public BattleStageController() {
        this.battleArena = new BattleArena();
    }

    public void setBattleStage(BattleStage battleStage) {
        this.battleStage = battleStage;
        battleStage.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((String) arg) {
            case "Player ready": {
                this.battleArena.setGotchiReady(0);
                System.out.println("PLAYER READY");
            }
        }
    }
}
