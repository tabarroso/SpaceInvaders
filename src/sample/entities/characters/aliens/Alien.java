/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.entities.characters.aliens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.entities.characters.Character;
import sample.game.MedInvaders;

/**
 * Define an alien
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class Alien extends Character {
    /**
     * The path to BIGSKIN resource
     */
    private static final String BIGSKIN = "/sample/resources/Largeinvader.png";
    /**
     * The path to MEDSKIN resource
     */
    private static final String MEDSKIN = "/sample/resources/Mediuminvader.png";
    /**
     * The path to SMALLSKIN resource
     */
    private static final String SMALLSKIN = "/sample/resources/Smallinvader.png";
    /**
     * Not used yet
     */
    private MedInvaders medInvaders;
    /**
     * The alien ImageView
     */
    private ImageView image;

    /**
     * Constructor
     * @param medInvaders
     * @param type
     */
    public Alien(MedInvaders medInvaders, TypeAlien type){
        super();
        switch (type){
            case BIG:this.setSkin(BIGSKIN);
                break;
            case MEDIUM:this.setSkin(MEDSKIN);
                break;
            case SMALL:this.setSkin(SMALLSKIN);
                break;
        }
        this.medInvaders = medInvaders;
        image = new ImageView(new Image(this.getSkin()));
    }

    /**
     *
     * @return the alien's ImageView
     */
    public ImageView getImage() {
        return image;
    }
}
