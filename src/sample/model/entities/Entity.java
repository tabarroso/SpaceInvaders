package sample.model.entities;

import javafx.beans.property.*;
import sample.model.Position;
import sample.model.entities.characters.Game;

import java.util.ArrayList;


public abstract class Entity {
    private final StringProperty skin = new SimpleStringProperty();
    private Position Position;
    
    public Entity(){
    }

    public Position getPosition() {
        return Position;
    }

    public void setPosition(Position Position) {
        this.Position = Position;
    }
}
