package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Action;
import com.codecool.gochiarena.model.BattleArena;
import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Observable;

public class BattleStage extends Observable {

    private BorderPane mainPane;
    private GotchiInfo playerInfo;
    private EnemyGotchisInfo enemyInfo;
    private ActionChooseView actionChoose = new ActionChooseView();
    private BattleMessageView battleMessageView = new BattleMessageView();
    private BattleArena battleArena = new BattleArena();

    public BattleStage() {
        this.mainPane = new BorderPane();
        this.mainPane.setLeft(actionChoose);
        ScrollPane scroll = createScrollPaneForMessages();
        this.battleMessageView.heightProperty().addListener((observable, oldValue, newValue) -> {
            scroll.setVvalue((double) newValue);
        });
        this.mainPane.setBottom(scroll);
    }

    public void setGotchiInfo(Gotchi gotchi) {
        this.battleArena.setGotchi1(gotchi);
        this.playerInfo = new GotchiInfo(gotchi);
        this.mainPane.setTop(playerInfo);
    }

    public void setEnemyInfo(Gotchi gotchi) {
        this.battleArena.setGotchi2(gotchi);
        this.enemyInfo = new EnemyGotchisInfo(gotchi);
        this.mainPane.setRight(enemyInfo);
    }


    public VBox createVerticalContainer() {
        VBox verticalContainer = new VBox();
        String playerImgId = TypeImage.valueOf(battleArena.getGotchi1().getType().toString()).imgPlayer;
        String enemyImgId = TypeImage.valueOf(battleArena.getGotchi2().getType().toString()).imgEnemy;
        verticalContainer.getChildren().addAll(createContainerForEnemy(enemyImgId), createGotchiContainer(playerImgId));
        return verticalContainer;
    }

    public HBox createContainerForEnemy(String id) {
        HBox enemyContainer = new HBox();
        enemyContainer.setAlignment(Pos.TOP_RIGHT);
        enemyContainer.setPadding(new Insets(10, 0, 150, 10));
        ProgressBar pbRed = new ProgressBar(0.4);
        enemyContainer.getChildren().add(pbRed);
        enemyContainer.setId(id);
        return enemyContainer;
    }

    public HBox createGotchiContainer(String id) {
        HBox gotchiContainer = new HBox();
        gotchiContainer.setAlignment(Pos.TOP_LEFT);
        gotchiContainer.setPadding(new Insets(10, 0, 150, 10));
        ProgressBar pbRed = new ProgressBar(1);
        gotchiContainer.getChildren().add(pbRed);
        gotchiContainer.setId(id);
        return gotchiContainer;
    }

    public ScrollPane createScrollPaneForMessages() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(battleMessageView);
        return scrollPane;
    }

    public BattleMessageView getBattleMessageView() {
        return battleMessageView;
    }

    public void setupReadyButton() {
        this.actionChoose.getReadyButton().setOnAction(event -> {
            this.notifyObservers("Player ready");
        });
    }

    public Parent getMainPane() {
        return mainPane;
    }
}

