/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters.aliens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.model.Position;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.animation.TranslateTransition;

import javafx.util.Duration;

/**
 *
 * @author ilbenjello
 */
public class MedInvaders {
    private ArrayList<Alien> listAlien;

    private static final double MIN = 0;
    private static final int ARMY = 55;
    private static final int SMALL = 11;
    private static final int MEDIUM = 33;
    private static final int BIG = 55;
    private static final double SPACE_X = 65;
    private static final double SPACE_Y = 50;
    private static final double MIN_X = 0;
    private static final double MIN_Y = 0;
    public MedInvaders(){
        listAlien = new ArrayList();
    }

    private void queryFireMissile(){
        int idx = (int)Math.random() * (ARMY+1);
        int i = 0;
        Iterator iterator = listAlien.iterator();
        while(iterator.hasNext()){
            Alien alien = (Alien)iterator.next();
            if (i == idx) {
                alien.fireMissile();
            }
            i++;
        }
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

    public ArrayList<ImageView> createImages(double x, double y){
        int cpt = 0;
        Position position = new Position(MIN_X,MIN_Y);
        ArrayList<ImageView> alienImageList = new ArrayList<>();
        ImageView alienImage;
        for (Iterator<Alien> it = listAlien.iterator(); it.hasNext();) {
            Alien alien = it.next();
            alienImage = new ImageView();
            alienImage.setImage(new Image(alien.getSkin()));
            calculatePosition(position, x, cpt);
            alienImage.setX(position.getxPosition());
            alienImage.setY(position.getyPosition());
            alienImageList.add(alienImage);
            cpt ++;
        }
        return alienImageList;
    }

    private void calculatePosition(Position position, double x, int cpt){
        position.setxPosition(position.getxPosition()+SPACE_X);
        if(cpt%11 == 0){
            position.setxPosition(MIN_X);
            position.setyPosition(position.getyPosition()+SPACE_Y);
        }
        if(position.getxPosition()+SPACE_X > x){
            position.setxPosition(MIN_X);
            position.setyPosition(position.getyPosition()+SPACE_Y);
            return;
        }
    }

    public ArrayList<Alien> getListAlien(){
        return listAlien;
    }

    public void initializeTranslation(Pane aliens, Pane battleground){
        TranslateTransition tty = new TranslateTransition(Duration.millis(1500), aliens);
        TranslateTransition ttx = new TranslateTransition(Duration.millis(3500), aliens);
        ttx.setOnFinished(event->{
            if(aliens.getBoundsInParent().getMaxY() <= (battleground.getBoundsInParent().getHeight()/100)*80){
                tty.setByX(0);
                tty.setByY(50);
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
}
