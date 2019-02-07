package com.codecool.gochiarena;

import com.codecool.gochiarena.view.BattleStage;
import com.codecool.gochiarena.view.GotchiCreationForm;
import com.codecool.gochiarena.view.MainMenu;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("fonts/font.ttf"), 16);
        GotchiCreationForm form = new GotchiCreationForm();
        Scene creationScene = new Scene(form, 640, 480);

        MainMenu mainMenu = new MainMenu();
        Scene mainScene = new Scene(mainMenu, 640, 480);

        BattleStage battleStage = new BattleStage();
        Scene battleScene = new Scene(battleStage, 640, 480);

        form.setupCreateButton(primaryStage, mainScene);
        mainMenu.setCreateButtonAction(primaryStage, creationScene);
        mainMenu.setStartButtonAction(primaryStage, battleScene);

        form.setupBackButton(primaryStage, mainScene);

        mainMenu.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        battleStage.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
