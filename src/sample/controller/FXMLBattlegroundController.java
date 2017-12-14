/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.model.Game;
import sample.model.entities.characters.Canon;
import sample.model.entities.characters.MedInvaders;
import sample.model.entities.characters.aliens.Alien;
import sample.model.entities.characters.aliens.TypeAlien;

import java.util.ArrayList;

/**
 *
 * @author ilbenjello
 */
public class FXMLBattlegroundController {
    private ImageView canonImage = new ImageView();
    private Canon canon;
    private ArrayList<Alien> aliens;
    private ArrayList<ImageView> aliensImages;
    private MedInvaders mediator;
    private Game game = new Game();
    private Boolean isLaunched = false;
    private ArrayList<TranslateTransition> xTrasitions;
    private ArrayList<TranslateTransition> yTrasitions;
    @FXML
    Pane battleground;

    @FXML
    private void initialize(){
        battleground.setOnMouseClicked(event -> {
            if(!isLaunched){
                isLaunched = true;
                launchGame();
            }
        });
    }

    private void setKeyEvents(){
        TranslateTransition transition = new TranslateTransition();
        battleground.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.RIGHT){

            }
            if(event.getCode() == KeyCode.LEFT){

            }
            if(event.getCode() == KeyCode.UP){

            }
        });
    }

    private void launchGame(){
        initializeCharacters();
        battleground.getChildren().addAll(aliensImages);
    }

    private void initializeCharacters(){
        mediator = new MedInvaders();
        mediator.createInvaders(game.getLevel());
        aliens = mediator.getListAlien();
        aliensImages = mediator.createImages(battleground.getBoundsInParent().getWidth(), battleground.getBoundsInParent().getHeight());
    }

}