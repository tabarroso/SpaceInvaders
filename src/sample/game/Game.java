package sample.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import sample.entities.characters.Canon;
import sample.entities.characters.aliens.Alien;

/**
 * This class is a facade for the entities sub system
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class Game {
    /**
     * The player's canon
     */
    private Canon canon;
    /**
     * The class that coordinate all the aliens
     */
    private MedInvaders mediator;
    /**
     * The player's pseudo (we use it for the score)
     */
    private StringProperty pseudo = new SimpleStringProperty();
    /**
     * The class that contain all the scores
     */
    private Scores scores;

    /**
     * Constructor
     * Instantiate the class attributes
     */
    public Game() {
        this.canon = new Canon();
        this.mediator = new MedInvaders();
        this.scores = new Scores();
    }

    /**
     *
     * @return the scores list from scores
     */
    public ObservableList<Score> getScores() {
        return scores.getScores();
    }

    /**
     *
     * @return the current score from scores
     */
    public Score getCurrentScore(){
        return scores.getCurrentScore();
    }

    /**
     * Up the current score by the value of @score
     * @param score the score we gain
     */
    public void upCurrentScore(int score){
        scores.upCurrentScore(score);
    }

    /**
     *
     * @return the best score property
     */
    public IntegerProperty bestScoreProperty(){
        return scores.bestScoreProperty();
    }

    /**
     *
     * @return the list property of scores
     */
    public ReadOnlyListProperty<Score> scoresProperty(){
        return scores.scoresProperty();
    }

    /**
     *
     * @return the String pseudo
     */
    public String getPseudo() {
        return pseudo.get();
    }

    /**
     *
     * @return the health property from canon
     */
    public IntegerProperty healthProperty(){
        return canon.healthProperty();
    }

    /**
     *
     * @return the pseudo property
     */
    public StringProperty pseudoProperty() {
        return pseudo;
    }

    /**
     * Set the value of the StringProperty pseudo
     * @param pseudo value of pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo.set(pseudo);
    }

    /**
     *
     * @return the player's canon
     */
    public Canon getCanon() {
        return canon;
    }

    /**
     *
     * @return the mediator
     */
    public MedInvaders getMediator() {
        return mediator;
    }

    /**
     *
     * @return the alien list from the mediator
     */
    public ObservableList<Alien> getAlienList(){
        return mediator.getListAlienProperty();
    }

    /**
     * Reinitialize the game environment
     */
    public void reInit(){
        mediator = new MedInvaders();
        canon = new Canon();
        scores.reinitCurrentScore(getPseudo());
    }
}
