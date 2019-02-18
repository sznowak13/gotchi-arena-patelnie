package com.codecool.gochiarena;

import com.codecool.gochiarena.controller.GotchiFormController;
import com.codecool.gochiarena.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) throws Exception {

        GotchiFormController formController = new GotchiFormController();
        GotchiCreationForm form = new GotchiCreationForm(formController);
        Scene creationScene = new Scene(form, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        PrepareBattle prepareBattle = new PrepareBattle();
        Scene prepareBattleScene = new Scene(prepareBattle, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        MainMenu mainMenu = new MainMenu();
        Scene mainScene = new Scene(mainMenu, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        BattleStage battleStage = new BattleStage();
        Scene battleScene = new Scene(battleStage, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        mainMenu.setId("pane");
        mainMenu.setSinglePlayerBtn(primaryStage, prepareBattleScene);
        mainMenu.setMultiPlayerBtn(primaryStage, prepareBattleScene);
        mainMenu.setCreateButtonAction(primaryStage, creationScene);
        mainMenu.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        mainMenu.getCreateGotchiBtn().getStyleClass().add("my-special-button");
        mainMenu.getSinglePlayerBtn().getStyleClass().add("my-special-button");
        mainMenu.getMultiPlayerBtn().getStyleClass().add("my-special-button");


        prepareBattle.setStartButtonAction(primaryStage, battleScene);
        prepareBattle.setupBackButton(primaryStage, mainScene);
        prepareBattle.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());

        form.setupCreateButton(primaryStage, prepareBattleScene);
        form.setupBackButton(primaryStage, mainScene);
        form.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());

        battleStage.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
