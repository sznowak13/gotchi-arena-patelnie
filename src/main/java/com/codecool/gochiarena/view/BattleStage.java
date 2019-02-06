package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.BattleArena;
import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class BattleStage extends GridPane {

    private BattleArena battleArena = new BattleArena();

    GotchiInfo info;

    public BattleStage() {
        this.setPadding(new Insets(10));
        this.add(new Text("test"), 0, 1);
        this.setGridLinesVisible(true);
        this.setAlignment(Pos.TOP_LEFT);
    }

    public void setGotchiInfo(Gotchi gotchi) {
        this.info = new GotchiInfo(gotchi);
        this.add(info, 0, 0);
    }
}
