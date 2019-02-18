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
            error = "Incorrect name";
        } else if (!gotchiDAO.validateStatpoints(points)) {
            error = "Too much stat points";
        }
        if (error == null) {
            gotchiDAO.addNewGotchi(new Gotchi(name, type, points));
        }

        return error;
    }

    public boolean addNewGotchi(String name, GochiType type, Map<String, Integer> points) {
        if gotchiDAO.addGotchiNameValidation(name);



            return false;
        }


    }
}
