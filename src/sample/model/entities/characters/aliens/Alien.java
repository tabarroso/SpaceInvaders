/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters.aliens;

import sample.model.entities.characters.Game;
import sample.model.entities.characters.Character;

/**
 *
 * @author ilbenjello
 */
public class Alien extends Character {
    private TypeAlien type;
    public Alien(Game game, TypeAlien type, int speed){
        super(speed);
        switch(type){
            case BIG: break;
            case MEDIUM: break;
            case SMALL: break;
        }
    }
    
    public void fireMissile(){
        
    }
}
