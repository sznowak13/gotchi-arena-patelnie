package com.codecool.gochiarena.view;

import com.codecool.gochiarena.controller.GotchiFormController;
import com.codecool.gochiarena.model.GochiType;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GotchiCreationForm extends VBox {

    private TextField nameField = new TextField();
    private ChoiceBox<GochiType> typeChoiceBox = new ChoiceBox<>();
    private List<Text> statValues = new ArrayList<>();
    private Text pointsPool = new Text("200");
    private Button createGotchi = new Button("Create!");
    private Button backButton = new Button("Back to main menu");

    private final Text warningMsg = new Text("Warning: You mustn't exceed 200pts");
    private GotchiFormController controller;
    private String[] statLabels = new String[] {"Attack", "Defence", "Speed", "Stamina"};

    public GotchiCreationForm(GotchiFormController controller) {
        this.controller = controller;
        this.nameField.setPromptText("Your gotchi name...");
        this.nameField.setFocusTraversable(false);
        this.setId("gotchi-creation-form");
        this.typeChoiceBox.getItems().addAll(GochiType.WATER, GochiType.FIRE, GochiType.GRASS);
        this.typeChoiceBox.setValue(GochiType.WATER);
        this.getChildren().addAll(
                new Label("Name: "), nameField,
                new Label("Choose type: "), typeChoiceBox,
                new Label("Stat points"), pointsPool
        );
        for (String statName : this.statLabels) {
            this.addStatField(statName);
        }


        this.createHorizontalField();
    }


    public void setupBackButton(Stage primaryStage, Scene scene){
        this.backButton.setOnAction((event) -> {
            primaryStage.setScene(scene);
        });
    }


    public void setupCreateButton(Stage primaryStage, Scene scene) {
        this.createGotchi.setOnAction((event) -> {
            Map<String, Integer> points = this.readStatPoints();
            String error = this.controller.addNewGotchi(
                    this.nameField.getText(),
                    this.typeChoiceBox.getValue(),
                    points);
            if (error == null){
                if (scene.getRoot() instanceof PrepareBattle) {
                    ((PrepareBattle) scene.getRoot()).createGotchiList();
                }
                primaryStage.setScene(scene);}
            else {
                this.removeExceedWarning();
                this.addExceedWarning(error);
            }
        });
    }





    private Map<String, Integer> readStatPoints() {
        Map<String, Integer> statPoints = new HashMap<>();
        for (int i = 0; i < statLabels.length; i++) {
            statPoints.put(statLabels[i], Integer.parseInt(statValues.get(i).getText()));
        }
        return statPoints;
    }

    private Slider createStatSlider(Text value) {
        Slider slider = new Slider(0, 200, 0);
        slider.getStyleClass().add("slider");
        slider.setOnMouseClicked(Event -> this.removeExceedWarning());

        slider.valueProperty().addListener((observable, oldValue, newValue) ->{
            value.setText(String.valueOf(newValue.intValue()));
            pointsPool.setText(controller.calculatePointsPool(readStatPoints()));
            }
        );
        return slider;
    }

    private HBox createHorizontalField(){
        HBox horizontalField = new HBox();
        horizontalField.setId("create-gotchi-buttons");
        horizontalField.getChildren().addAll(backButton, createGotchi);
        this.getChildren().add(horizontalField);
        return horizontalField;
    }

    private void addStatField(String statName) {
        HBox statField = new HBox();
        statField.setId("create-gotchi-stat-field");
        Text statValue = new Text("0");
        Slider statSlider = this.createStatSlider(statValue);
        statSlider.getStyleClass().add("slider");
        statField.getChildren().addAll(new Label(statName) ,statSlider, statValue);
        this.getChildren().add(statField);
        this.statValues.add(statValue);
    }

    private void addExceedWarning(String message){
        this.warningMsg.setText(message);
        this.warningMsg.setId("create-gotchi-warning-msg");
        this.getChildren().add(this.warningMsg);
    }

    private void removeExceedWarning(){
        this.getChildren().remove(this.warningMsg);
    }

}
