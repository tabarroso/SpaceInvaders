/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.entities.characters;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Define the canon
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class Canon extends Character {
    /**
     * A boolean to know if the can can shot
     */
    private boolean canShot;
    /**
     * The number of healths
     */
    private final static int HEALTH = 5;
    /**
     * The canon imageView
     */
    private ImageView image;
    /**
     * The number of healths property
     */
    private IntegerProperty health = new SimpleIntegerProperty();

    /**
     * Constructor
     */
    public Canon(){
        super();
        this.setSkin("/sample/resources/canon.jpg");
        canShot = true;
        image = new ImageView(new Image(this.getSkin()));
        setHealth(HEALTH);
    }

    /**
     *
     * @return the canon's ImageView
     */
    public ImageView getImage() {
        return image;
    }

    /**
     *
     * @return the canon's healths
     */
    public int getHealth() {
        return health.get();
    }

    /**
     *
     * @return the property of the canon's healths
     */
    public IntegerProperty healthProperty() {
        return health;
    }

    /**
     * Set the canon's healths with the value of health
     * @param health
     */
    public void setHealth(int health) {
        this.health.set(health);
    }

    /**
     *
     * @return if the canon can shot
     */
    public boolean isCanShot(){
        return canShot;
    }

    /**
     * Set if the canon can shot
     * @param canShot
     */
    public void setCanShot(boolean canShot){
        this.canShot = canShot;
    }
}
