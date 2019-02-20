package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
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
    private Rectangle readyBox = new Rectangle(15, 15, Color.RED);


    EnemyGotchisInfo(Gotchi gotchi) {
        this.setId("enemy-gotchi-battle-info");
        this.enemyName.setText("User: " + "Enemy");
        this.name.setText("Name: " + gotchi.getName());
        this.type.setText("Type: " + gotchi.getType());
        this.health.setText("Health: " + String.valueOf(gotchi.getStatPoints().getHealthPoints()));
        this.stamina.setText("Stamina: " + String.valueOf(gotchi.getStatPoints().getStaminaPoints()));
        this.readyStatus.getChildren().addAll(new Text("Ready: "), readyBox);
        this.readyStatus.setId("enemy-gotchi-battle-info");
        this.getChildren().addAll(enemyName, name, type, health, stamina, readyStatus);
    }

    public void setReadyBox(boolean ready) {
        Color readyStatus = ready ? Color.GREEN : Color.RED;
        readyBox.setFill(readyStatus);
    }

}
