package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GotchiInfo extends HBox {
    private Text name = new Text();
    private Text type = new Text();
    private Text health = new Text();
    private Text attack = new Text();
    private Text speed = new Text();
    private Text defence = new Text();
    private Text stamina = new Text();


    GotchiInfo(Gotchi gotchi) {
        super(15);
        name.setText("Name \n" + gotchi.getName());
        type.setText("Type \n" + gotchi.getType().toString());
        health.setText("Health \n" + String.valueOf(gotchi.getStatPoints().getHealthPoints()));
        attack.setText("Attack \n" + String.valueOf(gotchi.getStatPoints().getAttackPoints()));
        speed.setText("Speed \n" + String.valueOf(gotchi.getStatPoints().getSpeedPoints()));
        defence.setText("Defence \n" + String.valueOf(gotchi.getStatPoints().getDefencePoints()));
        stamina.setText("Stamina \n" + String.valueOf(gotchi.getStatPoints().getStaminaPoints()));

        this.getChildren().addAll(name, type, health, attack, speed, defence, stamina);
        this.setAlignment(Pos.CENTER);
    }


}
