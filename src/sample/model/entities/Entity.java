package sample.model.entities;

import javafx.beans.property.*;
import sample.model.Position;

import java.util.ArrayList;


public abstract class Entity {
    private BooleanProperty isAlive = new SimpleBooleanProperty(true);
    private StringProperty skin = new SimpleStringProperty();

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
        return skin.get();
    }

    public StringProperty skinProperty() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin.set(skin);
    }
}
