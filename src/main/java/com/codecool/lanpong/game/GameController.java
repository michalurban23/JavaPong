package com.codecool.lanpong.game;

import com.codecool.lanpong.lanlayer.DataReadWriteController;
import com.codecool.lanpong.lanlayer.PlayerController;
import com.codecool.lanpong.lanlayer.Server;
import com.codecool.lanpong.models.GameStatus;

import java.io.IOException;
import java.util.Random;

public class GameController {

    private static GameStatus gameStatus;
    private static PlayerController playerController;
    private DataReadWriteController dataController;
    private boolean gameRunning;
    private boolean matchRunning;
    private static int[] score;
    private Random random;
    private long timer = 0l;

    private static final long GAME_SPEED = 10;
    private static final int BOARD_WIDTH = 900;
    private static final int BOARD_HEIGHT = 600;
    private static final double BALL_RADIUS = 15d;

    public GameController(PlayerController pc) {

        playerController = pc;
        random = new Random();
    }

    public void handleGame() throws IOException {

        createGame();

        while(gameRunning) {
            runMatch();
        }
    }

    private void runMatch() throws IOException {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (matchRunning) {
            updateGameStatus();
            checkMatchEnd();
        }
        resetGameState();
        matchRunning = true;
    }

    private void resetGameState() {

        gameStatus.setBallAngle((new Random()).nextBoolean() ? 0d : 180d);  // Either goes left or right
        gameStatus.setBallX(BOARD_WIDTH / 2);
        gameStatus.setBallY(BOARD_HEIGHT / 2);
        gameStatus.setServerRacketPos(BOARD_HEIGHT / 2);
        gameStatus.setClientRacketPos(BOARD_HEIGHT / 2);

        timer = 0l;
    }

    private void createGame() throws IOException {

        if (gameStatus == null) {
            createStartingState();
        }

        dataController = new DataReadWriteController(playerController.getSocket());
        dataController.setup();
        gameRunning = true;
        score = new int[] {0,0};

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        matchRunning = true;
    }

    private static void createStartingState() {

        gameStatus = new GameStatus();
        gameStatus.setBallAngle((new Random()).nextBoolean() ? 0d : 180d);  // Either goes left or right
        gameStatus.setBallX(BOARD_WIDTH / 2);
        gameStatus.setBallY(BOARD_HEIGHT / 2);
        gameStatus.setServerRacketPos(BOARD_HEIGHT / 2);
        gameStatus.setClientRacketPos(BOARD_HEIGHT / 2);
    }

    private void updateGameStatus() throws IOException {

        // try {
        //     Thread.sleep(GAME_SPEED * 10);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        updateBallPosition();
        sendStatus();
        readStatus();
    }

    private void checkMatchEnd() {

        System.out.println(gameStatus);
        if (gameStatus.getBallX() < -BALL_RADIUS || gameStatus.getBallX() > BOARD_WIDTH + BALL_RADIUS) {
            updateScore();
            matchRunning = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateScore() {

        if (gameStatus.getBallX() < BOARD_WIDTH / 2) {
            score[1]++;
        } else {
            score[0]++;
        }
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

        double distance = Math.max(10d, timer++ / 10);
        System.out.println(distance);

        // Check collisions:
        boolean hitBorder = checkBorderCollisions();
        boolean hitRacket = checkRacketCollisions();

        // Determine ball direction:
        if (hitBorder) {
            gameStatus.setBallAngle(360d - gameStatus.getBallAngle());
            shiftBall(distance);
        }
        if (hitRacket) {
            if (gameStatus.getBallX() > BOARD_WIDTH / 2) {
                gameStatus.setBallAngle(random.nextInt(180) + 90d);
            } else {
                gameStatus.setBallAngle((random.nextInt(180) + 270d) % 360d);
            }
            shiftBall(distance);
        }

        double xDistance = distance * Math.cos(Math.toRadians(gameStatus.getBallAngle()));
        double yDistance = distance * Math.sin(Math.toRadians(gameStatus.getBallAngle()));

        // Move ball horizontally:
        gameStatus.setBallX(DataRetriever.getBallXPos() + xDistance);

        // Move ball vertically:
        gameStatus.setBallY(DataRetriever.getBallYPos() + yDistance);
    }

    private void shiftBall(double distance) {

        double shift = Math.max(BALL_RADIUS, distance);
        double xPos = gameStatus.getBallX();
        double yPos = gameStatus.getBallY();

        if (Math.abs(xPos) < 0) {
            gameStatus.setBallX(xPos + 2*shift);
        } else if (Math.abs(BOARD_WIDTH - xPos) < shift) {
            gameStatus.setBallX(xPos - 2*shift);
        }

        if (Math.abs(yPos) < 0) {
            gameStatus.setBallY(yPos + 2*shift);
        } else if (Math.abs(BOARD_HEIGHT - yPos) < shift) {
            gameStatus.setBallY(yPos - 2*shift);
        }
    }

    private boolean checkRacketCollisions() {
        boolean collides = false;
        double[] clientRacketRange = getClientRacketRange();
        double[] serverRacketRange = getServerRacketRange();

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

    public static int[] getScore() {
        return score;
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

    public static double getBallRadius() {

        return BALL_RADIUS;
    }

    private double[] getClientRacketRange() {
        // if (gameStatus.getClientRacketPos() == 0)
        //     return new double[]{0, 100};
        // else if (gameStatus.getClientRacketPos() == BOARD_HEIGHT)
        //     return new double[]{0, 100};
        // else
            return new double[]{gameStatus.getClientRacketPos()-50, gameStatus.getClientRacketPos()+50};
    }

    private double[] getServerRacketRange() {
        // if (gameStatus.getServerRacketPos() == 0)
        //     return new double[]{0, 100};
        // else if (gameStatus.getServerRacketPos() == BOARD_HEIGHT)
        //     return new double[]{300, 400};
        // else
            return new double[]{gameStatus.getServerRacketPos()-50, gameStatus.getServerRacketPos()+50};
    }
}
