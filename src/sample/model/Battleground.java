/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model;

import sample.model.entities.characters.Canon;
import sample.model.entities.characters.MedInvaders;

/**
 *
 * @author ilbenjello
 */
public class Battleground {
    private static final int MAX_HEIGHT = 1000;
    private static final int MAX_WIDTH = 1000;
    private static final int MIN_WIDTH = 0;
    private static final int MIN_HEIGHT = 0;
    
    private MedInvaders medInvaders;
    private Canon canon;
    
    public Battleground(){
        medInvaders = null;
        canon = null;
    }
    
    
    public int getMaxHeight() {
        return MAX_HEIGHT;
    }

    public int getMaxWidth() {
        return MAX_WIDTH;
    }

    public int getMinWidth(){ return MIN_WIDTH; }

    public int getMinHeight(){ return MIN_HEIGHT; }
}
