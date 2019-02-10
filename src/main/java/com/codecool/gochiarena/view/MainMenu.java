package com.codecool.gochiarena.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.*;

import javafx.stage.Stage;

public class MainMenu extends BorderPane {

    private Button createGotchiBtn = new Button("Create new gotchi");
    private Button singlePlayerBtn = new Button("Single player");
    private Button multiPlayerBtn = new Button("Multiplayer");


    public MainMenu() {
        this.setRight(this.createGotchiBtn);
        this.setCenter(this.multiPlayerBtn);
        this.setLeft(this.singlePlayerBtn);
    }


    public void setCreateButtonAction(Stage primaryStage, Scene scene) {
        this.createGotchiBtn.setOnAction((event) -> primaryStage.setScene(scene));
    }

    public void setSinglePlayerBtn(Stage primaryStage, Scene scene) {
        this.singlePlayerBtn.setOnAction((event) -> primaryStage.setScene(scene));
    }

    public void setMultiPlayerBtn(Stage primaryStage, Scene scene) {
        this.multiPlayerBtn.setOnAction((event) -> primaryStage.setScene(scene));
    }

}
