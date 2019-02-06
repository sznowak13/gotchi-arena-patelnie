package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GotchiInfo extends VBox {
    private Text name = new Text();
    private Text type = new Text();
    private Text health = new Text();
    private Text attack = new Text();
    private Text speed = new Text();
    private Text defence = new Text();
    private Text stamina = new Text();


    GotchiInfo(Gotchi gotchi) {
        name.setText(gotchi.getName());
        type.setText(gotchi.getType().toString());
        health.setText("Health " + String.valueOf(gotchi.getStatPoints().getHealthPoints()));
        attack.setText("Attack " + String.valueOf(gotchi.getStatPoints().getAttackPoints()));
        speed.setText("Speed " + String.valueOf(gotchi.getStatPoints().getSpeedPoints()));
        defence.setText("Defence " + String.valueOf(gotchi.getStatPoints().getDefencePoints()));
        stamina.setText("Stamina " + String.valueOf(gotchi.getStatPoints().getStaminaPoints()));

        this.getChildren().addAll(name, type, health, attack, speed, defence, stamina);
        this.setAlignment(Pos.CENTER);
    }


}
