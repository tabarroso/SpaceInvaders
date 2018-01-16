package sample.game;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entities.characters.Canon;
import sample.entities.characters.aliens.Alien;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    private Canon canon;
    private MedInvaders mediator;
    private StringProperty pseudo = new SimpleStringProperty();
    private Scores scores;

    public Game() {
        this.canon = new Canon();
        this.mediator = new MedInvaders();
        this.scores = new Scores(getPseudo());
    }

    public ObservableList<Score> getScores() {
        return scores.getScores();
    }

    public Score getCurrentScore(){
        return scores.getCurrentScore();
    }

    public void upCurrentScore(int score){
        scores.upCurrentScore(score);
    }

    public IntegerProperty bestScoreProperty(){
        return scores.bestScoreProperty();
    }

    public ReadOnlyListProperty<Score> scoresProperty(){
        return scores.scoresProperty();
    }

    public String getPseudo() {
        return pseudo.get();
    }

    public IntegerProperty healthProperty(){
        return canon.healthProperty();
    }

    public StringProperty pseudoProperty() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo.set(pseudo);
    }

    public Canon getCanon() {
        return canon;
    }

    public MedInvaders getMediator() {
        return mediator;
    }

    public ObservableList<Alien> getAlienList(){
        return mediator.getListAlienProperty();
    }

}
