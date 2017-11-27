package model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;


abstract class Entity {
    private BooleanProperty isAlive = new SimpleBooleanProperty(true);
    private StringProperty skin = new SimpleStringProperty();
    private ArrayList<Position> hitbox = new ArrayList<>();
}
