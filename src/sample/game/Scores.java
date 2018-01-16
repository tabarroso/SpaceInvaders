package sample.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

public class Scores {
    private ObservableList<Score> scoresObs = FXCollections.observableArrayList();
    private ListProperty<Score> scores = new SimpleListProperty<>(scoresObs);
    private Score currentScore;

    public Scores() {
    }

    public Score getCurrentScore() {
        return currentScore;
    }

    public void upCurrentScore(int score){
        currentScore.setScoreProp(currentScore.getScoreProp()+score);
    }

    public ObservableList<Score> getScores() {
        return scores.get();
    }

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
    public ReadOnlyListProperty<Score> scoresProperty() {
        return scores;
    }
    public void reinitCurrentScore(String pseudo){
        currentScore = new Score(0, pseudo);
    }
}
