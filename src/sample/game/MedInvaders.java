/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.*;

import javafx.util.Duration;
import sample.entities.characters.aliens.Alien;
import sample.entities.characters.aliens.TypeAlien;

/**
 *
 * @author ilbenjello
 */
public class MedInvaders {
    private ObservableList<Alien> listAlien = FXCollections.observableArrayList();
    private ListProperty<Alien> listAlienProperty = new SimpleListProperty<>(listAlien);
    private static final int ARMY = 55;
    private static final int SMALL = 11;
    private static final int MEDIUM = 33;
    private static final int BIG = 55;

    public MedInvaders(){
    }


    public void createInvaders(){
        if(listAlien.size() == 0) {
            for (int i = 0; i < ARMY; i++) {
                if (i < SMALL) {
                    listAlien.add(new Alien(this, TypeAlien.SMALL));
                }
                if (i >= SMALL && i < MEDIUM) {
                    listAlien.add(new Alien(this, TypeAlien.MEDIUM));
                }
                if (i >= MEDIUM && i < BIG) {
                    listAlien.add(new Alien(this, TypeAlien.BIG));
                }
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

    public void queryShot(ArrayList<ImageView> listImages, MissileShooter missileShooter, int level){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4-(0.05*level)), event -> {
            Random rn = new Random();
            int bound = listAlien.size();
            if(bound > 0) {
                int answer = rn.nextInt(bound);
                if (answer <= listAlien.size()) {
                    ImageView alienImage = listImages.get(answer);
                    missileShooter.alienShot(alienImage, level);
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public ObservableList<Alien> getListAlienProperty() {
        return listAlienProperty.get();
    }

    public ListProperty<Alien> listAlienProperty() {
        return listAlienProperty;
    }

    public void setListAlienProperty(ObservableList<Alien> listAlienProperty) {
        this.listAlienProperty.set(listAlienProperty);
    }
}
