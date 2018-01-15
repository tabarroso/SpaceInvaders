/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.entities;

/**
 *
 * @author ilbenjello
 */
public class Missile {
    private String shape;
    public static final String BASE_SHAPE = "/sample/resources/laser.png";
    
    public Missile(){
        shape = BASE_SHAPE;
    }

    public String getShape() {
        return shape;
    }
}
