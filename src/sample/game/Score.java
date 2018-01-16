package sample.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Defines a score
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 */
public class Score{
    /**
     * NickName of the player
     */
    private String pseudo;
    /**
     * Score property
     */
    private IntegerProperty scoreProp = new SimpleIntegerProperty();

    /**
     *
     * @return the nickname of the player
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Sets the nickname of the player
     * @param pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     *
     * @return the score property of the current session
     */
    public int getScoreProp() {
        return scoreProp.get();
    }

    /**
     *
     * @return the score property
     */
    public IntegerProperty scorePropProperty() {
        return scoreProp;
    }

    /**
     * Sets the score property
     * @param scoreProp
     */
    public void setScoreProp(int scoreProp) {
        this.scoreProp.set(scoreProp);
    }

    /**
     * Constructor
     * @param score The score of the player
     * @param pseudo The Nickname of the player
     */
    public Score(int score, String pseudo){
        this.setScoreProp(score);
        this.setPseudo(pseudo);
    }
}
