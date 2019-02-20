package com.codecool.gochiarena.controller;

import com.codecool.gochiarena.model.BatlleArenaModel;
import com.codecool.gochiarena.model.Gotchi;
import com.codecool.gochiarena.model.GotchiDAO;
import com.codecool.gochiarena.view.BattleArenaView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BattleArenaController implements PropertyChangeListener {

    private BattleArenaView battleArenaView;
    private BatlleArenaModel batlleArenaModel;

    public BattleArenaController() {
        this.batlleArenaModel = new BatlleArenaModel();
    }

    public void setBattleArenaView(BattleArenaView battleArenaView) {
        this.battleArenaView = battleArenaView;
        this.battleArenaView.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String s = evt.getPropertyName();
        if ("Player Ready".equals(s)) {
            setGotchiReady(0);
            if (this.batlleArenaModel.gotchisReady()) {
                // do battle and update the view
            }
        } else if ("BeginBattle".equals(s)) {
            Gotchi playersGotchi = (Gotchi) evt.getNewValue();
            Gotchi enemy = GotchiDAO.getInstance().getRandomGotchi();
            setupBattle(playersGotchi, enemy);
        }
    }

    private void setupBattle(Gotchi player, Gotchi enemy) {
        this.batlleArenaModel.setupGotchis(player, enemy);
        this.battleArenaView.setupGotchisView(player, enemy);

    }

    private void setGotchiReady(int gotchiNumber) {
        this.batlleArenaModel.setGotchiReady(gotchiNumber);
        if (gotchiNumber == 0) {
            this.battleArenaView.setPlayerReady();
        } else if (gotchiNumber == 1){
            this.battleArenaView.setEnemyReady();
        }
    }
}