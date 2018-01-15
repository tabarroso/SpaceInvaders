package sample.game;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AlienAnimation{

    private Pane battleground;
    private GridPane invaders;

    public AlienAnimation(Pane battleground, GridPane invaders){
        this.battleground = battleground;
        this.invaders = invaders;
    }
    public void initializeTranslation(){
        TranslateTransition tty = new TranslateTransition(Duration.millis(1500), invaders);
        TranslateTransition ttx = new TranslateTransition(Duration.millis(3500), invaders);
        ttx.setOnFinished(event->{
            if(invaders.getBoundsInParent().getMaxY() <= (battleground.getBoundsInParent().getHeight()/100)*80){
                goDown(tty);
            }
            else{
                doNothing(tty);
            }
        });
        tty.setOnFinished(event -> {
            ttx.setByY(0);
            if(invaders.getBoundsInParent().getMinX() == 0){
                goRight(ttx);
            }
            else{
                goLeft(ttx);
            }
            ttx.play();
        });
        goRight(ttx);
        ttx.play();
    }

    private void goLeft(TranslateTransition ttx) {
        ttx.setToX(battleground.getBoundsInParent().getMinX());
    }

    private void goRight(TranslateTransition ttx) {
        ttx.setToX(battleground.getBoundsInParent().getWidth()-invaders.getBoundsInParent().getWidth());
    }

    private void doNothing(TranslateTransition tty) {
        tty.setByX(0);
        tty.setByY(0);
        tty.setAutoReverse(true);
        tty.play();
    }

    private void goDown(TranslateTransition tty) {
        tty.setByX(0);
        tty.setByY(battleground.getBoundsInParent().getHeight()/40);
        tty.setAutoReverse(true);
        tty.play();
    }
}