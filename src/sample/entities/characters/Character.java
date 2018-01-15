/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.entities.characters;

import sample.entities.Missile;

/**
 *
 * @author ilbenjello
 */
public abstract class Character{
    private Missile missile;
    private String skin;

    public Character() {
        super();
        this.missile = null;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
