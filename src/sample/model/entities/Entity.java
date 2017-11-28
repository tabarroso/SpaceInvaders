package sample.model.entities;

import javafx.beans.property.*;
import sample.model.Position;

import java.util.ArrayList;


abstract class Entity {
    private BooleanProperty isAlive = new SimpleBooleanProperty(true);
    private StringProperty skin = new SimpleStringProperty();
    private ArrayList<Position> hitbox;
}
