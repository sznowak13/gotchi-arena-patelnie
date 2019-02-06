package com.codecool.gochiarena.model;

import java.util.Scanner;

public class ConsoleInterface {

    private static String[] statPoints = new String[] {"attack", "speed", "defence", "stamina"};

    public static void main(String[] args) {
        System.out.println("Create goczi");
        String name = askForInput("Enter name: ");
        GochiType type = GochiType.valueOf(askForInput("Enter type :"));
        System.out.println("Distribute points: ");
        int pointsPool = 200;
        int[] pointsInput = new int[statPoints.length];
        for (int i = 0; i < pointsInput.length; i++) {
            int points;
            do {
             points = Integer.parseInt(askForInput(statPoints[i]));
            } while (pointsPool - points < 0);
            pointsPool -= points;
            pointsInput[i] = points;
        }
        StatPoints statPoints = new StatPoints(pointsInput);

        Gotchi gotchi = new Gotchi(name, type, statPoints);
        System.out.println(gotchi);
    }

    public static String askForInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
}
