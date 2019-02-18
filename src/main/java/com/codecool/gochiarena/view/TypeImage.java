package com.codecool.gochiarena.view;

enum TypeImage {
    WATER("hbox-enemy-rock", "hbox-gotchi-rock", "vbox-custom-rock"),
    GRASS("hbox-enemy-paper", "hbox-gotchi-paper", "vbox-custom-paper"),
    FIRE("hbox-enemy-scissors", "hbox-gotchi-scissors", "vbox-custom-scissors");

    final String imgEnemy;
    final String imgPlayer;
    final String imgChoose;

    private TypeImage(String enemyImgId, String playerImgId, String imgChooseId) {
        this.imgEnemy = enemyImgId;
        this.imgPlayer = playerImgId;
        this.imgChoose = imgChooseId;
    }
}
