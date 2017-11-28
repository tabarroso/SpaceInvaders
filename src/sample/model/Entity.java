package sample.model;

import javafx.beans.property.*;

import java.util.ArrayList;


abstract class Entity {
    private BooleanProperty isAlive = new SimpleBooleanProperty(true);
    private StringProperty skin = new SimpleStringProperty();
    private ArrayList<Position> hitbox;
}
