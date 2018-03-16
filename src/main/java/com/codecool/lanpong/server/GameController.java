package com.codecool.lanpong.server;

import com.codecool.lanpong.common.GameStatus;

import java.util.Random;

public class GameController {

    private GameStatus gameStatus;

    public GameController(GameStatus gameStatus) {

        this.gameStatus = gameStatus;
    }

    public void analyzeGameStatus() {

        gameStatus.setScore(new int[]{(new Random()).nextInt(10),(new Random()).nextInt(10)});
    }
    //    private void runMatch() throws IOException {
//
//        // try {
//        //     Thread.sleep(1000);
//        // } catch (InterruptedException e) {
//        //     e.printStackTrace();
//        // }
//
//        while (matchRunning) {
//            updateGameStatus();
//            checkMatchEnd();
//        }
//        resetGameState();
//        matchRunning = true;
//    }
//
//    private void resetGameState() {
//
//        gameStatus.setBallAngle((new Random()).nextBoolean() ? 0d : 180d);  // Either goes left or right
//        gameStatus.setBallX(BOARD_WIDTH / 2);
//        gameStatus.setBallY(BOARD_HEIGHT / 2);
//        gameStatus.setPlayer1RacketPos(BOARD_HEIGHT / 2);
//        gameStatus.setPlayer2RacketPos(BOARD_HEIGHT / 2);
//
//        timer = 0l;
//
//        // try {
//        //     Thread.sleep(1000);
//        // } catch (InterruptedException e) {
//        //     e.printStackTrace();
//        // }
//    }
//
//    private void createGame() throws IOException {
//
//        if (gameStatus == null) {
//            createStartingState();
//        }
//
//        gameRunning = true;
//        score = new int[] {0,0};
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        matchRunning = true;
//    }
//
//    public static void createStartingState() {
//
//        gameStatus = new GameStatus();
//        gameStatus.setBallAngle((new Random()).nextBoolean() ? 0d : 180d);  // Either goes left or right
//        gameStatus.setBallX(BOARD_WIDTH / 2);
//        gameStatus.setBallY(BOARD_HEIGHT / 2);
//        gameStatus.setPlayer1RacketPos(BOARD_HEIGHT / 2);
//        gameStatus.setPlayer2RacketPos(BOARD_HEIGHT / 2);
//    }
//
//    private void updateGameStatus() throws IOException {
//
//        // try {
//        //     Thread.sleep(GAME_SPEED * 10);
//        // } catch (InterruptedException e) {
//        //     e.printStackTrace();
//        // }
//        readStatus();
//        updateBallPosition();
//        sendStatus();
//    }
//
//    private void checkMatchEnd() {
//
//        if (gameStatus.getBallX() < -BALL_RADIUS || gameStatus.getBallX() > BOARD_WIDTH + BALL_RADIUS) {
//            updateScore();
//            matchRunning = false;
//        }
//    }
//
//    private void updateScore() {
//
//        if (gameStatus.getBallX() < BOARD_WIDTH / 2) {
//            score[1]++;
//        } else {
//            score[0]++;
//        }
//    }
//
//    private void sendStatus() throws IOException {
//
//        player1dataController.setGameStatus(gameStatus);
//        player2dataController.setGameStatus(gameStatus);
//    }
//
//    private void updateBallPosition() {
//
//        double distance = Math.max(10d, timer++ / 10);
//        System.out.println(distance);
//
//        // Check collisions:
//        boolean hitBorder = checkBorderCollisions();
//        boolean hitRacket = checkRacketCollisions();
//
//        // Determine ball direction:
//        if (hitBorder) {
//            gameStatus.setBallAngle(360d - gameStatus.getBallAngle());
//            shiftBall(distance);
//        }
//        if (hitRacket) {
//            if (gameStatus.getBallX() > BOARD_WIDTH / 2) {
//                gameStatus.setBallAngle(random.nextInt(180) + 90d);
//            } else {
//                gameStatus.setBallAngle((random.nextInt(180) + 270d) % 360d);
//            }
//            shiftBall(distance);
//        }
//
//        double xDistance = distance * Math.cos(Math.toRadians(gameStatus.getBallAngle()));
//        double yDistance = distance * Math.sin(Math.toRadians(gameStatus.getBallAngle()));
//
//        // Move ball horizontally:
//        gameStatus.setBallX(gameStatus.getBallX() + xDistance);
//
//        // Move ball vertically:
//        gameStatus.setBallY(gameStatus.getBallY() + yDistance);
//    }
//
//    private void shiftBall(double distance) {
//
//        double shift = Math.max(BALL_RADIUS, distance);
//        double xPos = gameStatus.getBallX();
//        double yPos = gameStatus.getBallY();
//
//        if (Math.abs(xPos) < 0) {
//            gameStatus.setBallX(xPos + 2*shift);
//        } else if (Math.abs(BOARD_WIDTH - xPos) < shift) {
//            gameStatus.setBallX(xPos - 2*shift);
//        }
//
//        if (Math.abs(yPos) < 0) {
//            gameStatus.setBallY(yPos + 2*shift);
//        } else if (Math.abs(BOARD_HEIGHT - yPos) < shift) {
//            gameStatus.setBallY(yPos - 2*shift);
//        }
//    }
//
//    private boolean checkRacketCollisions() {
//        boolean collides = false;
//        double[] serverRacketRange = getPlayer1RacketRange();
//        double[] clientRacketRange = getPlayer2RacketRange();
//
//        if (clientRacketRange[0] <= gameStatus.getBallY() &&
//                clientRacketRange[1] >= gameStatus.getBallY() &&
//                gameStatus.getBallX() >= BOARD_WIDTH - 15 - BALL_RADIUS)
//            collides = true;
//
//        if (serverRacketRange[0] <= gameStatus.getBallY() &&
//                serverRacketRange[1] >= gameStatus.getBallY() &&
//                gameStatus.getBallX() <= 15 + BALL_RADIUS)
//            collides = true;
//
//        if (serverRacketRange[0] <= gameStatus.getBallY() &&
//                serverRacketRange[1] >= gameStatus.getBallY() &&
//                gameStatus.getBallX() >= BOARD_WIDTH - 15 - BALL_RADIUS)
//            collides = true;
//
//        if (clientRacketRange[0] <= gameStatus.getBallY() &&
//                clientRacketRange[1] >= gameStatus.getBallY() &&
//                gameStatus.getBallX() <= 15 + BALL_RADIUS)
//            collides = true;
//
//        return collides;
//    }
//
//    private boolean checkBorderCollisions() {
//
//        return gameStatus.getBallY() < 0 || gameStatus.getBallY() > BOARD_HEIGHT - BALL_RADIUS;
//    }
//
//    private void readStatus() throws IOException {
//
//        try {
//            gameStatus.setPlayer1RacketPos(player1dataController.readData().getPlayer1RacketPos());
//            gameStatus.setPlayer2RacketPos(player2dataController.readData().getPlayer2RacketPos());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static GameStatus getGameStatus() {
//
//        if (gameStatus == null) {
//            createStartingState();
//        }
//        return gameStatus;
//    }
//
//    public static int[] getScore() {
//        return score;
//    }
//
//    public static double getGameSpeed() {
//        return GAME_SPEED;
//    }
//
//    public static int getBoardWidth() {
//
//        return BOARD_WIDTH;
//    }
//
//    public static int getBoardHeight() {
//
//        return BOARD_HEIGHT;
//    }
//
//    public static double getBallRadius() {
//
//        return BALL_RADIUS;
//    }
//
//    private double[] getPlayer1RacketRange() {
//        if (gameStatus.getPlayer1RacketPos() == 0)
//            return new double[]{0, 100};
//        else if (gameStatus.getPlayer1RacketPos() == BOARD_HEIGHT)
//            return new double[]{BOARD_HEIGHT-100, BOARD_HEIGHT};
//        else
//            return new double[]{gameStatus.getPlayer1RacketPos()-50, gameStatus.getPlayer1RacketPos()+50};
//    }
//
//    private double[] getPlayer2RacketRange() {
//        if (gameStatus.getPlayer2RacketPos() == 0)
//            return new double[]{0, 100};
//        else if (gameStatus.getPlayer2RacketPos() == BOARD_HEIGHT)
//            return new double[]{BOARD_HEIGHT-100, BOARD_HEIGHT};
//        else
//            return new double[]{gameStatus.getPlayer2RacketPos()-50, gameStatus.getPlayer2RacketPos()+50};
//    }
}
