package com.codecool.lanpong.game;

import com.codecool.lanpong.lanlayer.DataReadWriteController;
import com.codecool.lanpong.lanlayer.PlayerController;
import com.codecool.lanpong.models.GameStatus;

import java.io.IOException;

public class GameController {

    private static GameStatus gameStatus;
    private PlayerController playerController;
    private DataReadWriteController dataController;
    private boolean gameRunning;

    public GameController(PlayerController playerController, GameStatus gs) {

        this.playerController = playerController;
        gameStatus = gs;
    }

    public void handleGame() throws IOException {

        createGame();

        while(gameRunning) {
            updateGameStatus();
        }
    }

    private void createGame() throws IOException {

        dataController = new DataReadWriteController(playerController.getSocket());
        dataController.setup();
        gameRunning = true;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void updateGameStatus() throws IOException {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendStatus();
        readStatus();
    }

    private void sendStatus() throws IOException {

        gameStatus.setBallDirection(DataRetriever.getBallDirection());
        gameStatus.setBallX(DataRetriever.getBallXPos());
        gameStatus.setBallY(DataRetriever.getBallYPos());
        gameStatus.setServerRacketPos(DataRetriever.getRacket1XPos());
        gameStatus.setClientRacketPos(DataRetriever.getRacket2XPos());

        System.out.println(gameStatus);
        dataController.sendData(gameStatus);
    }

    private void readStatus() throws IOException {

        gameStatus = dataController.readData();
    }

    public static GameStatus getGameStatus() {

        return gameStatus;
    }
}
