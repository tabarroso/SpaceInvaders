/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.entities;

/**
 * Define the missile
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class Missile {
    /**
     * The shape of the missile
     */
    private String shape;
    /**
     * The path to the shape resource
     */
    public static final String BASE_SHAPE = "/sample/resources/laser.png";

    /**
     * Constructor
     */
    public Missile(){
        shape = BASE_SHAPE;
    }

    /**
     *
     * @return the path to the shape resource
     */
    public String getShape() {
        return shape;
    }
}
