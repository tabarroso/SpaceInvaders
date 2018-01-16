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
/**
 * Controls all the missiles animations and collisions
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class MissileShooter {
    /**
     * The main Pane
     */
    private Pane battleground;
    /**
     * The Pane where the Aliens are placed
     */
    private GridPane invaders;
    /**
     * The Player
     */
    private Canon canon;
    /**
     * Observable List Alien
     */
    private ObservableList<Alien> aliens;
    /**
     * Instance of Game
     */
    private Game game;
    /**
     * The player's missile Animation
     */
    private TranslateTransition canonMissileTr;
    /**
     * The player's missile Image
     */
    private ImageView canonMissileImage;
    /**
     * Minimum position in the Pane
     */
    private final static double MIN = 0.0;
    /**
     * Numbers of points earned when an Alien is killed
     */
    private final static int POINT_PER_KILL = 25;
    
    /**
     * <b>Constructor</b>
     * @param battleground
     * @param invaders
     * @param game 
     */
    public MissileShooter(Pane battleground, GridPane invaders, Game game){
        this.game = game;
        this.battleground = battleground;
        this.invaders = invaders;
        this.canon = game.getCanon();
        this.aliens = game.getAlienList();
    }
    /**
     * Initializes the players missiles animations and collisions
     */
    public void canonShot(){
        Missile missile = new Missile();
        canonMissileImage = new ImageView(new Image(missile.getShape()));
        canonMissileTr = new TranslateTransition(Duration.millis(400), canonMissileImage);
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
    /**
     * Initializes the invaders missiles animations and collisions
     * @param alien
     * @param level 
     */
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
    /**
     * Method called when the player is hit by an Alien missile
     * @param missileImage
     * @param missileTr 
     */
    private void hitByAlien(ImageView missileImage, TranslateTransition missileTr) {
        missileTr.stop();
        battleground.getChildren().remove(missileImage);
        canon.setHealth(canon.getHealth()-1);
    }
    /**
     * Detects if a player's missile hits an Alien
     * @param missileImage
     * @return boolean is true when the players missiles hits an alien, else return false
     */
    private boolean isIntersectsAlien(ImageView missileImage) {
        return missileImage.getBoundsInParent().intersects(canon.getImage().getBoundsInParent());
    }
    /**
     * Stops a missile animation and remove it from the view
     * @param missileImage The missile Image
     * @param missileTr The missile Animation
     */
    private void stopMissile(ImageView missileImage, TranslateTransition missileTr) {
        missileTr.stop();
        battleground.getChildren().remove(missileImage);
    }
    /**
     * Detects if a missile is out of bounds
     * @param missileImage The missile Image
     * @return boolean is true when the missile is out of bounds, else return false
     */
    private boolean isOutOfBounds(ImageView missileImage) {
        return missileImage.getBoundsInParent().getMinY() <= MIN || missileImage.getBoundsInParent().getMaxY() >= battleground.getHeight();
    }
    /**
     * Method called when isIntersectsAlien returns true
     * @param missileImage
     * @param missileTr
     * @param it
     * @param alienImage 
     */
    private void hitByCanon(ImageView missileImage, TranslateTransition missileTr, Iterator<Alien> it, ImageView alienImage) {
        invaders.getChildren().remove(alienImage);
        missileTr.stop();
        it.remove();
        battleground.getChildren().remove(missileImage);
        canon.setCanShot(true);
        game.upCurrentScore(POINT_PER_KILL);
    }
    /**
     * Detects if an invaders missile hits the player
     * @param missileImage
     * @param alienImage
     * @return true when the invaders missiles hits the player, else return false
     */
    private boolean isIntersectsCanon(ImageView missileImage, ImageView alienImage) {
        return missileImage.getBoundsInParent().intersects(alienImage.getBoundsInParent().getMinX() + invaders.getBoundsInParent().getMinX(),
                alienImage.getBoundsInParent().getMinY() + invaders.getBoundsInParent().getMinY(),
                alienImage.getImage().getWidth(), alienImage.getImage().getHeight());
    }
    /**
     * Stops the player's missile animation and remove it from the view: Called when a level is finished
     */
    public void stopCanonMissile(){
            canonMissileTr.stop();
            battleground.getChildren().remove(canonMissileImage);
            canon.setCanShot(true);
    }
}
