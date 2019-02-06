package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class BattleStage extends BorderPane {

    private GotchiInfo playerInfo;
    private EnemyGotchisInfo enemyInfo;
    private ActionChooseView actionChoose = new ActionChooseView();
    private BattleMessageView battleMessageView = new BattleMessageView();

    public BattleStage() {
        this.setPadding(new Insets(10));
        this.setLeft(actionChoose);
        this.setBottom(battleMessageView);
    }

    public void setGotchiInfo(Gotchi gotchi) {
        this.playerInfo = new GotchiInfo(gotchi);
        this.setTop(playerInfo);
    }

    public void setEnemyInfo(Gotchi gotchi) {
        this.enemyInfo = new EnemyGotchisInfo(gotchi);
        this.setRight(enemyInfo);
    }
}
