package com.codecool.gochiarena.controller;

import com.codecool.gochiarena.model.GochiType;
import com.codecool.gochiarena.model.Gotchi;
import com.codecool.gochiarena.model.GotchiDAO;
import com.codecool.gochiarena.view.GotchiCreationForm;

import java.util.Map;

public class GotchiFormController {

    GotchiCreationForm form;
    GotchiDAO gotchiDAO;

    GotchiFormController(){

    }

    public boolean addNewGotchi(String name, GochiType type, Map<String, Integer> points) {
        if gotchiDAO.addGotchiNameValidation(name);



            return false;
        }


    }
}
