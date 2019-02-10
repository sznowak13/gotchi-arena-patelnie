package com.codecool.gochiarena.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenu extends BorderPane {

    private Button createGotchiBtn = new Button("Create new Gotchi");
    private Button singlePlayerBtn = new Button("Single player");
    private Button multiPlayerBtn = new Button("Multiplayer");



    public MainMenu() {
        this.setCenter(this.createVerticalField());
        this.setTop(this.createHorizontalFieldForImage());
    }

    public Button getCreateGotchiBtn() {
        return createGotchiBtn;
    }

    public Button getSinglePlayerBtn() {
        return singlePlayerBtn;
    }

    public Button getMultiPlayerBtn() {
        return multiPlayerBtn;
    }

    private VBox createVerticalField(){
        VBox verticalField = new VBox();
        verticalField.setSpacing(8);
        verticalField.setAlignment(Pos.TOP_CENTER);
        verticalField.getChildren().addAll(singlePlayerBtn, multiPlayerBtn, createGotchiBtn);
        return verticalField;
    }

    private HBox createHorizontalFieldForImage(){
        HBox horizontalField = new HBox();
        horizontalField.getStyleClass().add("hbox");
        horizontalField.setId("hbox-custom");
        return horizontalField;
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
