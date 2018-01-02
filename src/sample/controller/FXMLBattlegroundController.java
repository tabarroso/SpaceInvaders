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
import sample.model.entities.characters.aliens.MedInvaders;
import sample.model.entities.characters.aliens.Alien;

import java.util.ArrayList;
import sample.model.entities.characters.XYTransition;

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
    private ArrayList<XYTransition> alienXYTT;
    @FXML
    Pane battleground;
    @FXML
    Pane invaders;

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
        TranslateTransition transition = new TranslateTransition(Duration.millis(6000), canonImage);
        setPressed(transition);
        setReleased(transition);
        setTyped();
    }

    private void setTyped() {
        battleground.setOnKeyTyped(event -> {
            if(event.getCode() == KeyCode.UP){

            }
        });
    }

    private void setReleased(TranslateTransition transition) {
        battleground.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.RIGHT){
                transition.stop();
            }
            if(event.getCode() == KeyCode.LEFT){
                transition.stop();
            }
            event.consume();
        });
    }

    private void setPressed(TranslateTransition transition) {
        battleground.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.RIGHT){
                transition.setToX(battleground.getBoundsInParent().getWidth()-canonImage.getImage().getWidth());
                transition.setDuration(Duration.millis(6000-6000*(canonImage.getBoundsInParent().getMaxX()/battleground.getBoundsInParent().getWidth())));
                transition.play();
            }
            if(event.getCode() == KeyCode.LEFT){
                transition.setToX(battleground.getBoundsInParent().getMinX());
                transition.setDuration(Duration.millis(6000*(canonImage.getBoundsInParent().getMaxX()/battleground.getBoundsInParent().getWidth())));
                transition.play();
            }
            event.consume();
        });
    }

    private void launchGame(){
        initializeCharacters();
        setKeyEvents();
    }

    private void initializeCharacters(){
        mediator = new MedInvaders();
        mediator.createInvaders(game.getLevel());
        aliens = mediator.getListAlien();
        canon = new Canon(100);
        canonImage = new ImageView(new Image(canon.getSkin()));
        canonImage.setY(battleground.getBoundsInParent().getHeight() - canonImage.getImage().getHeight());
        canonImage.setX(0);
        aliensImages = mediator.createImages(battleground.getBoundsInParent().getWidth(), battleground.getBoundsInParent().getHeight());
        invaders.getChildren().addAll(aliensImages);
        battleground.getChildren().add(canonImage);
        mediator.initializeTranslation(invaders, battleground);
    }
}