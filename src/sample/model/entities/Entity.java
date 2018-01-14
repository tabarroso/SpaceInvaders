package sample.model.entities;

import javafx.beans.property.*;


public abstract class Entity {
    private BooleanProperty isAlive = new SimpleBooleanProperty(true);
    private String skin;

    public boolean isIsAlive() {
        return isAlive.get();
    }

    public BooleanProperty isAliveProperty() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive.set(isAlive);
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
