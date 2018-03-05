package com.codecool.lanpong.game;

import com.codecool.lanpong.models.Board;

public class GameController {

    private Board board;

    public GameController() {
        this.board = new Board(800, 600);
        board.setGameParameters();
    }


}
