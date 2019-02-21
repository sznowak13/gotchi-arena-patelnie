package com.codecool.gochiarena.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerAI implements Runnable {

    private Gotchi gotchi;
    private boolean playing;
    private PropertyChangeSupport support;

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }

    public Gotchi getGotchi() {
        return gotchi;
    }

    public PlayerAI(Gotchi gotchi) {
        this.support = new PropertyChangeSupport(this);
        this.gotchi = gotchi;
        playing = true;
    }

    @Override
    public void run() {
        try {
            while (playing) {
                if (!gotchi.isReady()) {
                    Thread.sleep(3000);
                    support.firePropertyChange("EnemyReady", null, Action.getRandomAction());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
