package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class BattleStage extends BorderPane {

    private GotchiInfo playerInfo;
    private EnemyGotchisInfo enemyInfo;
    private ActionChooseView actionChoose = new ActionChooseView();
    private BattleMessageView battleMessageView = new BattleMessageView();

    public BattleStage() {
        this.setPadding(new Insets(10));
        this.actionChoose.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.setLeft(actionChoose);
        this.setBottom(battleMessageView);
    }

    public void setGotchiInfo(Gotchi gotchi) {
        this.playerInfo = new GotchiInfo(gotchi);
        this.playerInfo.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, null, null)));
        this.setTop(playerInfo);
    }

    public void setEnemyInfo(Gotchi gotchi) {
        this.enemyInfo = new EnemyGotchisInfo(gotchi);
        this.enemyInfo.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.setRight(enemyInfo);
    }

    public BattleMessageView getBattleMessageView() {
        return battleMessageView;
    }
}
