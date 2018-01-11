package sample.model;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.model.entities.characters.MissileShooter;
import sample.model.entities.characters.aliens.MedInvaders;

public class GameInitializer {
    private Game game;
    private MedInvaders mediator;
    private CanonAnimation canonAnimation;
    private MissileShooter missileShooter;
    private AlienAnimation alienAnimation;

    @FXML
    Pane battleground;
    @FXML
    GridPane invaders;

    private static final int NB_COL = 11;
    private static final int NB_LINE = 5;

    public GameInitializer(Pane battleground, GridPane invaders){
        mediator = new MedInvaders();
        game = new Game();
        canonAnimation = new CanonAnimation(battleground, invaders, mediator);
        alienAnimation = new AlienAnimation(battleground, invaders);
        missileShooter = new MissileShooter();
        this.battleground = battleground;
        this.invaders = invaders;
    }

    public void initializeGame(){
        mediator.createInvaders(game.getLevel());
        mediator.initializePositions(invaders,NB_COL,NB_LINE);
        canonAnimation.getCanon().getImage().setY(battleground.getBoundsInParent().getHeight() - canonAnimation.getCanon().getImage().getImage().getHeight());
        canonAnimation.getCanon().getImage().setX(0);
        battleground.getChildren().add(canonAnimation.getCanon().getImage());
        alienAnimation.initializeTranslation(invaders, battleground);
        missileShooter = new MissileShooter();
        mediator.initializeShot(battleground,invaders, canonAnimation.getCanon(),mediator.getImages(),missileShooter);
    }

    public void setKeyEvents(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(6000), canonAnimation.getCanon().getImage());
        canonAnimation.setPressed(transition);
        canonAnimation.setReleased(transition);
    }
}

