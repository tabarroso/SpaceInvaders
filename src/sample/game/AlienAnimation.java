package sample.game;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AlienAnimation{

    private Pane battleground;
    private GridPane invaders;
    private TranslateTransition tty;
    private TranslateTransition ttx;
    private TranslateTransition ttd;

    public AlienAnimation(Pane battleground, GridPane invaders){
        this.battleground = battleground;
        this.invaders = invaders;
        tty = new TranslateTransition(Duration.millis(1500), invaders);
        ttx = new TranslateTransition(Duration.millis(3500), invaders);
        ttd = new TranslateTransition(Duration.millis(1), invaders);
    }
    public void initializeTranslation(){
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
    private void goUp(TranslateTransition ttdep){
        ttdep.setToX(battleground.getBoundsInParent().getMinX());
        ttdep.setToY(battleground.getBoundsInParent().getMinY() - 75);
    }
    public void goToStart(){
        ttx.stop();
        tty.stop();
        goUp(ttd);
        ttd.play();
    }
}