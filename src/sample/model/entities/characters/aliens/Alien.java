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
    private static final String BIGSKIN = "/sample/resources/Largeinvader.png";
    private static final String MEDSKIN = "/sample/resources/Mediuminvader.png";
    private static final String SMALLSKIN = "/sample/resources/Smallinvader.png";

    public Alien(Game game, TypeAlien type, int speed){
        super(speed);
        switch (type){
            case BIG:this.setSkin(BIGSKIN);
            case MEDIUM:this.setSkin(MEDSKIN);
            case SMALL:this.setSkin(SMALLSKIN);
        }
    }

    public Alien(TypeAlien type){
        super(2);
        switch (type){
            case BIG:this.setSkin(BIGSKIN);
            case MEDIUM:this.setSkin(MEDSKIN);
            case SMALL:this.setSkin(SMALLSKIN);
        }
    }
    
    public void fireMissile(){
        
    }
}
