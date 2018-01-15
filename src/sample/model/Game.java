package sample.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.entities.characters.Canon;
import sample.model.entities.characters.aliens.Alien;
import sample.model.entities.characters.aliens.MedInvaders;
import sample.model.entities.characters.aliens.TypeAlien;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    private Canon canon;
    private MedInvaders mediator;
    private ObservableList<Score> scoresObs = FXCollections.observableArrayList();
    private ListProperty<Score> scores = new SimpleListProperty<>(scoresObs);

    public Game() {
        this.canon = new Canon();
        this.mediator = new MedInvaders();
    }

    public Canon getCanon() {
        return canon;
    }

    public MedInvaders getMediator() {
        return mediator;
    }

    public ArrayList<Alien> getAlienList(){
        return mediator.getListAlien();
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
}
