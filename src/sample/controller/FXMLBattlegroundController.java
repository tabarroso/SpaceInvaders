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
import sample.model.entities.characters.aliens.Alien;
import sample.model.entities.characters.aliens.MedInvaders;

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
    private Missile missile;
    private ImageView missileImage;
    private MedInvaders mediator;
    private Game game = new Game();
    private Boolean isLaunched = false;
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
        TranslateTransition transition = new TranslateTransition(Duration.millis(6000), canonImage);
        setPressed(transition);
        setReleased(transition);
    }

    private void upShotCmd(KeyEvent event) {
        if(event.getCode() == KeyCode.UP){
            if(canon.isCanShot()){
                canon.setCanShot(false);
                missileImage = new ImageView(new Image(missile.getShape()));
                TranslateTransition missileTr = new TranslateTransition(Duration.millis(6000), missileImage);
                missileImage.setY(canonImage.getBoundsInParent().getMinY());
                missileImage.setX(canonImage.getBoundsInParent().getMinX()+canonImage.getBoundsInParent().getWidth()/2);
                missileImage.translateYProperty().addListener((observable, oldValue, newValue) -> {
                    aliensImages.forEach(alien ->{
                        if(missileImage.getBoundsInParent().intersects(alien.getBoundsInParent().getMinX()+invaders.getBoundsInParent().getMinX(),
                                alien.getBoundsInParent().getMinY()+invaders.getBoundsInParent().getMinY(),
                                alien.getImage().getWidth(),alien.getImage().getHeight())){
                            invaders.getChildren().remove(alien);
                            missileTr.stop();
                            aliensImages.remove(alien);
                            battleground.getChildren().remove(missileImage);
                            canon.setCanShot(true);
                        }
                    });
                });
                missileTr.setOnFinished(event1 -> {
                    battleground.getChildren().remove(missileImage);
                    canon.setCanShot(true);
                });
                battleground.getChildren().add(missileImage);
                missileTr.setByX(0);
                missileTr.setToY(-battleground.getBoundsInParent().getHeight());
                missileTr.play();
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
        missile = new Missile();
    }
}