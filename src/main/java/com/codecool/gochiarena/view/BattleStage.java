package com.codecool.gochiarena.view;

import com.codecool.gochiarena.model.Action;
import com.codecool.gochiarena.model.BattleArena;
import com.codecool.gochiarena.model.Gotchi;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BattleStage extends BorderPane {

    private GotchiInfo playerInfo;
    private EnemyGotchisInfo enemyInfo;
    private ActionChooseView actionChoose = new ActionChooseView();
    private BattleMessageView battleMessageView = new BattleMessageView();
    private BattleArena battleArena = new BattleArena();

    public BattleStage() {
        this.setLeft(actionChoose);
        ScrollPane scroll = createScrollPaneForMessages();
        this.battleMessageView.heightProperty().addListener((observable, oldValue, newValue) -> {
            scroll.setVvalue((double) newValue);
        });
        this.setBottom(scroll);
        this.setCenter(createVerticalContainer());
    }

    public void setGotchiInfo(Gotchi gotchi) {
        this.battleArena.setGotchi1(gotchi);
        this.playerInfo = new GotchiInfo(gotchi);
        this.setTop(playerInfo);
    }

    public void setEnemyInfo(Gotchi gotchi) {
        this.battleArena.setGotchi2(gotchi);
        this.enemyInfo = new EnemyGotchisInfo(gotchi);
        this.setRight(enemyInfo);
    }

    public VBox createVerticalContainer() {
        VBox verticalContainer = new VBox();
        verticalContainer.getChildren().addAll(createContainerForEnemy(), createGotchiContainer());
        return verticalContainer;
    }



    public HBox createContainerForEnemy() {
        HBox enemyContainer = new HBox();
        enemyContainer.setAlignment(Pos.TOP_RIGHT);
        enemyContainer.setPadding(new Insets(10, 0, 150, 10));
        ProgressBar pbRed = new ProgressBar(0.4);
        enemyContainer.getChildren().add(pbRed);
        enemyContainer.setId("hbox-enemy-scissors");
        return enemyContainer;
    }

    public HBox createGotchiContainer() {
        HBox gotchiContainer = new HBox();
        gotchiContainer.setAlignment(Pos.TOP_LEFT);
        gotchiContainer.setPadding(new Insets(10, 0, 150, 10));
        ProgressBar pbRed = new ProgressBar(1);
        gotchiContainer.getChildren().add(pbRed);
        gotchiContainer.setId("hbox-gotchi-scissors");
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
            Gotchi g1 = this.battleArena.getGotchi1();
            Gotchi g2 = this.battleArena.getGotchi2();
            g1.setCurrentAction(Action.PRIMARY_ATTACK);
            g2.setCurrentAction(Action.PRIMARY_ATTACK);
            this.battleMessageView.addNewMessage(this.battleArena.battle());
            this.setGotchiInfo(this.battleArena.getGotchi1());
            this.setEnemyInfo(this.battleArena.getGotchi2());
            System.out.println(g1.getStatPoints().getHealthPoints());
            System.out.println(g2.getStatPoints().getHealthPoints());
        });
    }
}

enum TypeImageEnemy {
    ROCK("hbox-enemy-rock"),
    PAPER("hbox-enemy-paper"),
    SCISSORS("hbox-enemy-scissors");

    final String id;

    private TypeImageEnemy(String id) {
        this.id = id;
    }
}

enum TypeImageGotchi {
    ROCK("hbox-gotchi-rock"),
    PAPER("hbox-gotchi-paper"),
    SCISSORS("hbox-gotchi-scissors");

    final String id;

    private TypeImageGotchi(String id) {
        this.id = id;
    }
}
