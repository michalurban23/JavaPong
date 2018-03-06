package com.codecool.lanpong.game;

import com.codecool.lanpong.models.Board;
import com.codecool.lanpong.view.WindowDisplay;
import javafx.application.Application;

public class GameController implements Runnable {

    private Board board;

    public GameController() {
        this.board = new Board(800, 600);
        board.setGameParameters();
    }

    @Override
    public void run() {
        Application.launch(WindowDisplay.class);
    }
}
