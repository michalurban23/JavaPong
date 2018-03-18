package com.codecool.lanpong.server;

import com.codecool.lanpong.common.GameParameters;
import com.codecool.lanpong.common.GameStatus;

import java.util.Arrays;

public class GameController {

    private GameStatus gameStatus;
    private double ballXvelocity;
    private double ballYvelocity;

    public GameController(GameStatus gameStatus) {

        this.gameStatus = gameStatus;
        setBallStartingVelocity();
    }

    private void setBallStartingVelocity() {

        ballXvelocity = GameParameters.BALL_STARTING_SPEED * Math.cos(GameParameters.BALL_STARTING_ANGLE);
        ballYvelocity = GameParameters.BALL_STARTING_SPEED * Math.sin(GameParameters.BALL_STARTING_ANGLE);
    }

    public void analyzeGameStatus() {

        checkEndGame();
        updateBallPosition();
    }

    private void checkEndGame() {

        if (gameStatus.getBallXpos() < 0 ||
                gameStatus.getBallXpos() > GameParameters.BOARD_WIDTH + GameParameters.BALL_RADIUS) {
            updateScore();
            resetGame();
        }
    }

    private void resetGame() {

        System.out.println(Arrays.toString(gameStatus.getScore()));
        setBallStartingVelocity();
        gameStatus.setBallXpos(GameParameters.BOARD_WIDTH / 2);
        gameStatus.setBallYpos(GameParameters.BOARD_HEIGHT / 2);
    }

    private void updateScore() {

        if (gameStatus.getBallXpos() < GameParameters.BOARD_WIDTH / 2) {
            gameStatus.getScore()[1]++;
        } else {
            gameStatus.getScore()[0]++;
        }
    }


    private void updateBallPosition() {

        if (checkBorderCollisions()) {
            ballYvelocity = -ballYvelocity;
            ballYvelocity = Math.min(ballYvelocity * GameParameters.TIME_FACTOR, GameParameters.BALL_RADIUS / 2);
            // shiftBall(distance);
        }
        if (checkRacketCollisions()) {
            System.out.println("collided");
            ballXvelocity = -ballXvelocity;
            ballXvelocity = Math.min(ballXvelocity * GameParameters.TIME_FACTOR, GameParameters.BALL_RADIUS / 2);;
            // shiftBall(distance);
        }
        gameStatus.setBallXpos(gameStatus.getBallXpos() + ballXvelocity);
        gameStatus.setBallYpos(gameStatus.getBallYpos() + ballYvelocity);
    }

    private boolean checkRacketCollisions() {

        boolean hitLeftRacket = gameStatus.getBallXpos() < GameParameters.RACKET_WIDTH &&
                gameStatus.getBallXpos() > 0 &&
                gameStatus.getBallYpos() > gameStatus.getPlayer1racket() &&
                gameStatus.getBallYpos() < gameStatus.getPlayer1racket() + GameParameters.RACKET_HEIGHT;
        boolean hitRightRacket = gameStatus.getBallXpos() < GameParameters.BOARD_WIDTH &&
                gameStatus.getBallXpos() > GameParameters.BOARD_WIDTH - GameParameters.RACKET_WIDTH - GameParameters.BALL_RADIUS &&
                gameStatus.getBallYpos() > gameStatus.getPlayer2racket() &&
                gameStatus.getBallYpos() < gameStatus.getPlayer2racket() + GameParameters.RACKET_HEIGHT;
        return hitLeftRacket || hitRightRacket;
    }

    private boolean checkBorderCollisions() {

        return gameStatus.getBallYpos() < 0 ||
                gameStatus.getBallYpos() > GameParameters.BOARD_HEIGHT - GameParameters.BALL_RADIUS;
    }
}
