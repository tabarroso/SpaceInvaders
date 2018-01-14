package sample.model;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.model.entities.characters.MissileShooter;
import sample.model.entities.characters.aliens.MedInvaders;

import java.util.Random;

public class GameInitializer {
    private Game game;
    private MedInvaders mediator;
    private CanonAnimation canonAnimation;
    private MissileShooter missileShooter;
    private AlienAnimation alienAnimation;
    private Pane battleground;
    private GridPane invaders;
    private IntegerProperty time = new SimpleIntegerProperty(COUNTDOWN_START);
    private static final int NB_COL = 11;
    private static final int NB_LINE = 5;
    private static final int COUNTDOWN_START = 5;

    public GameInitializer(Pane battleground, GridPane invaders){
        mediator = new MedInvaders();
        game = new Game();
        missileShooter = new MissileShooter();
        canonAnimation = new CanonAnimation(battleground, invaders, mediator, missileShooter);
        alienAnimation = new AlienAnimation(battleground, invaders);
        this.battleground = battleground;
        this.invaders = invaders;
    }

    public void initializeGame(){
        mediator.createInvaders(game.getLevel());
        mediator.initializePositions(invaders,NB_COL,NB_LINE);
        canonAnimation.getCanon().getImage().setY(battleground.getBoundsInParent().getHeight() - canonAnimation.getCanon().getImage().getImage().getHeight());
        canonAnimation.getCanon().getImage().setX(0);
        battleground.getChildren().add(canonAnimation.getCanon().getImage());
        startCountdown();
    }

    private void setKeyEvents(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(6000), canonAnimation.getCanon().getImage());
        canonAnimation.setPressed(transition);
        canonAnimation.setReleased(transition);
    }

    private void startCountdown(){
        Label countdown = new Label();
        countdown.setStyle("-fx-font-size: 4em;");
        countdown.setTextFill(Color.RED);
        battleground.getChildren().add(countdown);
        countdown.textProperty().bind(time.asString());
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(COUNTDOWN_START-1), new KeyValue(time,0)));
        timeline.playFromStart();
        timeline.setOnFinished(event -> {
            battleground.getChildren().remove(countdown);
            setKeyEvents();
            alienAnimation.initializeTranslation();
            missileShooter = new MissileShooter();
            mediator.initializeShot(battleground,invaders, canonAnimation.getCanon(),mediator.getImages(),missileShooter);
        });
    }
}

