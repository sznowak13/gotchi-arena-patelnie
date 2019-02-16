package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.GochiType;
import com.codecool.gochiarena.model.Gotchi;
import com.codecool.gochiarena.model.GotchiDAO;
import com.codecool.gochiarena.model.StatPoints;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GotchiCreationForm extends VBox {

    private TextField nameField = new TextField();
    private ChoiceBox<GochiType> typeChoiceBox = new ChoiceBox<>();
    private List<Text> statValues = new ArrayList<>();
    private Text pointsPool = new Text("200");
    private Button createGotchi = new Button("Create!");
    private Button backButton = new Button("Back to main menu");
    private final int poolOfPoints = 200;
    private final Text exceedMsg = new Text("Warning: You mustn't exceed 200pts");


    public GotchiCreationForm() {
        this.nameField.setPromptText("Your gotchi name...");
        this.nameField.setFocusTraversable(false);
        this.setId("gotchi-creation-form");
        this.typeChoiceBox.getItems().addAll(GochiType.ROCK, GochiType.SCISSORS, GochiType.PAPER);
        this.typeChoiceBox.setValue(GochiType.ROCK);
        this.getChildren().addAll(
                new Label("Name: "), nameField,
                new Label("Choose type: "), typeChoiceBox,
                new Label("Stat points"), pointsPool
        );
        for (String statName : new String[] {"Attack", "Speed", "Defence", "Stamina"}) {
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
            int [] points = this.calculateStatPoints();
            GotchiDAO.addGotchi(new Gotchi(
                    this.nameField.getText(),
                    this.typeChoiceBox.getValue(),
                    points[0], points[1], points[2], points[3])
            );
            if (checkStatsAllowed(countStats(calculateStatPoints()))){
                if (scene.getRoot() instanceof PrepareBattle) {
                    ((PrepareBattle) scene.getRoot()).createGotchiList();
                }
                primaryStage.setScene(scene);}
            else{
                this.removeExceedWarning(exceedMsg);
                this.addExceedWarning(exceedMsg);
            }
        });
    }

    /*private List<Integer> createStatsArr(StatPoints statsPoints) {
        List<Integer> statsArr = new ArrayList<>();
        statsArr.add(statsPoints.getSpeedPoints());
        statsArr.add(statsPoints.getDefencePoints());
        statsArr.add(statsPoints.getAttackPoints());
        statsArr.add(statsPoints.getStaminaPoints());
        return statsArr;
    }
*/
    private int countStats(int [] statsArr){
        int statSum = 0;
        for(int stat: statsArr){
            statSum +=stat;
        }
        return statSum;
    }

    public boolean checkStatsAllowed(int statSum){
        return statSum <= poolOfPoints ? true : false;
    }


    private int[] calculateStatPoints() {
        int[] statPoints = new int[statValues.size()];
        for (int i = 0; i < statPoints.length; i++) {
            statPoints[i] = Integer.parseInt(statValues.get(i).getText());
        }
        return statPoints;
    }

    private Slider createStatSlider(Text value) {
        Slider slider = new Slider(0, 200, 0);
        slider.getStyleClass().add("slider");
        slider.setOnMouseClicked(Event ->{
            this.removeExceedWarning(exceedMsg);
        });

        slider.valueProperty().addListener((observable, oldValue, newValue) ->{

            value.setText(String.valueOf(newValue.intValue()));
            int sliderSum = countStats(calculateStatPoints());
            pointsPool.setText(Integer.toString(poolOfPoints - sliderSum));

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

    private void addExceedWarning(Text message){
        message.setId("create-gotchi-warning-msg");
        this.getChildren().add(message);
    }

    private void removeExceedWarning(Text message){
        this.getChildren().remove(message);
    }

}
