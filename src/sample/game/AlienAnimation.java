package sample.game;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * This class manage the aliens animations
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class AlienAnimation{
    /**
     * The battleground container
     */
    private Pane battleground;
    /**
     * The aliens container
     */
    private GridPane invaders;
    /**
     * The Y axis transition
     */
    private TranslateTransition transitionY;
    /**
     * The X axis transition
     */
    private TranslateTransition transitionX;
    /**
     * The transition to the start position
     */
    private TranslateTransition transitionStart;
    /**
     * Constant that correct the start position
     */
    private static int POSITION_CORRECT = 75;

    /**
     * Constructor
     * @param battleground
     * @param invaders
     */
    public AlienAnimation(Pane battleground, GridPane invaders){
        this.battleground = battleground;
        this.invaders = invaders;
        transitionY = new TranslateTransition(Duration.millis(1500), invaders);
        transitionX = new TranslateTransition(Duration.millis(3500), invaders);
        transitionStart = new TranslateTransition(Duration.millis(1), invaders);
    }

    /**
     * Initialize the aliens transitions
     */
    public void initializeTranslation(){
        transitionX.setOnFinished(event->{
            if(invaders.getBoundsInParent().getMaxY() <= (battleground.getBoundsInParent().getHeight()/100)*80){
                goDown();
            }
            else{
                doNothing();
            }
        });
        transitionY.setOnFinished(event -> {
            transitionX.setByY(0);
            if(invaders.getBoundsInParent().getMinX() == 0){
                goRight();
            }
            else{
                goLeft();
            }
        });
        resume();
    }

    /**
     * Resume the game
     */
    public void resume(){
        goRight();
    }

    /**
     * Play a transition to the left
     */
    private void goLeft() {
        transitionX.setToX(battleground.getBoundsInParent().getMinX());
        transitionX.play();
    }

    /**
     * Play a transition to the right
     */
    private void goRight() {
        transitionX.setToX(battleground.getBoundsInParent().getWidth()-invaders.getBoundsInParent().getWidth());
        transitionX.play();
    }

    /**
     * Play a void transition
     */
    private void doNothing() {
        transitionY.setByX(0);
        transitionY.setByY(0);
        transitionY.setAutoReverse(true);
        transitionY.play();
    }

    /**
     * Play a transition to the bottom
     */
    private void goDown() {
        transitionY.setByX(0);
        transitionY.setByY(battleground.getBoundsInParent().getHeight()/40);
        transitionY.setAutoReverse(true);
        transitionY.play();
    }

    /**
     * Play a transition to the start position
     */
    private void goUp(){
        transitionStart.setToX(battleground.getBoundsInParent().getMinX());
        transitionStart.setToY(battleground.getBoundsInParent().getMinY() - POSITION_CORRECT);
        transitionStart.play();
    }

    /**
     * Stop the other transition and go up
     */
    public void goToStart(){
        transitionX.stop();
        transitionY.stop();
        goUp();
    }
}