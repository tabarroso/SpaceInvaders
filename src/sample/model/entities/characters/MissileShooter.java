package sample.model.entities.characters;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.model.entities.Missile;
import sample.model.entities.characters.aliens.Alien;

import java.util.ArrayList;
import java.util.Iterator;

public class MissileShooter {
    private final static double MIN = 0.0;

    public MissileShooter(){
    }

    public void canonShot(Pane battleground, GridPane invaders, Canon canon, ArrayList<Alien> aliens){
        Missile missile = new Missile();
        ImageView missileImage = new ImageView(new Image(missile.getShape()));
        TranslateTransition missileTr = new TranslateTransition(Duration.millis(6000), missileImage);
        missileImage.setY(canon.getImage().getBoundsInParent().getMinY());
        missileImage.setX(canon.getImage().getBoundsInParent().getMinX() + canon.getImage().getBoundsInParent().getWidth() / 2);
        missileImage.translateYProperty().addListener((observable, oldValue, newValue) -> {
            for (Iterator<Alien> it = aliens.iterator(); it.hasNext(); ) {
                Alien alien = it.next();
                ImageView alienImage = alien.getImage();
                if (missileImage.getBoundsInParent().intersects(alienImage.getBoundsInParent().getMinX() + invaders.getBoundsInParent().getMinX(),
                        alienImage.getBoundsInParent().getMinY() + invaders.getBoundsInParent().getMinY(),
                        alienImage.getImage().getWidth(), alienImage.getImage().getHeight())) {
                    invaders.getChildren().remove(alienImage);
                    missileTr.stop();
                    it.remove();
                    battleground.getChildren().remove(missileImage);
                    canon.setCanShot(true);
                }
                if(missileImage.getBoundsInParent().getMinY() <= MIN){
                    missileTr.stop();
                    battleground.getChildren().remove(missileImage);
                    canon.setCanShot(true);
                }
            }
        });
        battleground.getChildren().add(missileImage);
        missileTr.setByX(0);
        missileTr.setToY(-battleground.getBoundsInParent().getHeight());
        missileTr.play();
    }

    public void alienShot(Pane battleground, GridPane invaders, Canon canon, ImageView alien){
        Missile missile = new Missile();
        ImageView missileImage = new ImageView(new Image(missile.getShape()));
        TranslateTransition missileTr = new TranslateTransition(Duration.millis(6000), missileImage);
        missileImage.setY(alien.getBoundsInParent().getMaxY() + invaders.getBoundsInParent().getMinY());
        missileImage.setX(alien.getBoundsInParent().getMinX() + invaders.getBoundsInParent().getMinX() + alien.getImage().getWidth()/ 2);
        missileImage.translateYProperty().addListener((observable, oldValue, newValue) -> {
            if (missileImage.getBoundsInParent().intersects(canon.getImage().getBoundsInParent())) {
                missileTr.stop();
                battleground.getChildren().remove(missileImage);
                canon.setHealth(canon.getHealth()-1);
            }
            if(missileImage.getBoundsInParent().getMaxY() >= battleground.getHeight()){
                missileTr.stop();
                battleground.getChildren().remove(missileImage);
            }
        });
        missileTr.setOnFinished(event1 -> battleground.getChildren().remove(missileImage));
        battleground.getChildren().add(missileImage);
        missileTr.setByX(0);
        missileTr.setToY(battleground.getBoundsInParent().getHeight());
        missileTr.play();
    }
}
