package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.BattleArena;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class BattleStage extends HBox {

    private BattleArena battleArena = new BattleArena();
    private Text gotchiName = new Text();

    public BattleStage() {
        this.getChildren().add(gotchiName);
        this.setAlignment(Pos.CENTER);
    }

    public void setGotchiName(String gotchiName) {
        this.gotchiName.setText(gotchiName);
    }
}
