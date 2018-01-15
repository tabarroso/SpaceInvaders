/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ilbenjello
 */
public class Canon extends Character {
    private IntegerProperty health = new SimpleIntegerProperty();
    private boolean canShot;
    private final static int HEALTH = 5;
    private ImageView image;

    public ImageView getImage() {
        return image;
    }

    public Canon(){
        super();
        this.setSkin("/sample/resources/canon.jpg");
        canShot = true;
        image = new ImageView(new Image(this.getSkin()));
    }

    public int getHealth() {
        return health.get();
    }

    public IntegerProperty healthProperty() {
        return health;
    }

    public void setHealth(int health) {
        this.health.set(health);
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
