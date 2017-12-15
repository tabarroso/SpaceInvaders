/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters;

import javafx.animation.TranslateTransition;

/**
 *
 * @author ilbenjello
 */
public class XYTransition {
    private TranslateTransition ttx;
    private TranslateTransition tty;
    
    public XYTransition(TranslateTransition ttx, TranslateTransition tty){
        this.ttx = ttx;
        this.tty = tty;
    }
    public TranslateTransition getTtx() {
        return ttx;
    }

    public TranslateTransition getTty() {
        return tty;
    }
}
