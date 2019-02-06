package com.codecool.gochiarena.view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BattleMessageView extends VBox {
    private List<Text> messages = new ArrayList<>();

    public BattleMessageView() {
        this.setMinWidth(500);
        this.setMinHeight(100);
        this.setBackground(new Background(new BackgroundFill(Color.CORNSILK, null, null)));
    }

    public void addNewMessage(String message) {
        this.messages.add(new Text(message));
        this.refreshMessages();
    }

    public void refreshMessages() {
        this.getChildren().clear();
        for (Text message: messages) {
            this.getChildren().add(message);
        }
    }
}
