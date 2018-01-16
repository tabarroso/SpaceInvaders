package sample.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.launcher.Main;
import sample.game.Game;
import sample.game.Score;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLLauncherController {

    private static Game game = new Game();
    private static Stage newWindow;

    @FXML
    ListView<Score> bestScoresList;

    @FXML
    TextField pseudoField;

    @FXML
    private void onClickStart(){
        try {
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/sample/vue/FXMLBattleground.fxml")));
            newWindow = new Stage();
            newWindow.setTitle(pseudoField.getText());
            newWindow.setScene(newScene);
            newWindow.initOwner(Main.getPrimaryStage());
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLauncherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onClickQuit(){
        Main.getPrimaryStage().close();
    }

    public static Game getGame(){ return game; }

    public static Stage getGameStage(){
        return newWindow;
    }

    @FXML
    private void initialize(){
        game.pseudoProperty().bind(pseudoField.textProperty());
        game.getScores().add(new Score(1500, "tanguy"));
        game.getScores().add(new Score(2000, "ilyace"));
        game.getScores().add(new Score(3000, "the king"));
        bestScoresList.setItems(game.scoresProperty());
        bestScoresList.setCellFactory((param) -> new ListCell<Score>(){
            @Override
            protected void updateItem(Score score, boolean empty) {
                super.updateItem(score, empty);
                if (! empty) {
                    textProperty().bind(Bindings.concat(score.getPseudo()," : ",score.scorePropProperty()));
                } else {
                    textProperty().unbind();
                    setText("");
                }
            }
        });
    }
}