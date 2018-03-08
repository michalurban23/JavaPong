package com.codecool.lanpong.game;

import com.codecool.lanpong.lanlayer.DataReadWriteController;
import com.codecool.lanpong.lanlayer.PlayerController;
import com.codecool.lanpong.lanlayer.Server;
import com.codecool.lanpong.models.GameStatus;

import java.io.IOException;

public class GameController {

    private static GameStatus gameStatus;
    private static PlayerController playerController;
    private DataReadWriteController dataController;
    private boolean gameRunning;
    // private boolean ballJustBounced;

    private static final long GAME_SPEED = 20;
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 400;
    private static final int BALL_RADIUS = 15;

    public GameController(PlayerController pc) {

        playerController = pc;
    }

    public void handleGame() throws IOException {

        createGame();

        while(gameRunning) {
            updateGameStatus();
        }
    }

    private void createGame() throws IOException {

        if (gameStatus == null) {
            createStartingState();
        }

        dataController = new DataReadWriteController(playerController.getSocket());
        dataController.setup();
        gameRunning = true;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void createStartingState() {

        gameStatus = new GameStatus();
        gameStatus.setBallAngle(80);
        gameStatus.setBallX(400);
        gameStatus.setBallY(300);
        gameStatus.setServerRacketPos(300);
        gameStatus.setClientRacketPos(300);
    }

    private void updateGameStatus() throws IOException {

        try {
            Thread.sleep(GAME_SPEED/2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateBallPosition();
        sendStatus();
        readStatus();
    }

    private void sendStatus() throws IOException {

        if (playerController instanceof Server) {
            gameStatus.setServerRacketPos(DataRetriever.getRacket1YPos());
        } else {
            gameStatus.setClientRacketPos(DataRetriever.getRacket2YPos());
        }
        dataController.sendData(gameStatus);
    }

    private void updateBallPosition() {

        // Check collisions:
        boolean hitBorder = checkBorderCollisions();
        boolean hitRacket = checkRacketCollisions();

        // Determine ball direction:
        if (hitBorder) {
            gameStatus.setBallAngle(360 - gameStatus.getBallAngle());
            shiftBall();
        }
        if (hitRacket) {
            gameStatus.setBallAngle(90 + gameStatus.getBallAngle());
            shiftBall();
        }

        int distance = (int) GAME_SPEED;
        int xDistance = (int) (distance * Math.cos(Math.toRadians(gameStatus.getBallAngle())));
        int yDistance = (int) (distance * Math.sin(Math.toRadians(gameStatus.getBallAngle())));

        // Move ball horizontally:
        gameStatus.setBallX(DataRetriever.getBallXPos() + xDistance);

        // Move ball vertically:
        gameStatus.setBallY(DataRetriever.getBallYPos() + yDistance);
    }

    private void shiftBall() {

        int xPos = gameStatus.getBallX();
        int yPos = gameStatus.getBallY();

        if (Math.abs(xPos) < 0) {
            gameStatus.setBallX(xPos + 2*BALL_RADIUS);
        } else if (Math.abs(BOARD_WIDTH - xPos) < BALL_RADIUS) {
            gameStatus.setBallX(xPos - 2*BALL_RADIUS);
        }

        if (Math.abs(yPos) < 0) {
            gameStatus.setBallY(yPos + 2*BALL_RADIUS);
        } else if (Math.abs(BOARD_HEIGHT - yPos) < BALL_RADIUS) {
            gameStatus.setBallY(yPos - 2*BALL_RADIUS);
        }
    }

    private boolean checkRacketCollisions() {
        boolean collides = false;
        int[] clientRacketRange = getClientRacketRange();
        int[] serverRacketRange = getServerRacketRange();

        if (clientRacketRange[0] <= gameStatus.getBallY() &&
                clientRacketRange[1] >= gameStatus.getBallY() &&
                gameStatus.getBallX() >= BOARD_WIDTH - 15 - BALL_RADIUS)
            collides = true;

        if (serverRacketRange[0] <= gameStatus.getBallY() &&
                serverRacketRange[1] >= gameStatus.getBallY() &&
                gameStatus.getBallX() <= 15 + BALL_RADIUS)
            collides = true;

        return collides;
    }

    private boolean checkBorderCollisions() {

        return gameStatus.getBallY() < 0 || gameStatus.getBallY() > BOARD_HEIGHT - BALL_RADIUS;
    }

    private void readStatus() throws IOException {

        gameStatus = dataController.readData();
    }

    public static GameStatus getGameStatus() {

        if (gameStatus == null) {
            createStartingState();
        }
        return gameStatus;
    }

    public static PlayerController getGameOwner() {
        return playerController;
    }

    public static double getGameSpeed() {
        return GAME_SPEED;
    }

    public static int getBoardWidth() {

        return BOARD_WIDTH;
    }

    public static int getBoardHeight() {

        return BOARD_HEIGHT;
    }

    public static int getBallRadius() {

        return BALL_RADIUS;
    }

    private int[] getClientRacketRange() {
        if (gameStatus.getClientRacketPos() == 0)
            return new int[]{0, 100};
        else if (gameStatus.getClientRacketPos() == BOARD_HEIGHT)
            return new int[]{0, 100};
        else
            return new int[]{gameStatus.getClientRacketPos()-50, gameStatus.getClientRacketPos()+50};
    }

    private int[] getServerRacketRange() {
        if (gameStatus.getServerRacketPos() == 0)
            return new int[]{0, 100};
        else if (gameStatus.getServerRacketPos() == BOARD_HEIGHT)
            return new int[]{300, 400};
        else
            return new int[]{gameStatus.getServerRacketPos()-50, gameStatus.getServerRacketPos()+50};
    }
}
