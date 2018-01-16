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

/**
 * Control the FXMLLauncher view
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class FXMLLauncherController {
    /**
     * Use to communicate the scores and the pseudo
     */
    private static Game game = new Game();
    /**
     * The battleground window
     */
    private static Stage newWindow;
    /**
     * Used to display the score list
     */
    @FXML
    ListView<Score> bestScoresList;
    /**
     * Used to enter the pseudo
     */
    @FXML
    TextField pseudoField;

    /**
     * Load the battleground window when we click on the start button
     */
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

    /**
     * Close the window
     */
    @FXML
    private void onClickQuit(){
        Main.getPrimaryStage().close();
    }

    /**
     *
     * @return the game
     */
    public static Game getGame(){ return game; }

    /**
     *
     * @return the battleground stage
     */
    public static Stage getGameStage(){
        return newWindow;
    }

    /**
     * Initialize the window
     */
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