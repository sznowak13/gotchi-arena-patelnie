package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.GochiType;
import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrepareBattle extends BorderPane {
    private Button selectGotchi = new Button("Battle!");
    private VBox gotchiListing = new VBox(15);
    private ToggleGroup gotchiToggle = new ToggleGroup();
    private Button backButton = new Button("Back to main menu");

    public PrepareBattle() {
        this.createGotchiList();
        this.setLeft(this.gotchiListing);
        this.setRight(this.selectGotchi);
        this.setBottom(this.createHorizontalFieldForButtons());
    }

    public void createGotchiList() {
        this.gotchiListing.getChildren().clear();
        this.gotchiListing.setBackground(new Background(new BackgroundFill(Color.LIGHTSEAGREEN, null, null)));
        this.gotchiListing.setPadding(new Insets(0, 15, 0, 15));
        this.gotchiListing.setAlignment(Pos.CENTER_LEFT);
        this.gotchiListing.getChildren().add(new Text("Available Gotchis: "));
        for (Gotchi gotchi : Gotchi.getAvailableGotchis()) {
            RadioButton listItem = new RadioButton(gotchi.getName() + " " + gotchi.getType());
            listItem.setUserData(gotchi);
            listItem.setToggleGroup(this.gotchiToggle);
            listItem.setOnMouseClicked(event -> {
                this.setCenter(createVerticalFieldForType(TypeImage.valueOf(gotchi.getType().toString()).id));
            });
            this.gotchiListing.getChildren().add(listItem);
        }
        this.gotchiToggle.selectToggle(this.gotchiToggle.getToggles().get(0));
    }

    public void setStartButtonAction(Stage primaryStage, Scene scene) {
        this.selectGotchi.setOnAction((event) -> {
            if (scene.getRoot() instanceof BattleStage) {
                BattleStage battleStage = (BattleStage) scene.getRoot();
                battleStage.setGotchiInfo((Gotchi) this.gotchiToggle.getSelectedToggle().getUserData());
                battleStage.setEnemyInfo(Gotchi.getRandomGotchi());
                battleStage.getBattleMessageView().addNewMessage("Battle starts!");
                battleStage.setupReadyButton();

            }
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
        horizontalFieldForButtons.setBackground(new Background(new BackgroundFill(Color.LIGHTSEAGREEN, null, null)));
        horizontalFieldForButtons.setPadding(new Insets(0, 0, 0, 2)); // <----- looks like magic number and it is
        horizontalFieldForButtons.setSpacing(ViewConfig.WIDTH/3);
        horizontalFieldForButtons.getChildren().addAll(backButton, selectGotchi);
        return horizontalFieldForButtons;
    }

    private VBox createVerticalFieldForType(String id) {
        VBox verticalField = new VBox();
        verticalField.getStyleClass().add("vbox");
        verticalField.setId(id);
        return verticalField;
    }

}

enum TypeImage {
    ROCK("vbox-custom-rock"),
    PAPER("vbox-custom-paper"),
    SCISSORS("vbox-custom-scissors");

    final String id;

    private TypeImage(String id) {
        this.id = id;
    }
}
