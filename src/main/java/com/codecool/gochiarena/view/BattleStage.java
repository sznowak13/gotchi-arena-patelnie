package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Action;
import com.codecool.gochiarena.model.BattleArena;
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
    private BattleArena battleArena = new BattleArena();

    public BattleStage() {
        this.setPadding(new Insets(10));
        this.actionChoose.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.setLeft(actionChoose);
        this.setBottom(battleMessageView);
    }

    public void setGotchiInfo(Gotchi gotchi) {
        this.battleArena.setGotchi1(gotchi);
        this.playerInfo = new GotchiInfo(gotchi);
        this.playerInfo.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, null, null)));
        this.setTop(playerInfo);
    }

    public void setEnemyInfo(Gotchi gotchi) {
        this.battleArena.setGotchi2(gotchi);
        this.enemyInfo = new EnemyGotchisInfo(gotchi);
        this.enemyInfo.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.setRight(enemyInfo);
    }

    public BattleMessageView getBattleMessageView() {
        return battleMessageView;
    }

    public void setupReadyButton() {
        this.actionChoose.getReadyButton().setOnAction(event -> {
            Gotchi g1 = this.battleArena.getGotchi1();
            Gotchi g2 = this.battleArena.getGotchi2();
            g1.setCurrentAction(Action.PRIMARY_ATTACK);
            g2.setCurrentAction(Action.PRIMARY_ATTACK);
            this.battleMessageView.addNewMessage(this.battleArena.battle());
            this.setGotchiInfo(this.battleArena.getGotchi1());
            this.setEnemyInfo(this.battleArena.getGotchi2());
            System.out.println(g1.getStatPoints().getHealthPoints());
            System.out.println(g2.getStatPoints().getHealthPoints());
        });
    }
}
