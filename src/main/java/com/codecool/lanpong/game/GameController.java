package com.codecool.lanpong.game;

import com.codecool.lanpong.models.GameStatus;

public class GameController {

    private GameStatus gameStatus;

    public GameController() {
    }

    public void handleGame() {

        while(true) {
            updateGameStatus();
            System.out.println(gameStatus.getBallX());
        }
    }

    private void updateGameStatus() {
        gameStatus.setBallDirection(DataRetriever.getBallDirection());
        gameStatus.setBallX(DataRetriever.getBallXPos());
        gameStatus.setBallY(DataRetriever.getBallYPos());
        gameStatus.setServerRacketPos(DataRetriever.getRacket1XPos());
        gameStatus.setClientRacketPos(DataRetriever.getRacket2XPos());
    }
}
