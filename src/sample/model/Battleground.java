/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sample.model.entities.characters.Canon;
import sample.model.entities.characters.Invaders;

/**
 *
 * @author ilbenjello
 */
public class Battleground {
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;
    private IntegerProperty height = new SimpleIntegerProperty(HEIGHT);
    
    private Invaders invaders;
    private Canon canon;
    //private DefenseWall defensewalls
    
    public Battleground(){
        invaders = null;
        canon = null;
        //defensewalls = null;
    }
    
    
    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }
}
