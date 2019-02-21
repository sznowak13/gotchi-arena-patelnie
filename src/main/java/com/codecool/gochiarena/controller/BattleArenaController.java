package com.codecool.gochiarena.controller;

import com.codecool.gochiarena.model.*;
import com.codecool.gochiarena.view.BattleArenaView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BattleArenaController implements PropertyChangeListener {

    private BattleArenaView battleArenaView;
    private BatlleArenaModel batlleArenaModel;
    private Thread AIThread = new Thread();

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
            this.batlleArenaModel.setPlayersGotchiCurrentAction((Action) evt.getNewValue());
            if (this.batlleArenaModel.gotchisReady()) {

            }
        } else if ("EnemyReady".equals(s)) {
            setGotchiReady(1);
            this.batlleArenaModel.setEnemyCurrentAction((Action) evt.getNewValue());
        } else if ("BeginBattle".equals(s)) {
            Gotchi playersGotchi = (Gotchi) evt.getNewValue();
            PlayerAI enemyPlayer = createEnemyFromAvailableGotchis(playersGotchi);
            setupBattle(playersGotchi, enemyPlayer.getGotchi());
        }
    }

    private PlayerAI createEnemyFromAvailableGotchis(Gotchi playersGotchi) {
        PlayerAI enemy = new PlayerAI(GotchiDAO.getInstance().getRandomGotchi());
        while(enemy.getGotchi().equals(playersGotchi)){
            enemy = new PlayerAI(GotchiDAO.getInstance().getRandomGotchi());
        }
        AIThread = new Thread(enemy);
        enemy.addPropertyChangeListener(this);
        AIThread.start();
        return enemy;
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
