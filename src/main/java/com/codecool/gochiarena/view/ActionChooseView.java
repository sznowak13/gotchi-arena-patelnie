package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Action;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ActionChooseView extends VBox {

    private ToggleGroup actionsGroup = new ToggleGroup();
    private Button readyButton = new Button("Ready!");

    ActionChooseView() {
        this.setId("action-choose");
        this.getChildren().add(new Text("Choose your gotchi action!"));
        for (Action action: Action.values()) {
            RadioButton choice = new RadioButton(action.toString().toLowerCase());
            choice.setToggleGroup(actionsGroup);
            this.getChildren().add(choice);
        }
        this.getChildren().add(readyButton);
        this.actionsGroup.selectToggle(this.actionsGroup.getToggles().get(0));
    }

    public Button getReadyButton() {
        return this.readyButton;
    }
}
