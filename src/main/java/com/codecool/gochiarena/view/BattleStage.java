package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.BattleArena;
import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class BattleStage extends BorderPane {

    private GotchiInfo info;
    private ActionChooseView actionChoose = new ActionChooseView();
    private BattleMessageView battleMessageView = new BattleMessageView();

    public BattleStage() {
        this.setPadding(new Insets(10));
        this.setLeft(actionChoose);
        this.setBottom(battleMessageView);
    }

    public void setGotchiInfo(Gotchi gotchi) {
        this.info = new GotchiInfo(gotchi);
        this.setTop(info);
    }
}
