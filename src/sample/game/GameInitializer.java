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

/**
 * Initializes the game components
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 */
public class GameInitializer {
    /**
     * Used to communicate with the game sub-system
     */
    private Game game;
    /**
     * Manage the animations of the Canon
     */
    private CanonAnimation canonAnimation;
    /**
     * Manage the animations of the Missiles
     */
    private MissileShooter missileShooter;
    /**
     * Manage the animations of the Aliens
     */
    private AlienAnimation alienAnimation;
    /**
     * The Battleground Container
     */
    private Pane battleground;
    /**
     * The Invaders Container
     */
    private GridPane invaders;
    /**
     * Level of the current session
     */
    private int level;
    /**
     * Countdown property
     */
    private IntegerProperty time = new SimpleIntegerProperty(COUNTDOWN_START);
    /**
     * Number of columns of the Invaders Container
     */
    private static final int NB_COL = 11;
    /**
     * Number of ROws of the Invaders COntainer
     */
    private static final int NB_LINE = 5;
    /**
     * Countdown time
     */
    private static final int COUNTDOWN_START = 3;

    /**
<<<<<<< HEAD
     * Constructor
     * @param battleground The Battleground Container
     * @param invaders The Invaders Container
=======
     *
     * @param battleground
     * @param invaders
>>>>>>> b27fe22779114cc365695179069458b4a0030f5e
     */
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

    /**
     * Initializes the game components
     */
    public void initializeGame(){
        initializeLevel();
        game.getCanon().getImage().setY(battleground.getBoundsInParent().getHeight() - game.getCanon().getImage().getImage().getHeight());
        game.getCanon().getImage().setX(0);
        battleground.getChildren().add(game.getCanon().getImage());
        setListeners();
        startCountdown();
    }

    /**
     * Initializes the current session level components
     */
    public void initializeLevel(){
        alienAnimation.goToStart();
        game.getMediator().createInvaders();
        game.getMediator().initializePositions(invaders,NB_COL,NB_LINE);
        this.level +=1;
    }

    /**
     * Initializes the Listeners
     */
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

    /**
     * closes the games when the number of HP is equal to 0
     */
    private void gameOver(){
        FXMLLauncherController.getGameStage().close();
    }

    /**
     * Initializes the Key Events
     */
    private void setKeyEvents(){
        canonAnimation.setPressed();
        canonAnimation.setReleased();
    }

    /**
     * Launches the game and the level
     */
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

    /**
     * Initializes the scores, best score and Health points Views
     * @param scoreLabel
     * @param bestScoreLabel
     * @param healthLabel
     */
    public void initializeBindings(Label scoreLabel, Label bestScoreLabel, Label healthLabel){
        bestScoreLabel.textProperty().bind(game.bestScoreProperty().asString());
        scoreLabel.textProperty().bind(game.getCurrentScore().scorePropProperty().asString());
        healthLabel.textProperty().bind(game.healthProperty().asString());
    }
}

