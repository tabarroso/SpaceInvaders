/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.game.GameInitializer;

/**
 *  Control the FXMLBattleground view
 * @author Ilyace Benjelloun
 * @author Tanguy Barroso
 * @version 1.0
 */
public class FXMLBattlegroundController{
    /**
     * The number of columns
     */
    private static final int NB_COL = 11;
    /**
     * The number of lines
     */
    private static final int NB_LINE = 5;
    /**
     * The minimum height
     */
    private static final int MIN_HEIGHT = 0;
    /**
     * The minimum width
     */
    private static final int MIN_WIDTH = 0;
    /**
     * A boolean to know if the game is launched
     */
    private Boolean isLaunched = false;
    /**
     * Used to initialize the game
     */
    private GameInitializer gameInitializer;
    /**
     * Used to display the help at the start of the game
     */
    private Label helpLabel;
    /**
     * The battleground container
     */
    @FXML
    Pane battleground;
    /**
     * The alien container
     */
    @FXML
    GridPane invaders;
    /**
     * The label in which we display the current score
     */
    @FXML
    Label scoreLabel;
    /**
     * The label in which we display the best score
     */
    @FXML
    Label bestScoreLabel;
    /**
     * Used to display the healths
     */
    @FXML
    Label healthLabel;
    /**
     * Not used yet
     */
    @FXML
    ImageView healthImageView;

    /**
     * Initialize the window
     */
    @FXML
    private void initialize(){
        battleground.setStyle("-fx-background-color: #000000;");
        enableHelp();
        battleground.setOnMouseClicked(event -> {
        if(!isLaunched){
            battleground.getChildren().remove(helpLabel);
            isLaunched = true;
            initializeGrid();
            gameInitializer = new GameInitializer(battleground, invaders);
            gameInitializer.initializeGame();
            gameInitializer.initializeBindings(scoreLabel, bestScoreLabel, healthLabel);
        }
    });
    }

    /**
     * Display the help
     */
    private void enableHelp(){
        helpLabel = new Label();
        helpLabel.setText("Press right/left key to move, up key to shoot.");
        helpLabel.setTextFill(Color.RED);
        helpLabel.setFont(new Font(20));
        battleground.getChildren().add(helpLabel);
    }
    
    /**
     * Create the columns and rows of the GridPane invaders
     */
    private void initializeGrid(){
        for(int i=2;i<=NB_COL;i++)
            invaders.addColumn(i);
        for(int i=2;i<=NB_LINE;i++)
            invaders.addRow(i);
        invaders.setMinSize(MIN_WIDTH,MIN_HEIGHT);
    }

}