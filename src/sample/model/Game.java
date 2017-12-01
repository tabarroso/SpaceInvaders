package sample.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Game {
    private StringProperty pseudo = new SimpleStringProperty("default");
    private ObservableList<Score> scoresObs = FXCollections.observableArrayList();
    private ListProperty<Score> scores = new SimpleListProperty<>(scoresObs);

    public ObservableList<Score> getScores() {
        return scores.get();
    }

    public ReadOnlyListProperty<Score> scoresProperty() {
        return scores;
    }

    public String getPseudo() {
        return pseudo.get();
    }

    public StringProperty pseudoProperty() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo.set(pseudo);
    }
}
