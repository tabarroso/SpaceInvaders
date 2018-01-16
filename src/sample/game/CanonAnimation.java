package sample.game;

import javafx.animation.TranslateTransition;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.entities.characters.Canon;

public class CanonAnimation {
    private Canon canon;
    private MissileShooter missileShooter;
    private Pane battleground;
    private TranslateTransition transition;

    public CanonAnimation(Pane battleground, MissileShooter missileShooter, Canon canon) {
        this.canon = canon;
        this.battleground = battleground;
        this.missileShooter = missileShooter;
        transition = new TranslateTransition(Duration.millis(6000), canon.getImage());
    }

    private void upShotCmd(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            if (canon.isCanShot()) {
                canon.setCanShot(false);
                missileShooter.canonShot();
            }
        }
    }

    public void setReleased() {
        battleground.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                transition.stop();
            }
            if (event.getCode() == KeyCode.LEFT) {
                transition.stop();
            }
            event.consume();
        });
    }

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
    public void stopAnimation(){
        transition.stop();
    }
}
