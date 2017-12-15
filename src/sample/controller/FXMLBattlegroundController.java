/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.model.Game;
import sample.model.entities.characters.Canon;
import sample.model.entities.characters.MedInvaders;
import sample.model.entities.characters.aliens.Alien;

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
        TranslateTransition transition = new TranslateTransition(Duration.millis(1), canonImage);
        battleground.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.RIGHT){
                transition.setByX(20);
                transition.play();
            }
            if(event.getCode() == KeyCode.LEFT){
                transition.setByX(-50);
                transition.play();
            }
            if(event.getCode() == KeyCode.UP){

            }
            event.consume();
        });
        battleground.setOnKeyReleased(event -> {
            transition.stop();
        });
    }

    private void launchGame(){
        initializeCharacters();
        battleground.getChildren().addAll(aliensImages);
        battleground.getChildren().add(canonImage);
        setKeyEvents();
    }

    private void initializeCharacters(){
        mediator = new MedInvaders();
        mediator.createInvaders(game.getLevel());
        aliens = mediator.getListAlien();
        aliensImages = mediator.createImages(battleground.getBoundsInParent().getWidth(), battleground.getBoundsInParent().getHeight());
        canon = new Canon(10);
        canonImage = new ImageView();
        canonImage.setImage(new Image(canon.getSkin()));
        canonImage.setY(battleground.getBoundsInParent().getHeight() - canonImage.getImage().getHeight());
        canonImage.setX((battleground.getBoundsInParent().getWidth() - canonImage.getImage().getWidth())/2);
    }
}