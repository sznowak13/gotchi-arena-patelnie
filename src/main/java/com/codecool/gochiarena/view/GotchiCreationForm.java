package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.GochiType;
import com.codecool.gochiarena.model.Gotchi;
import com.codecool.gochiarena.model.StatPoints;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GotchiCreationForm extends VBox {

    private TextField nameField = new TextField("Your gotchi name...");
    private ChoiceBox<GochiType> typeChoiceBox = new ChoiceBox<>();
    private List<Text> statValues = new ArrayList<>();
    private Text pointsPool = new Text("200");
    private Button createGotchi = new Button("Create!");
    private Button backButton = new Button("Back to main menu");

    public GotchiCreationForm() {
        this.setSpacing(5);
        this.setPadding(new Insets(20));
        this.typeChoiceBox.getItems().addAll(GochiType.ROCK, GochiType.SCISSORS, GochiType.PAPER);
        this.typeChoiceBox.setValue(GochiType.ROCK);
        this.getChildren().addAll(
                new Label("Name: "), nameField,
                new Label("Choose type: "), typeChoiceBox,
                new Label("Stat points"), pointsPool
        );
        for (String statName : StatPoints.getStatNames()) {
            this.addStatField(statName);
        }
        this.getChildren().add(createGotchi);
        this.getChildren().add(backButton);
    }


    public void setupBackButton(Stage primaryStage, Scene scene){
        this.backButton.setOnAction((event) -> {
            primaryStage.setScene(scene);
        });
    }

    public void setupCreateButton(Stage primaryStage, Scene scene) {
        this.createGotchi.setOnAction((event) -> {
            Gotchi.addGotchi(new Gotchi(
                    this.nameField.getText(),
                    this.typeChoiceBox.getValue(),
                    this.calculateStatPoints())
            );
            if (scene.getRoot() instanceof MainMenu) {
                ((MainMenu) scene.getRoot()).createGotchiList();
            }
            primaryStage.setScene(scene);
        });
    }

    private StatPoints calculateStatPoints() {
        int[] statPoints = new int[statValues.size()];
        for (int i = 0; i < statPoints.length; i++) {
            statPoints[i] = Integer.parseInt(statValues.get(i).getText());
        }
        return new StatPoints(statPoints);
    }

    private Slider createStatSlider(Text value) {
        Slider slider = new Slider(0, 200, 0);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(1);
        slider.setShowTickLabels(true);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> value.setText(String.valueOf(newValue.intValue())));

        return slider;
    }

    private void addStatField(String statName) {
        HBox statField = new HBox(5);
        Text statValue = new Text("0");
        Slider statSlider = this.createStatSlider(statValue);
        statField.getChildren().addAll(new Label(statName) ,statSlider, statValue);
        this.getChildren().add(statField);
        this.statValues.add(statValue);
    }
}
