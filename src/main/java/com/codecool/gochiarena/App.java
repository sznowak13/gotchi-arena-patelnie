package com.codecool.gochiarena;

import com.codecool.gochiarena.controller.BattleArenaController;
import com.codecool.gochiarena.controller.GotchiFormController;
import com.codecool.gochiarena.controller.PrepareBattleController;
import com.codecool.gochiarena.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) throws Exception {

        GotchiFormController formController = new GotchiFormController();
        GotchiCreationForm form = new GotchiCreationForm(formController);
        Scene creationScene = new Scene(form, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        PrepareBattleController prepareBattleController = new PrepareBattleController();
        PrepareBattle prepareBattle = new PrepareBattle();
        prepareBattleController.setPrepareBattleView(prepareBattle);
        Scene prepareBattleScene = new Scene(prepareBattle, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        MainMenu mainMenu = new MainMenu();
        Scene mainScene = new Scene(mainMenu, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        BattleArenaController battleArenaController = new BattleArenaController();
        BattleArenaView battleArenaView = new BattleArenaView();
        battleArenaController.setBattleArenaView(battleArenaView);
        Scene battleScene = new Scene(battleArenaView, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        mainMenu.setId("pane");
        mainMenu.setSinglePlayerBtn(primaryStage, prepareBattleScene);
        mainMenu.setMultiPlayerBtn(primaryStage, prepareBattleScene);
        mainMenu.setCreateButtonAction(primaryStage, creationScene);
        mainMenu.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        mainMenu.getCreateGotchiBtn().getStyleClass().add("my-special-button");
        mainMenu.getSinglePlayerBtn().getStyleClass().add("my-special-button");
        mainMenu.getMultiPlayerBtn().getStyleClass().add("my-special-button");
        mainMenu.addPropertyChangeListener(prepareBattleController);


        prepareBattle.setStartButtonAction(primaryStage, battleScene);
        prepareBattle.setupBackButton(primaryStage, mainScene);
        prepareBattle.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        prepareBattle.addPropertyChangeListener(battleArenaController);

        form.setupCreateButton(primaryStage, mainScene);
        form.setupBackButton(primaryStage, mainScene);
        form.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());

        battleArenaView.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
