package com.codecool.gochiarena.controller;

import com.codecool.gochiarena.model.GotchiDAO;
import com.codecool.gochiarena.view.PrepareBattle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PrepareBattleController implements PropertyChangeListener {

    private PrepareBattle prepareBattleView;
    private GotchiDAO gotchiDAO = GotchiDAO.getInstance();

    public void setPrepareBattleView(PrepareBattle prepareBattleView) {
        this.prepareBattleView = prepareBattleView;
        this.prepareBattleView.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("SinglePlayer".equals(evt.getPropertyName())) {
            this.prepareBattleView.createGotchiList(gotchiDAO.getAvailableGotchis());
        }
    }
}
