package com.codecool.gochiarena.controller;

import com.codecool.gochiarena.model.Config;
import com.codecool.gochiarena.model.GochiType;
import com.codecool.gochiarena.model.Gotchi;
import com.codecool.gochiarena.model.GotchiDAO;
import com.codecool.gochiarena.view.GotchiCreationForm;

import java.util.Map;

public class GotchiFormController {

    GotchiCreationForm form;
    GotchiDAO gotchiDAO = GotchiDAO.getInstance();

    public String addNewGotchi(String name, GochiType type, Map<String, Integer> points) {
        String error = null;
        if (!gotchiDAO.validateName(name)) {
            error = "Incorrect name!";
        } else if (!gotchiDAO.validateStatpoints(points)) {
            error = "Too much stat points!";
        }
        if (error == null) {
            gotchiDAO.addNewGotchi(new Gotchi(name, type, points));
        }

        return error;
    }

    public String calculatePointsPool(Map<String, Integer> statValues) {
        int statSum = sumStatValues(statValues);
        return Integer.toString(Config.MAX_POOL_OF_POINTS - statSum);
    }

    private int sumStatValues(Map<String, Integer> statValues) {
        int sum = 0;
        for (int val : statValues.values()) {
            sum += val;
        }
        return sum;
    }
}
