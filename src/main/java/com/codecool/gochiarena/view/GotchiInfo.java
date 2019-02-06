package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GotchiInfo extends HBox {
    private Text name = new Text();
    private Text type = new Text();
    private Text health = new Text();
    private Text stamina = new Text();
    private Text attack = new Text();
    private Text speed = new Text();
    private Text defence = new Text();


    GotchiInfo(Gotchi gotchi) {
        super(15);
        name.setText("Name \n" + gotchi.getName());
        name.setFont(Font.loadFont("file:resources/fonts/font.ttf", 120));
        type.setText("Type \n" + gotchi.getType().toString());
        this.type.setFont(Font.font(15));
        health.setText("Health \n" + String.valueOf(gotchi.getStatPoints().getHealthPoints()));
        this.health.setFont(Font.font(15));
        stamina.setText("Stamina \n" + String.valueOf(gotchi.getStatPoints().getStaminaPoints()));
        this.stamina.setFont(Font.font(15));
        attack.setText("Attack \n" + String.valueOf(gotchi.getStatPoints().getAttackPoints()));
        this.attack.setFont(Font.font(15));
        speed.setText("Speed \n" + String.valueOf(gotchi.getStatPoints().getSpeedPoints()));
        this.speed.setFont(Font.font(15));
        defence.setText("Defence \n" + String.valueOf(gotchi.getStatPoints().getDefencePoints()));
        this.defence.setFont(Font.font(15));


        this.getChildren().addAll(name, type, health, stamina, attack, speed, defence);
        this.setAlignment(Pos.CENTER);
    }


}
