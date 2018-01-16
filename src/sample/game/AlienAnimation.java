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
    private static int POSITION_CORRECT = 75;

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
                goDown();
            }
            else{
                doNothing();
            }
        });
        tty.setOnFinished(event -> {
            ttx.setByY(0);
            if(invaders.getBoundsInParent().getMinX() == 0){
                goRight();
            }
            else{
                goLeft();
            }
            ttx.play();
        });
        goRight();
        ttx.play();
    }
    public void resume(){
        goRight();
        ttx.play();
    }

    private void goLeft() {
        ttx.setToX(battleground.getBoundsInParent().getMinX());
    }

    private void goRight() {
        ttx.setToX(battleground.getBoundsInParent().getWidth()-invaders.getBoundsInParent().getWidth());
    }

    private void doNothing() {
        tty.setByX(0);
        tty.setByY(0);
        tty.setAutoReverse(true);
        tty.play();
    }

    private void goDown() {
        tty.setByX(0);
        tty.setByY(battleground.getBoundsInParent().getHeight()/40);
        tty.setAutoReverse(true);
        tty.play();
    }
    private void goUp(){
        ttd.setToX(battleground.getBoundsInParent().getMinX());
        ttd.setToY(battleground.getBoundsInParent().getMinY() - POSITION_CORRECT);
    }
    public void goToStart(){
        ttx.stop();
        tty.stop();
        goUp();
        ttd.play();
    }
}