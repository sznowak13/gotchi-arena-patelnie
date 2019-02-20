package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GotchiInfo extends HBox {
    private Text name = new Text();
    private Text type = new Text();
    private Text health = new Text();
    private Text stamina = new Text();
    private Text attack = new Text();
    private Text speed = new Text();
    private Text defence = new Text();
    private Rectangle readyIndicator = new Rectangle(15, 15, Color.RED);


    GotchiInfo(Gotchi gotchi) {
        super.setId("gotchi-battle-info");
        name.setText("Name \n" + gotchi.getName());
        type.setText("Type \n" + gotchi.getType().toString());
        health.setText("Health \n" + String.valueOf(gotchi.getStatPoints().getHealthPoints()));
        stamina.setText("Stamina \n" + String.valueOf(gotchi.getStatPoints().getStaminaPoints()));
        attack.setText("Attack \n" + String.valueOf(gotchi.getStatPoints().getAttackPoints()));
        speed.setText("Speed \n" + String.valueOf(gotchi.getStatPoints().getSpeedPoints()));
        defence.setText("Defence \n" + String.valueOf(gotchi.getStatPoints().getDefencePoints()));

        this.getChildren().addAll(name, new Text("Ready: "), readyIndicator, type, health, stamina, attack, speed, defence);
    }


    public void setPlayerReady(boolean ready) {
        Color readyColor = ready ? Color.GREEN : Color.RED;
        this.readyIndicator.setFill(readyColor);
    }
}
