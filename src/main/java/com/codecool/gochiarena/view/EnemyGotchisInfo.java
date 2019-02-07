package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class EnemyGotchisInfo extends VBox {
    private Text enemyName = new Text();
    private Text name = new Text();
    private Text type = new Text();
    private Text health = new Text();
    private Text stamina = new Text();
    private HBox readyStatus = new HBox(5);


    EnemyGotchisInfo(Gotchi gotchi) {
        this.setAlignment(Pos.CENTER);
        this.enemyName.setText("User: " + "Enemy");
        this.name.setText("Name: " + gotchi.getName());
        this.type.setText("Type: " + gotchi.getType());
        this.health.setText("Health: " + String.valueOf(gotchi.getStatPoints().getHealthPoints()));
        this.stamina.setText("Stamina: " + String.valueOf(gotchi.getStatPoints().getStaminaPoints()));
        this.readyStatus.getChildren().addAll(new Text("Ready: "), new Rectangle(10,10, Color.RED));
        this.readyStatus.setAlignment(Pos.CENTER);
        this.getChildren().addAll(enemyName, name, type, health, stamina, readyStatus);
    }

}
