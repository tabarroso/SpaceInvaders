/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.model.GameInitializer;

/**
 *
 * @author ilbenjello
 */
public class FXMLBattlegroundController{
    private static final int NB_COL = 11;
    private static final int NB_LINE = 5;
    private static final int MIN_HEIGHT = 0;
    private static final int MIN_WIDTH = 0;
    private Boolean isLaunched = false;
    private GameInitializer gameInitializer;
    @FXML
    Pane battleground;
    @FXML
    GridPane invaders;
    @FXML
    Label scoreLabel;
    @FXML
    Label bestScoreLabel;

    @FXML
    private void initialize(){
        battleground.setStyle("-fx-background-color: #000000;");
        battleground.setOnMouseClicked(event -> {
        if(!isLaunched){
            isLaunched = true;
            initializeGrid();
            gameInitializer = new GameInitializer(battleground, invaders);
            gameInitializer.initializeGame();
            gameInitializer.initializeBindings(scoreLabel, bestScoreLabel);
        }
    });
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