package sample.model.entities.characters;

import java.util.Iterator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.entities.characters.aliens.Alien;
import sample.model.entities.characters.aliens.TypeAlien;
import sample.model.entities.Entity;

public abstract class Game {
    
    private ObservableList<Entity> listAlien;
    
    private static final int ARMY = 55;
    private static final int SMALL = 11;
    private static final int MEDIUM = 33;
    private static final int BIG = 55;
    private static final String skin ="";
    
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;
    public static final String MOVE_Y = "Y";
    public static final String MOVE_X = "X";
    
    public Game(){
        listAlien = FXCollections.observableArrayList();
    }
    private void startLevel(int level){
        createArmy(this, level);
    }
    private void queryFireMissile(){
        int idx = (int)Math.random() * (ARMY+1);
        int i = 0;
        Iterator iterator = listAlien.iterator();
        while(iterator.hasNext()){
            Alien alien = (Alien)iterator.next();
            if (i == idx){
                alien.fireMissile();
            }
            i++;
        }
    }
    
    private void createArmy(Game game, int level){
        int i; 
        int speed = level/10;
        for(i=0; i < ARMY; i++){
            if(i < SMALL){
                listAlien.add(new Alien(game, TypeAlien.SMALL, speed));
            }
            if(i > SMALL && i < MEDIUM){
                listAlien.add(new Alien(game, TypeAlien.MEDIUM, speed));
            }
            if(i > MEDIUM && i < BIG){
                listAlien.add(new Alien(game, TypeAlien.BIG, speed));
            }
        }
    }
    
    public ObservableList<Entity> getListAlien(){
        return listAlien;
    }
}