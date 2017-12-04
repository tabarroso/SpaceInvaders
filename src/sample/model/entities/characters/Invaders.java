/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters;

import sample.model.entities.characters.aliens.Alien;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 *
 * @author ilbenjello
 */
public class Invaders {
    private ObservableList<Alien> listAlien;
    
    public static final int ARMY = 55;
    public static final int SMALL = 11;
    public static final int MEDIUM = 33;
    public static final int BIG = 55;
    public Invaders(){
        listAlien = FXCollections.observableArrayList();
    }
    
    public void createArmy(int level){
        int i; 
        int speed = level/10;
        for(i=0; i < ARMY; i++){
            if(i < SMALL){
                listAlien.add(new Alien(TYPEALIEN.SMALL, speed));
            }
            if(i > SMALL && i < MEDIUM){
                listAlien.add(new Alien(TYPEALIEN.MEDIUM, speed));
            }
            if(i > MEDIUM && i < BIG){
                listAlien.add(new Alien(TPEALIEN.BIG, speed));
            }
        }
    }
    public ObservableList<Alien> getListAlien(){
        return this.listAlien;
    }
}
