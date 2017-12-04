package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.entites.characters.aliens;

public class Game {
    
    private Invaders invaders;
    
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;
    public static final String MOVE_Y = "Y";
    public static final String MOVE_X = "X";
    
    private StringProperty pseudo = new SimpleStringProperty("default");
    //private ObservableList<Score> scores = FXCollections.observableArrayList<>();

    public String getPseudo() {
        return pseudo.get();
    }

    public StringProperty pseudoProperty() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo.set(pseudo);
    }
    public Game(){
        invaders = new Invaders();
    }
    public void startLevel(int level){
        invaders.createArmy(level);
    }
    public void deplacement(String moove){
        int i;
        for(Alien alien : invaders.getListAlien()) {
            if (moove == MOVE_Y){
                alien.move_Y();
            }
            else{
                alien.move_X();
            }
        }
    }
    
}