/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters;

/**
 *
 * @author ilbenjello
 */
public class Canon extends Character {
    private int health;
    private boolean canShot;
    private final static int HEALTH = 5;
    
    public Canon(int speed){
        super(speed);
        this.setSkin("/sample/resources/canon.jpg");
        this.health = HEALTH;
        canShot = true;
    }
    private int getHealth(){
        return health;
    }
    private void setHealth(int health){
        this.health = health;
    }

    public boolean isCanShot(){
        if(canShot){
            canShot = false;
            return true;
        }
        return false;
    }

    public void setCanShot(boolean canShot){
        this.canShot = canShot;
    }
}
