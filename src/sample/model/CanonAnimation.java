package sample.model;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.model.entities.characters.Canon;
import sample.model.entities.characters.MissileShooter;
import sample.model.entities.characters.aliens.MedInvaders;

public class CanonAnimation {
    private Canon canon;
    private MissileShooter missileShooter;
    private MedInvaders mediator;
    private Pane battleground;
    private GridPane invaders;

    public CanonAnimation(Pane battleground, GridPane invaders, MedInvaders mediator, MissileShooter missileShooter) {
        this.canon = new Canon(100);
        this.battleground = battleground;
        this.invaders = invaders;
        this.mediator = mediator;
        this.missileShooter = missileShooter;
    }

    private void upShotCmd(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            if (canon.isCanShot()) {
                canon.setCanShot(false);
                missileShooter.canonShot(battleground, invaders, canon, mediator.getListAlien());
            }
        }
    }

    public void setReleased(TranslateTransition transition) {
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

    public void setPressed(TranslateTransition transition) {
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
    public Canon getCanon(){
        return this.canon;
    }
}
