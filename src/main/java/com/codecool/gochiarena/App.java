package com.codecool.gochiarena;

import com.codecool.gochiarena.view.BattleStage;
import com.codecool.gochiarena.view.GotchiCreationForm;
import com.codecool.gochiarena.view.MainMenu;
import com.codecool.gochiarena.view.ViewConfig;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) throws Exception {
        GotchiCreationForm form = new GotchiCreationForm();
        Scene creationScene = new Scene(form, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        MainMenu mainMenu = new MainMenu();
        Scene mainScene = new Scene(mainMenu, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        BattleStage battleStage = new BattleStage();
        Scene batteScene = new Scene(battleStage, ViewConfig.WIDTH, ViewConfig.HEIGHT);

        form.setupCreateButton(primaryStage, mainScene);
        mainMenu.setCreateButtonAction(primaryStage, creationScene);
        mainMenu.setStartButtonAction(primaryStage, batteScene);

        form.setupBackButton(primaryStage, mainScene);


        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
