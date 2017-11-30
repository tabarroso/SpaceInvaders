package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Game {
    private StringProperty pseudo = new SimpleStringProperty("default");
    private ObservableList<Score> scores = new FXCollections.

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
