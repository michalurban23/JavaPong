package com.codecool.lanpong.game;

import com.codecool.lanpong.models.Board;
import com.codecool.lanpong.view.WindowDisplay;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameController implements Runnable {

    private Board board;

    public GameController() {
        this.board = new Board(800, 600);
        board.setGameParameters();
    }

    @Override
    public void run() {
        WindowDisplay windowDisplay = new WindowDisplay(board);
//        Application.launch(windowDisplay);
        try {
            windowDisplay.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
