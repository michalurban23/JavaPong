package com.codecool.lanpong.common;

import java.io.Serializable;
import java.util.Arrays;

public class GameStatus implements Serializable {

    private String player1Name;
    private String player2Name;

    private boolean player1Ready;
    private boolean player2Ready;

    private double ballXpos;
    private double ballYpos;

    private double player1racket;
    private double player2racket;

    private int[] score;

    public static GameStatus createInitialStatus() {

        GameStatus gameStatus = new GameStatus();

        gameStatus.setBallXpos(GameParameters.BALL_STARTING_X);
        gameStatus.setBallYpos(GameParameters.BALL_STARTING_Y);
        gameStatus.setPlayer1racket(0);
        gameStatus.setPlayer2racket(GameParameters.BOARD_WIDTH - GameParameters.RACKET_WIDTH);
        gameStatus.setScore(new int[] {0,0});

        return gameStatus;
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

    public boolean isPlayer1Ready() {
        return player1Ready;
    }

    public void setPlayer1Ready(boolean player1Ready) {
        this.player1Ready = player1Ready;
    }

    public boolean isPlayer2Ready() {
        return player2Ready;
    }

    public void setPlayer2Ready(boolean player2Ready) {
        this.player2Ready = player2Ready;
    }

    public double getBallXpos() {
        return ballXpos;
    }

    public void setBallXpos(double ballXpos) {
        this.ballXpos = ballXpos;
    }

    public double getBallYpos() {
        return ballYpos;
    }

    public void setBallYpos(double ballYpos) {
        this.ballYpos = ballYpos;
    }

    public double getPlayer1racket() {
        return player1racket;
    }

    public void setPlayer1racket(double player1racket) {
        this.player1racket = player1racket;
    }

    public double getPlayer2racket() {
        return player2racket;
    }

    public void setPlayer2racket(double player2racket) {
        this.player2racket = player2racket;
    }

    public int[] getScore() {
        return score;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "GameStatus{" +
                "player1Name='" + player1Name + '\'' +
                ", player2Name='" + player2Name + '\'' +
                ", player1Ready=" + player1Ready +
                ", player2Ready=" + player2Ready +
                ", ballXpos=" + ballXpos +
                ", ballYpos=" + ballYpos +
                ", player1racket=" + player1racket +
                ", player2racket=" + player2racket +
                ", score=" + Arrays.toString(score) +
                '}';
    }
}
