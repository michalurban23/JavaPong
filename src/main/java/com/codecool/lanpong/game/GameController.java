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
    private Random random;
    private long timer = 0l;

    private static final long GAME_SPEED = 10;
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 400;
    private static final double BALL_RADIUS = 15d;

    public GameController(PlayerController pc) {

        playerController = pc;
        random = new Random();
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

        return gameStatus.getBallX() < 0 || gameStatus.getBallX() > BOARD_WIDTH - BALL_RADIUS;
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

    public static double getBallRadius() {

        return BALL_RADIUS;
    }
}