package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.controller.FXMLLauncherController;
import sample.model.entities.characters.MissileShooter;
import sample.model.entities.characters.aliens.MedInvaders;

import javax.swing.event.ListDataEvent;

public class GameInitializer {
    private Game game;
    private CanonAnimation canonAnimation;
    private MissileShooter missileShooter;
    private AlienAnimation alienAnimation;
    private Pane battleground;
    private GridPane invaders;
    private int level;
    private IntegerProperty time = new SimpleIntegerProperty(COUNTDOWN_START);
    private static final int NB_COL = 11;
    private static final int NB_LINE = 5;
    private static final int COUNTDOWN_START = 5;

    public GameInitializer(Pane battleground, GridPane invaders){
        this.battleground = battleground;
        this.invaders = invaders;
        game = FXMLLauncherController.getGame();
        level = 0;
        missileShooter = new MissileShooter(battleground,invaders,game.getCanon(),game.getAlienList());
        canonAnimation = new CanonAnimation(battleground, missileShooter, game.getCanon());
        alienAnimation = new AlienAnimation(battleground, invaders);
    }

    public void initializeGame(){
        initializeLevel();
        game.getCanon().getImage().setY(battleground.getBoundsInParent().getHeight() - game.getCanon().getImage().getImage().getHeight());
        game.getCanon().getImage().setX(0);
        battleground.getChildren().add(game.getCanon().getImage());
        startCountdown();
    }
    public void initializeLevel(){
        game.getMediator().createInvaders();
        game.getMediator().initializePositions(invaders,NB_COL,NB_LINE);
        this.level +=1;
    }
    private void setKeyEvents(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(6000), game.getCanon().getImage());
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
            game.getMediator().initializeShot(game.getMediator().getImages(),missileShooter, level);
        });
    }

    public void initializeBindings(Label scoreLabel, Label bestScoreLabel){
        bestScoreLabel.textProperty().bind(game.bestScoreProperty().asString());
    }

    private void nextLevel(){
        game.getMediator().getListAlien().isEmpty(event -> {

        });
    }
}

