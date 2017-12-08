/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import sample.model.Battleground;
import sample.model.Position;
import sample.model.entities.characters.aliens.Alien;

/**
 *
 * @author ilbenjello
 */
public class FXMLBattlegroundController {
    @FXML 
    private Pane panel;
    //private SimpleObjectProperty<Alien> alien = new SimpleObjectProperty<>(new Alien());
    private SimpleObjectProperty<Battleground> battleground = new SimpleObjectProperty<>(new Battleground());
        
    public void initialize(URL url, ResourceBundle rb){
        //panel.heightProperty().bind(battleground.get());
        battleground.bind(battleground);
        //alien.bind(alien);
    }
}
