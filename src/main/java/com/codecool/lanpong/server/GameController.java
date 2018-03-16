package com.codecool.lanpong.server;

import com.codecool.lanpong.common.GameParameters;
import com.codecool.lanpong.common.GameStatus;

public class GameController {

    private GameStatus gameStatus;
    private long timeFactor;
    private double ballXvelocity;
    private double ballYvelocity;

    public GameController(GameStatus gameStatus) {

        this.gameStatus = gameStatus;
        timeFactor = 0;
        ballXvelocity = GameParameters.BALL_STARTING_SPEED * Math.cos(GameParameters.BALL_STARTING_ANGLE);
        ballYvelocity = GameParameters.BALL_STARTING_SPEED * Math.sin(GameParameters.BALL_STARTING_ANGLE);
    }

    public void analyzeGameStatus() {

        checkEndGame();
        updateBallPosition();
        System.out.println(gameStatus.getBallXpos() + "  " + gameStatus.getBallYpos());
    }

    private void checkEndGame() {

        // TODO
    }
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

    private void updateBallPosition() {

        if (checkBorderCollisions()) {
            ballYvelocity = -ballYvelocity;
            // shiftBall(distance);
        }
        if (checkRacketCollisions()) {
            ballXvelocity = -ballXvelocity;
            // shiftBall(distance);
        }

        gameStatus.setBallXpos(gameStatus.getBallXpos() + ballXvelocity);
        gameStatus.setBallYpos(gameStatus.getBallYpos() + ballYvelocity);
    }

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
    private boolean checkRacketCollisions() {

        return gameStatus.getBallXpos() < GameParameters.RACKET_WIDTH ||
                gameStatus.getBallXpos() > GameParameters.BOARD_WIDTH - GameParameters.RACKET_WIDTH;
    }

    private boolean checkBorderCollisions() {

        return gameStatus.getBallYpos() < 0 || gameStatus.getBallYpos() > GameParameters.BOARD_HEIGHT;
    }
}
