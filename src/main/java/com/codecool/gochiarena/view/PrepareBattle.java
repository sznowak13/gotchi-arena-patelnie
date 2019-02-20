package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import com.codecool.gochiarena.model.GotchiDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class PrepareBattle extends BorderPane {

    private Button selectGotchi = new Button("Battle!");
    private VBox gotchiListing = new VBox();
    private ToggleGroup gotchiToggle = new ToggleGroup();
    private Button backButton = new Button("Back to main menu");
    private PropertyChangeSupport support;

    public PrepareBattle() {
        this.support = new PropertyChangeSupport(this);
        this.setLeft(this.gotchiListing);
        this.setRight(this.selectGotchi);
        this.setBottom(this.createHorizontalFieldForButtons());
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void createGotchiList(List<Gotchi> gotchiList) {
        this.gotchiListing.getChildren().clear();
        this.gotchiListing.setId("choose-gotchi-border");
        this.gotchiListing.getChildren().add(new Text("Available Gotchis: "));
        for (Gotchi gotchi : gotchiList) {
            RadioButton listItem = new RadioButton(gotchi.getName() + " " + gotchi.getType());
            listItem.setUserData(gotchi);
            listItem.setToggleGroup(this.gotchiToggle);
            listItem.setOnMouseClicked(event -> this.setCenter(createVerticalFieldForType(TypeImage.valueOf(gotchi.getType().toString()).imgChoose)));
            this.gotchiListing.getChildren().add(listItem);
        }
        this.gotchiToggle.selectToggle(this.gotchiToggle.getToggles().get(0));
    }

    public void setStartButtonAction(Stage primaryStage, Scene scene) {
        this.selectGotchi.setOnAction((event) -> {
            support.firePropertyChange("BeginBattle", null, this.gotchiToggle.getSelectedToggle().getUserData());
            primaryStage.setScene(scene);
        });
    }

    public void setupBackButton(Stage primaryStage, Scene scene) {
        this.backButton.setOnAction((event) -> {
            primaryStage.setScene(scene);
        });
    }

    private HBox createHorizontalFieldForButtons(){
        HBox horizontalFieldForButtons = new HBox();
        horizontalFieldForButtons.setId("buttons-border");
        horizontalFieldForButtons.getChildren().addAll(backButton, selectGotchi);
        return horizontalFieldForButtons;
    }

    private VBox createVerticalFieldForType(String id) {
        VBox verticalField = new VBox();
        verticalField.setId(id);
        return verticalField;
    }

}

