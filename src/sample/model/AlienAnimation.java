package sample.model;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AlienAnimation{
    @FXML
    Pane battleground;
    @FXML
    GridPane invaders;

    public AlienAnimation(Pane battleground, GridPane invaders){
        this.battleground = battleground;
        this.invaders = invaders;
    }
    public void initializeTranslation(GridPane aliens, Pane battleground){
        TranslateTransition tty = new TranslateTransition(Duration.millis(1500), aliens);
        TranslateTransition ttx = new TranslateTransition(Duration.millis(3500), aliens);
        ttx.setOnFinished(event->{
            if(aliens.getBoundsInParent().getMaxY() <= (battleground.getBoundsInParent().getHeight()/100)*80){
                tty.setByX(0);
                tty.setByY(battleground.getBoundsInParent().getHeight()/40);
                tty.setAutoReverse(true);
                tty.play();
            }
            else{
                tty.setByX(0);
                tty.setByY(0);
                tty.setAutoReverse(true);
                tty.play();
            }
        });
        tty.setOnFinished(event -> {
            ttx.setByY(0);
            if(aliens.getBoundsInParent().getMinX() == 0){
                ttx.setToX(battleground.getBoundsInParent().getWidth()-aliens.getBoundsInParent().getWidth());
            }
            else{
                ttx.setToX(battleground.getBoundsInParent().getMinX());
            }
            ttx.play();
        });
        ttx.setToX(battleground.getBoundsInParent().getWidth()-aliens.getBoundsInParent().getWidth());
        ttx.play();
    }
}