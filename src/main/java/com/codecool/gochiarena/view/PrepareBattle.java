package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        this.setCenter(this.selectGotchi);
        this.setBottom(this.backButton);
    }

    public void createGotchiList() {
        this.gotchiListing.getChildren().clear();
        this.gotchiListing.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        this.gotchiListing.getChildren().add(new Text("Available Gotchis: "));
        for (Gotchi gotchi : Gotchi.getAvailableGotchis()) {
            RadioButton listItem = new RadioButton(gotchi.getName() + " " + gotchi.getType());
            listItem.setUserData(gotchi);
            listItem.setToggleGroup(this.gotchiToggle);
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

    public void setupBackButton(Stage primaryStage, Scene scene){
        this.backButton.setOnAction((event) -> {
            primaryStage.setScene(scene);
        });
    }
}
