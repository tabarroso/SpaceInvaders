package sample.game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.controller.FXMLLauncherController;

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
    private static final int COUNTDOWN_START = 3;

    public GameInitializer(Pane battleground, GridPane invaders){
        this.battleground = battleground;
        this.invaders = invaders;
        game = FXMLLauncherController.getGame();
        game.reInit();
        level = 0;
        missileShooter = new MissileShooter(battleground,invaders,game);
        canonAnimation = new CanonAnimation(battleground, missileShooter, game.getCanon());
        alienAnimation = new AlienAnimation(battleground, invaders);
    }

    public void initializeGame(){
        initializeLevel();
        game.getCanon().getImage().setY(battleground.getBoundsInParent().getHeight() - game.getCanon().getImage().getImage().getHeight());
        game.getCanon().getImage().setX(0);
        battleground.getChildren().add(game.getCanon().getImage());
        setListeners();
        startCountdown();
    }
    public void initializeLevel(){
        alienAnimation.goToStart();
        game.getMediator().createInvaders();
        game.getMediator().initializePositions(invaders,NB_COL,NB_LINE);
        this.level +=1;
    }

    private void setListeners(){
        game.healthProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue() == 0){
                gameOver();
            }
        });
        game.getMediator().listAlienProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isEmpty()){
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(COUNTDOWN_START-1), new KeyValue(time,0)));
                timeline.playFromStart();
                timeline.setOnFinished(event -> {
                    missileShooter.stopCanonMissile();
                    initializeLevel();
                    startCountdown();
                });
            }
        });
    }

    private void gameOver(){
        FXMLLauncherController.getGameStage().close();
    }

    private void setKeyEvents(){
        canonAnimation.setPressed();
        canonAnimation.setReleased();
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
            if(level == 1){
                alienAnimation.initializeTranslation();
            }
            else {
                canonAnimation.stopAnimation();
                alienAnimation.resume();
            }
            game.getMediator().queryShot(game.getMediator().getImages(),missileShooter, level);
        });
    }

    public void initializeBindings(Label scoreLabel, Label bestScoreLabel, Label healthLabel){
        bestScoreLabel.textProperty().bind(game.bestScoreProperty().asString());
        scoreLabel.textProperty().bind(game.getCurrentScore().scorePropProperty().asString());
        healthLabel.textProperty().bind(game.healthProperty().asString());
    }
}

