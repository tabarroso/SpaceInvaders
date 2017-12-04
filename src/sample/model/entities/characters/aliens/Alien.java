/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters.aliens;

import sample.model.entities.characters.Character;

/**
 *
 * @author ilbenjello
 */
public class Alien extends Character {
    private TypeAlien type;
    public Alien(TypeAlien type, int speed){
        super(speed);
        this.type = type;
    }
}
