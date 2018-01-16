package sample.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

/**
 * Manages the Scores Lists
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 */
public class Scores {
    /**
     * Observable of the List of Scores
     */
    private ObservableList<Score> scoresObs = FXCollections.observableArrayList();
    /**
     * The List of Scores
     */
    private ListProperty<Score> scores = new SimpleListProperty<>(scoresObs);
    /**
     * The score of the current session
     */
    private Score currentScore;

    /**
     * Constructor
     */
    public Scores() {
    }

    /**
     *
     * @return the score of the current session
     */
    public Score getCurrentScore() {
        return currentScore;
    }

    /**
     * Raises the score of the current session
     * @param score
     */
    public void upCurrentScore(int score){
        currentScore.setScoreProp(currentScore.getScoreProp()+score);
    }

    /**
     *
     * @return list of scores
     */
    public ObservableList<Score> getScores() {
        return scores.get();
    }

    /**
     *
     * @return the best score of the list
     */
    public IntegerProperty bestScoreProperty(){
        Score bestScore = scoresObs.get(0);
        for(Iterator<Score> it = scoresObs.iterator(); it.hasNext();){
            Score score = it.next();
            if(score.getScoreProp() > bestScore.getScoreProp()){
                bestScore = score;
            }
        }
        return bestScore.scorePropProperty();
    }

    /**
     *
     * @return  the scores property
     */
    public ReadOnlyListProperty<Score> scoresProperty() {
        return scores;
    }

    /**
     * ReInits the the score of the current session to 0
     * @param pseudo the nickname of the player
     */
    public void reinitCurrentScore(String pseudo){
        currentScore = new Score(0, pseudo);
    }
}
