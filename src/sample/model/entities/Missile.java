/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities;

/**
 *
 * @author ilbenjello
 */
public class Missile {
    private String shape;
    private int speed;
    public static final int BASE_MISSILE_SPEED = 5;
    public static final String BASE_SHAPE = "/images/base_missile.png";
    
    public Missile(){
        shape = BASE_SHAPE;
        speed = BASE_MISSILE_SPEED;
    }
}
