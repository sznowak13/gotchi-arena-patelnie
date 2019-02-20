package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BattleArenaView extends BorderPane {

    private GotchiInfo playerInfo;
    private EnemyGotchisInfo enemyInfo;
    private ActionChooseView actionChoose = new ActionChooseView();
    private BattleMessageView battleMessageView = new BattleMessageView();
    private PropertyChangeSupport support;

    public BattleArenaView() {
        this.support = new PropertyChangeSupport(this);
        this.setLeft(actionChoose);
        ScrollPane scroll = createScrollPaneForMessages();
        this.battleMessageView.heightProperty().addListener((observable, oldValue, newValue) -> {
            scroll.setVvalue((double) newValue);
        });
        this.setBottom(scroll);
        this.setupReadyButton();
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }

    public void setupGotchisView(Gotchi player, Gotchi enemy) {
        setGotchiInfo(player);
        setEnemyInfo(enemy);
        this.setCenter(createVerticalContainer(player, enemy));
    }

    private void setGotchiInfo(Gotchi gotchi) {
        this.playerInfo = new GotchiInfo(gotchi);
        this.setTop(playerInfo);
    }

    private void setEnemyInfo(Gotchi gotchi) {
        this.enemyInfo = new EnemyGotchisInfo(gotchi);
        this.setRight(enemyInfo);
    }


    private VBox createVerticalContainer(Gotchi player, Gotchi enemy) {
        VBox verticalContainer = new VBox();
        String playerImgId = TypeImage.valueOf(player.getType().toString()).imgPlayer;
        String enemyImgId = TypeImage.valueOf(enemy.getType().toString()).imgEnemy;
        verticalContainer.getChildren().addAll(createContainerForEnemy(enemyImgId), createGotchiContainer(playerImgId));
        return verticalContainer;
    }

    private HBox createContainerForEnemy(String id) {
        HBox enemyContainer = new HBox();
        enemyContainer.setAlignment(Pos.TOP_RIGHT);
        enemyContainer.setPadding(new Insets(10, 0, 150, 10));
        ProgressBar pbRed = new ProgressBar(0.4);
        enemyContainer.getChildren().add(pbRed);
        enemyContainer.setId(id);
        return enemyContainer;
    }

    private HBox createGotchiContainer(String id) {
        HBox gotchiContainer = new HBox();
        gotchiContainer.setAlignment(Pos.TOP_LEFT);
        gotchiContainer.setPadding(new Insets(10, 0, 150, 10));
        ProgressBar pbRed = new ProgressBar(1);
        gotchiContainer.getChildren().add(pbRed);
        gotchiContainer.setId(id);
        return gotchiContainer;
    }

    private ScrollPane createScrollPaneForMessages() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(battleMessageView);
        return scrollPane;
    }

    private BattleMessageView getBattleMessageView() {
        return battleMessageView;
    }

    public void setupReadyButton() {
        this.actionChoose.getReadyButton().setOnAction(event -> {
            support.firePropertyChange("Player Ready", false, true);
        });
    }

    public void setEnemyReady() {
        this.enemyInfo.setReadyBox(true);
    }
}

