/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters.aliens;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.model.Battleground;
import sample.model.Position;

import java.util.*;

import javafx.animation.TranslateTransition;

import javafx.util.Duration;
import sample.model.entities.characters.Canon;
import sample.model.entities.characters.MissileShooter;

/**
 *
 * @author ilbenjello
 */
public class MedInvaders {
    private ArrayList<Alien> listAlien;
    private static final int ARMY = 55;
    private static final int SMALL = 11;
    private static final int MEDIUM = 33;
    private static final int BIG = 55;
    private static final double Y_TRANS = 55;

    public MedInvaders(){
        listAlien = new ArrayList();
    }

    public void createInvaders(int level){
        int i;
        int speed = level/10;
        for(i=0; i < ARMY; i++){
            if(i < SMALL){
                listAlien.add(new Alien(this, TypeAlien.SMALL, speed));
            }
            if(i >= SMALL && i < MEDIUM){
                listAlien.add(new Alien(this, TypeAlien.MEDIUM, speed));
            }
            if(i >= MEDIUM && i < BIG){
                listAlien.add(new Alien(this, TypeAlien.BIG, speed));
            }
        }
    }

    public void initializePositions(GridPane invaders, int nbCol, int nbRow){
        int col = 1, row = 1;
        ImageView alienImage;
        for (Iterator<Alien> it = listAlien.iterator(); it.hasNext();) {
            if(row > nbRow) return;
            if(col > nbCol) {
                col = 1;
                row++;
            }
            Alien alien = it.next();
            alienImage = alien.getImage();
            invaders.add(alienImage, col, row);
            col++;
        }
    }

    public ArrayList<ImageView> getImages(){
        ArrayList<ImageView> images = new ArrayList<>();
        for (Iterator<Alien> it = listAlien.iterator(); it.hasNext();){
            Alien alien = it.next();
            images.add(alien.getImage());
        }
        return images;
    }

    public ArrayList<Alien> getListAlien(){
        return listAlien;
    }

    public void initializeTranslation(GridPane aliens, Pane battleground){
        TranslateTransition tty = new TranslateTransition(Duration.millis(1500), aliens);
        TranslateTransition ttx = new TranslateTransition(Duration.millis(3500), aliens);
        ttx.setOnFinished(event->{
            if(aliens.getBoundsInParent().getMaxY() <= (battleground.getBoundsInParent().getHeight()/100)*80){
                tty.setByX(0);
                tty.setByY(battleground.getBoundsInParent().getHeight()/40);
                tty.setAutoReverse(true);
                tty.play();
            }
            else{
                tty.setByX(0);
                tty.setByY(0);
                tty.setAutoReverse(true);
                tty.play();
            }
        });
        tty.setOnFinished(event -> {
            ttx.setByY(0);
            if(aliens.getBoundsInParent().getMinX() == 0){
                ttx.setToX(battleground.getBoundsInParent().getWidth()-aliens.getBoundsInParent().getWidth());
            }
            else{
                ttx.setToX(battleground.getBoundsInParent().getMinX());
            }
            ttx.play();
        });
        ttx.setToX(battleground.getBoundsInParent().getWidth()-aliens.getBoundsInParent().getWidth());
        ttx.play();
    }

    public void initializeShot(Pane battleground, GridPane invaders, Canon canon, ArrayList<ImageView> listImages, MissileShooter missileShooter){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), ev -> {
            Random rn = new Random();
            int answer = rn.nextInt(ARMY-1) + 1;
            ImageView alienImage = listImages.get(answer);
            missileShooter.alienShot(battleground,invaders,canon,alienImage);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
