/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters;

import sample.model.Position;
import sample.model.entities.Missile;

/**
 *
 * @author ilbenjello
 */
public abstract class Character {
    private Missile missile;
    private Position position;
    private boolean canShotMissile;
    private int speed;
    public static final int BASE_SPEED = 5;
    
    public Character(){
        super();
        this.missile = null;
        this.canShotMissile = true;
        this.speed = BASE_SPEED + speed;
    }
    private void fireMissile(){
        if(isCanShotMissile()){
            missile = new Missile();
        }
    }
    private void setCanShotMissile(boolean canShotMissile){
        this.canShotMissile=canShotMissile;
    }
    private void setSpeed(int speed){
        this.speed = speed;
    }
    
    private int getSpeed(){
        return speed;
    }
    private boolean isCanShotMissile(){
        return canShotMissile;
    }
}
