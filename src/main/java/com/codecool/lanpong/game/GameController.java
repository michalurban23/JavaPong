package com.codecool.lanpong.game;

import com.codecool.lanpong.models.Board;
import com.codecool.lanpong.view.WindowDisplay;
import com.sun.javafx.application.ParametersImpl;
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

        WindowDisplay.launch();
        WindowDisplay.Parameters parameters = new ParametersImpl();
        System.out.println(parameters.getNamed());
    }
}
