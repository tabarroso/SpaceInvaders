/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.entities.characters;

import sample.entities.Missile;

/**
 * Abstract class that define a character
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public abstract class Character{
    /**
     * The path to the character's skin resource
     */
    private String skin;

    /**
     * Constructor
     */
    public Character() {
    }

    /**
     *
     * @return the path to the character's skin resource
     */
    public String getSkin() {
        return skin;
    }

    /**
     * Set the path to the character's skin resource
     * @param skin
     */
    public void setSkin(String skin) {
        this.skin = skin;
    }
}
