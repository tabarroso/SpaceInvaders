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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.model.Game;
import sample.model.entities.Missile;
import sample.model.entities.characters.Canon;
import sample.model.entities.characters.MissileShooter;
import sample.model.entities.characters.aliens.Alien;
import sample.model.entities.characters.aliens.MedInvaders;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ilbenjello
 */
public class FXMLBattlegroundController {
    private Canon canon;
    private ArrayList<Alien> aliens;
    private MedInvaders mediator;
    private Game game = new Game();
    private Boolean isLaunched = false;
    private MissileShooter missileShooter;
    @FXML
    Pane battleground;
    @FXML
    Pane invaders;

    @FXML
    private void initialize(){
        battleground.setOnMouseClicked(event -> {
            if(!isLaunched){
                battleground.setStyle("-fx-background-color: #000000;");
                isLaunched = true;
                launchGame();
            }
        });
    }

    private void setKeyEvents(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(6000), canon.getImage());
        setPressed(transition);
        setReleased(transition);
    }

    private void upShotCmd(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            if (canon.isCanShot()) {
                canon.setCanShot(false);
                missileShooter.canonShot(battleground,invaders,canon,mediator.getListAlien());
            }
        }
    }

    private void setReleased(TranslateTransition transition) {
        battleground.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.RIGHT){
                transition.stop();
            }
            if(event.getCode() == KeyCode.LEFT){
                transition.stop();
            }
            upShotCmd(event);
            event.consume();
        });
    }

    private void setPressed(TranslateTransition transition) {
        battleground.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.RIGHT){
                transition.setToX(battleground.getBoundsInParent().getWidth()-canon.getImage().getImage().getWidth());
                transition.setDuration(Duration.millis(6000-6000*(canon.getImage().getBoundsInParent().getMaxX()/battleground.getBoundsInParent().getWidth())));
                transition.play();
            }
            if(event.getCode() == KeyCode.LEFT){
                transition.setToX(battleground.getBoundsInParent().getMinX());
                transition.setDuration(Duration.millis(6000*(canon.getImage().getBoundsInParent().getMaxX()/battleground.getBoundsInParent().getWidth())));
                transition.play();
            }
            event.consume();
        });
    }

    private void launchGame(){
        initializeGame();
        setKeyEvents();
    }

    private void initializeGame(){
        mediator = new MedInvaders();
        mediator.createInvaders(game.getLevel());
        mediator.initializePositions(battleground.getBoundsInParent().getWidth(), battleground.getBoundsInParent().getHeight());
        aliens = mediator.getListAlien();
        canon = new Canon(100);
        canon.getImage().setY(battleground.getBoundsInParent().getHeight() - canon.getImage().getImage().getHeight());
        canon.getImage().setX(0);
        invaders.getChildren().addAll(mediator.getImages());
        battleground.getChildren().add(canon.getImage());
        mediator.initializeTranslation(invaders, battleground);
        missileShooter = new MissileShooter();
        //mediator.initializeShot(battleground,invaders,canon,mediator.getImages(),missileShooter);
    }
}