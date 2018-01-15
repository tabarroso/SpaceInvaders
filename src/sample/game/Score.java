package sample.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Score{
    private String pseudo;
    private IntegerProperty scoreProp = new SimpleIntegerProperty();

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getScoreProp() {
        return scoreProp.get();
    }

    public IntegerProperty scorePropProperty() {
        return scoreProp;
    }

    public void setScoreProp(int scoreProp) {
        this.scoreProp.set(scoreProp);
    }

    public Score(int score, String pseudo){
        this.setScoreProp(score);
        this.setPseudo(pseudo);
    }
}
