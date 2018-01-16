package sample.game;

import javafx.animation.TranslateTransition;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.entities.characters.Canon;

/**
 * This class manage the canon's animations
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class CanonAnimation {
    /**
     * The player's canon
     */
    private Canon canon;
    /**
     *The class that manage the missile shots
     */
    private MissileShooter missileShooter;
    /**
     * The battleground container
     */
    private Pane battleground;
    /**
     * The canon transition
     */
    private TranslateTransition transition;

    /**
     * Constructor
     * @param battleground
     * @param missileShooter
     * @param canon
     */
    public CanonAnimation(Pane battleground, MissileShooter missileShooter, Canon canon) {
        this.canon = canon;
        this.battleground = battleground;
        this.missileShooter = missileShooter;
        transition = new TranslateTransition(Duration.millis(6000), canon.getImage());
    }

    /**
     * Initialize the shot command (up key)
     * @param event
     */
    private void upShotCmd(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            if (canon.isCanShot()) {
                canon.setCanShot(false);
                missileShooter.canonShot();
            }
        }
    }

    /**
     * Stop the transition when the move key is released
     */
    public void setReleased() {
        battleground.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
                stopAnimation();
            }
            event.consume();
        });
    }

    /**
     * Set the movement transition according the key we press
     */
    public void setPressed() {
        battleground.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                transition.setToX(battleground.getBoundsInParent().getWidth() - canon.getImage().getImage().getWidth());
                transition.setDuration(Duration.millis(6000 - 6000 * (canon.getImage().getBoundsInParent().getMaxX() / battleground.getBoundsInParent().getWidth())));
                transition.play();
            }
            if (event.getCode() == KeyCode.LEFT) {
                transition.setToX(battleground.getBoundsInParent().getMinX());
                transition.setDuration(Duration.millis(6000 * (canon.getImage().getBoundsInParent().getMaxX() / battleground.getBoundsInParent().getWidth())));
                transition.play();
            }
            upShotCmd(event);
            event.consume();
        });
    }

    /**
     * Stop the animation
     */
    public void stopAnimation(){
        transition.stop();
    }
}
