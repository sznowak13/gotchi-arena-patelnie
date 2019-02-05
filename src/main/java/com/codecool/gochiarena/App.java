package com.codecool.gochiarena;

import com.codecool.gochiarena.view.CreateGotchiForm;
import com.codecool.gochiarena.view.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) throws Exception {
        CreateGotchiForm form = new CreateGotchiForm();
        Scene scene2 = new Scene(form, 640, 480);

        MainMenu mainMenu = new MainMenu();
        mainMenu.setCreateButtonAction(primaryStage, scene2);
        Scene mainScene = new Scene(mainMenu, 640, 480);

        form.setupCreateButton(primaryStage, mainScene);


        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
