package com.codecool.gochiarena.view;

enum TypeImage {
    ROCK("hbox-enemy-rock", "hbox-gotchi-rock", "vbox-custom-rock"),
    PAPER("hbox-enemy-paper", "hbox-gotchi-paper", "vbox-custom-paper"),
    SCISSORS("hbox-enemy-scissors", "hbox-gotchi-scissors", "vbox-custom-scissors");

    final String imgEnemy;
    final String imgPlayer;
    final String imgChoose;

    private TypeImage(String enemyImgId, String playerImgId, String imgChooseId) {
        this.imgEnemy = enemyImgId;
        this.imgPlayer = playerImgId;
        this.imgChoose = imgChooseId;
    }
}
