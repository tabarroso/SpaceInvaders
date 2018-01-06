/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters.aliens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.entities.characters.Character;

/**
 *
 * @author ilbenjello
 */
public class Alien extends Character {
    private static final String BIGSKIN = "/sample/resources/Largeinvader.png";
    private static final String MEDSKIN = "/sample/resources/Mediuminvader.png";
    private static final String SMALLSKIN = "/sample/resources/Smallinvader.png";
    private MedInvaders medInvaders;
    private ImageView image;

    public ImageView getImage() {
        return image;
    }

    public Alien(MedInvaders medInvaders, TypeAlien type, int speed){
        super(speed);
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
}
