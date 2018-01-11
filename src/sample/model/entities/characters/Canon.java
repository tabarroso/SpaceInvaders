/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ilbenjello
 */
public class Canon extends Character {
    private int health;
    private boolean canShot;
    private final static int HEALTH = 5;
    private ImageView image;

    public ImageView getImage() {
        return image;
    }

    public Canon(int speed){
        super(speed);
        this.setSkin("/sample/resources/canon.jpg");
        this.health = HEALTH;
        canShot = true;
        image = new ImageView(new Image(this.getSkin()));
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
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
