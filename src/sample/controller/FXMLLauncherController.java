package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Score;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLLauncherController {

    @FXML
    ListView<Score> listeScore;

    @FXML
    Button start;

    @FXML
    Button quit;

    @FXML
    TextField pseudoField;

    @FXML
    private void onClickStart(){
        try {
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/sample/vue/FXMLBattleground.fxml")));
            Stage newWindow = new Stage();
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

    public void initialize(){

    }
}
