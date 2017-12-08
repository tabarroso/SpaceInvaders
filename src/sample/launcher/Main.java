package sample.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/sample/vue/FXMLLauncher.fxml"));
        primaryStage.setTitle("Space Invaders");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }
}