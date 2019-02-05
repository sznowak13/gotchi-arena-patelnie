package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends HBox {

    private Button createGotchiBtn = new Button("Create new gotchi");
    private Button refreshList = new Button("Refresh list");
    private VBox gotchiListing = new VBox(15);

    public MainMenu() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(50);
        this.createGotchiList();
        this.setupRefreshButton();
        this.getChildren().addAll(this.gotchiListing, this.createGotchiBtn, this.refreshList);
    }

    private void setupRefreshButton() {
        this.refreshList.setOnAction((event) -> this.createGotchiList());
    }

    private void createGotchiList() {
        this.gotchiListing.getChildren().clear();
        this.gotchiListing.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        this.gotchiListing.getChildren().add(new Text("Available Gotchis: "));
        for (Gotchi gotchi : Gotchi.getAvailableGotchis()) {
            this.gotchiListing.getChildren().add(new Text(gotchi.getName() + " " + gotchi.getType()));
        }
    }

    public void setCreateButtonAction(Stage primaryStage, Scene scene) {
        this.createGotchiBtn.setOnAction((event) -> primaryStage.setScene(scene));
    }
}
