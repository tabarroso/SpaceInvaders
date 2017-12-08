/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.model.entities.characters.aliens.Alien;
import sample.model.entities.characters.aliens.TypeAlien;

/**
 *
 * @author ilbenjello
 */
public class FXMLBattlegroundController {
    @FXML
    Pane battleground;

    @FXML
    private void initialize(){
        Alien alien = new Alien(TypeAlien.BIG);
        ImageView alienImage = new ImageView();
        alienImage.setImage(new Image(alien.getSkin()));
        battleground.getChildren().add(alienImage);
        alien.getPosition().setxPosition(battleground.getBoundsInParent().getWidth());
        alien.getPosition().setyPosition(battleground.getBoundsInParent().getHeight());
        System.out.println(battleground.getBoundsInParent().getWidth());
        System.out.println(battleground.getBoundsInParent().getHeight());
        alienImage.xProperty().bindBidirectional(alien.getPosition().xPositionProperty());
        alienImage.yProperty().bindBidirectional(alien.getPosition().xPositionProperty());
    }
}