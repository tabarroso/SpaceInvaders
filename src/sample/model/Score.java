package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Score{
    private StringProperty pseudoProp = new SimpleStringProperty();
    private IntegerProperty scoreProp = new SimpleIntegerProperty();

    public String getPseudoProp() {
        return pseudoProp.get();
    }

    public StringProperty pseudoPropProperty() {
        return pseudoProp;
    }

    public void setPseudoProp(String pseudoProp) {
        this.pseudoProp.set(pseudoProp);
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
        this.setPseudoProp(pseudo);
    }
}
