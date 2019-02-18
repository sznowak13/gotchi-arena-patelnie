package com.codecool.gochiarena.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GotchiDAO {

    private static GotchiDAO instance = new GotchiDAO();

    private List<Gotchi> availableGotchis = new ArrayList<>();

    public static GotchiDAO getInstance() {
        return instance;
    }

    private GotchiDAO() {
        availableGotchis.add(new Gotchi("Goczi1", GochiType.WATER, 10, 10, 10, 10));
        availableGotchis.add(new Gotchi("Goczi2", GochiType.FIRE, 10, 10, 10 ,10));
        availableGotchis.add(new Gotchi("Goczi3", GochiType.GRASS, 10, 10, 10, 10));
    }

    private int countStats(Map<String, Integer> statsMap){
        int statSum = 0;
        for(int stat: statsMap.values()){
            statSum +=stat;
        }
        return statSum;
    }

    public boolean checkStatsAllowed(int statSum){
        return statSum <= Config.MAX_POOL_OF_POINTS;
    }


    public boolean validateName(String name) {
        name = name.trim();
        return name.length() >= 1;
    }

    public boolean validateStatpoints(Map<String, Integer> mapPoints) {
       return checkStatsAllowed(countStats(mapPoints));
    }

    public  Gotchi getRandomGotchi() {
        return availableGotchis.get((int) (Math.random() * availableGotchis.size()));
    }

    public List<Gotchi> getAvailableGotchis() {
        return availableGotchis;
    }


    public void addNewGotchi(Gotchi gotchi){
        availableGotchis.add(gotchi);
    }
}
