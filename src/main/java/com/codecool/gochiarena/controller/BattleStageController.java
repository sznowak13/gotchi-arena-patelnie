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
        this.battleStage.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String s = evt.getPropertyName();
        if ("Player Ready".equals(s)) {
            this.battleArena.setGotchiReady(0);
            System.out.println("PLAYER READY");
        } else if ("BeginBattle".equals(s)) {
            Gotchi playersGotchi = (Gotchi) evt.getNewValue();
            Gotchi enemy = GotchiDAO.getInstance().getRandomGotchi();
            this.battleArena.setupGotchis(playersGotchi, enemy);
            this.battleStage.setupGotchisView(playersGotchi, enemy);
        }
    }
}
