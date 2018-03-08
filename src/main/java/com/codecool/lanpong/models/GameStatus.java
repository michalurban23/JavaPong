package com.codecool.lanpong.models;

import java.io.Serializable;

public class GameStatus implements Serializable {

    private double ballX;
    private double ballY;
    private double ballAngle;
    private double player1RacketPos;
    private double player2RacketPos;
    private int[] score;
    private String player1Name;
    private String player2Name;
    private boolean bothPlayersConnected;

    public boolean isBothPlayersConnected() {

        return bothPlayersConnected;
    }

    public void setBothPlayersConnected(boolean bothPlayersConnected) {

        this.bothPlayersConnected = bothPlayersConnected;
    }

    public String getPlayer1Name() {

        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {

        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {

        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {

        this.player2Name = player2Name;
    }

    public double getBallX() {

        return ballX;
    }

    public void setBallX(double ballX) {

        this.ballX = ballX;
    }

    public double getBallY() {

        return ballY;
    }

    public void setBallY(double ballY) {

        this.ballY = ballY;
    }

    public double getBallAngle() {

        return ballAngle;
    }

    public void setBallAngle(double ballAngle) {

        if (ballAngle >= 360) {
            this.ballAngle = ballAngle - 360;
        } else
            this.ballAngle = ballAngle;
    }

    public double getPlayer1RacketPos() {

        return player1RacketPos;
    }

    public void setPlayer1RacketPos(double player1RacketPos) {

        this.player1RacketPos = player1RacketPos;
    }

    public double getPlayer2RacketPos() {

        return player2RacketPos;
    }

    public void setPlayer2RacketPos(double player2RacketPos) {

        this.player2RacketPos = player2RacketPos;
    }

    public int[] getScore() {

        return score;
    }

    public void setScore(int[] score) {

        this.score = score;
    }

    @Override
    public String toString() {

        return "ballAngle: " + this.ballAngle + ", " +
                "ballX: " + this.ballX + ", " +
                "ballY: " + this.ballY + ", " +
                "player1Racket: " + this.player1RacketPos + ", " +
                "player2Racket: " + this.player2RacketPos;
    }
}
