/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.entities.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.entities.characters.aliens.Alien;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import sample.model.entities.characters.aliens.TypeAlien;

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
    private static final double SPACE = 50;
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
            if (i == idx){
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
            if(i > SMALL && i < MEDIUM){
                listAlien.add(new Alien(this, TypeAlien.MEDIUM, speed));
            }
            if(i > MEDIUM && i < BIG){
                listAlien.add(new Alien(this, TypeAlien.BIG, speed));
            }
        }
    }

    public ArrayList<ImageView> createImages(double x, double y){
        double X = MIN_X, Y = MIN_Y;
        ArrayList<ImageView> alienImageList = new ArrayList<>();
        ImageView alienImage;
        System.out.println(x);
        for (Alien alien : listAlien) {
            X += SPACE;
            alienImage = new ImageView();
            alienImage.setImage(new Image(alien.getSkin()));
            if(X+SPACE > x){
                X = MIN_X;
                Y += SPACE;
            }
            alienImage.setX(X);
            alienImage.setY(Y);
            alienImageList.add(alienImage);
        }
        return alienImageList;
    }

    public ArrayList<Alien> getListAlien(){
        return listAlien;
    }
}
