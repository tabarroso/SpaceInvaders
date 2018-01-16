package sample.game;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.entities.Missile;
import sample.entities.characters.Canon;
import sample.entities.characters.aliens.Alien;

import java.util.ArrayList;
import java.util.Iterator;

public class MissileShooter {
    private Pane battleground;
    private GridPane invaders;
    private Canon canon;
    private ObservableList<Alien> aliens;
    private Game game;
    private final static double MIN = 0.0;
    private final static int POINT_PER_KILL = 25;
    private TranslateTransition canonMissileTr;
    private ImageView canonMissileImage;

    public MissileShooter(Pane battleground, GridPane invaders, Game game){
        this.game = game;
        this.battleground = battleground;
        this.invaders = invaders;
        this.canon = game.getCanon();
        this.aliens = game.getAlienList();
    }

    public void canonShot(){
        Missile missile = new Missile();
        canonMissileImage = new ImageView(new Image(missile.getShape()));
        canonMissileTr = new TranslateTransition(Duration.millis(200), canonMissileImage);
        canonMissileImage.setY(canon.getImage().getBoundsInParent().getMinY());
        canonMissileImage.setX(canon.getImage().getBoundsInParent().getMinX() + canon.getImage().getBoundsInParent().getWidth() / 2);
        canonMissileImage.translateYProperty().addListener((observable, oldValue, newValue) -> {
            for (Iterator<Alien> it = aliens.iterator(); it.hasNext(); ) {
                Alien alien = it.next();
                ImageView alienImage = alien.getImage();
                if (isIntersectsCanon(canonMissileImage, alienImage)) {
                    hitByCanon(canonMissileImage, canonMissileTr, it, alienImage);
                }
                if(isOutOfBounds(canonMissileImage)){
                    stopMissile(canonMissileImage, canonMissileTr);
                    canon.setCanShot(true);
                }
            }
        });
        battleground.getChildren().add(canonMissileImage);
        canonMissileTr.setByX(0);
        canonMissileTr.setToY(-battleground.getBoundsInParent().getHeight());
        canonMissileTr.play();
    }

    public void alienShot(ImageView alien, int level){
        Missile missile = new Missile();
        ImageView missileImage = new ImageView(new Image(missile.getShape()));
        TranslateTransition missileTr = new TranslateTransition(Duration.millis(6000 -level*100), missileImage);
        missileImage.setY(alien.getBoundsInParent().getMaxY() + invaders.getBoundsInParent().getMinY());
        missileImage.setX(alien.getBoundsInParent().getMinX() + invaders.getBoundsInParent().getMinX() + alien.getImage().getWidth()/ 2);
        missileImage.translateYProperty().addListener((observable, oldValue, newValue) -> {
            if (isIntersectsAlien(missileImage)) {
                hitByAlien(missileImage, missileTr);
            }
            if(isOutOfBounds(missileImage)){
                stopMissile(missileImage, missileTr);
            }
        });
        missileTr.setOnFinished(event1 -> battleground.getChildren().remove(missileImage));
        battleground.getChildren().add(missileImage);
        missileTr.setByX(0);
        missileTr.setToY(battleground.getBoundsInParent().getHeight());
        missileTr.play();
    }

    private void hitByAlien(ImageView missileImage, TranslateTransition missileTr) {
        missileTr.stop();
        battleground.getChildren().remove(missileImage);
        canon.setHealth(canon.getHealth()-1);
    }

    private boolean isIntersectsAlien(ImageView missileImage) {
        return missileImage.getBoundsInParent().intersects(canon.getImage().getBoundsInParent());
    }
    private void stopMissile(ImageView missileImage, TranslateTransition missileTr) {
        missileTr.stop();
        battleground.getChildren().remove(missileImage);
    }

    private boolean isOutOfBounds(ImageView missileImage) {
        return missileImage.getBoundsInParent().getMinY() <= MIN || missileImage.getBoundsInParent().getMaxY() >= battleground.getHeight();
    }

    private void hitByCanon(ImageView missileImage, TranslateTransition missileTr, Iterator<Alien> it, ImageView alienImage) {
        invaders.getChildren().remove(alienImage);
        missileTr.stop();
        it.remove();
        battleground.getChildren().remove(missileImage);
        canon.setCanShot(true);
        game.upCurrentScore(POINT_PER_KILL);
    }

    private boolean isIntersectsCanon(ImageView missileImage, ImageView alienImage) {
        return missileImage.getBoundsInParent().intersects(alienImage.getBoundsInParent().getMinX() + invaders.getBoundsInParent().getMinX(),
                alienImage.getBoundsInParent().getMinY() + invaders.getBoundsInParent().getMinY(),
                alienImage.getImage().getWidth(), alienImage.getImage().getHeight());
    }

    public void stopCanonMissile(){
            canonMissileTr.stop();
            battleground.getChildren().remove(canonMissileImage);
            canon.setCanShot(true);
    }
}
