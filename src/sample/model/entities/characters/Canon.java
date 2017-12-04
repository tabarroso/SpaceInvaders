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

    public final static int HEALTH = 5;
    
    public Canon(int speed){
        super();
        this.health = HEALTH;
    }
    private int getHealth(){
        return health;
    }
    private void setHealth(int health){
        this.health = health;
    }
}
