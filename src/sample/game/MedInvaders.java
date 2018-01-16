/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.game;

import java.util.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import sample.entities.characters.aliens.Alien;
import sample.entities.characters.aliens.TypeAlien;

/**
 * Manage the list of Aliens
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class MedInvaders {
    /**
     * Observable of the listALien
     */
    private ObservableList<Alien> listAlien = FXCollections.observableArrayList();
    /**
     * ListALienProperty
     */
    private ListProperty<Alien> listAlienProperty = new SimpleListProperty<>(listAlien);
    /**
     * Number of Aliens in the Army
     */
    private static final int ARMY = 55;
    /**
     * 11 SMALL Aliens
     */
    private static final int SMALL = 11;
    /**
     * 22 Medium Aliens
     */
    private static final int MEDIUM = 33;
    /**
     * 22 BIG Aliens
     */
    private static final int BIG = 55;

    public MedInvaders(){
    }
    /**
     * Creates the list of aliens for the game
     */
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
    /**
     * Initializes the positions of the Aliens
     * @param invaders The GridPane, where the invaders are placed
     * @param nbCol Number of colums of the gridPane
     * @param nbRow Number of rows of the GridPane
     */
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
    /**
     * 
     * @return the List of Image Views
     */
    public ArrayList<ImageView> getImages(){
        ArrayList<ImageView> images = new ArrayList<>();
        for (Iterator<Alien> it = listAlien.iterator(); it.hasNext();){
            Alien alien = it.next();
            images.add(alien.getImage());
        }
        return images;
    }
    /**
     * Initializes a timed event that makes a random Alien shot a missile
     * @param listImages List of ImageViews
     * @param missileShooter Instance of MissileShooter
     * @param level The Current level
     */
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
    /**
     * 
     * @return The List Alien Property
     */
    public ObservableList<Alien> getListAlienProperty() {
        return listAlienProperty.get();
    }
    /**
     * 
     * @return listAlienProperty
     */
    public ListProperty<Alien> listAlienProperty() {
        return listAlienProperty;
    }
}
