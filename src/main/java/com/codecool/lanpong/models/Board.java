package com.codecool.lanpong.models;

public class Board {

    private int maxWidth;
    private int maxHeight;
    private Ball ball;
    private Racket player1;
    private Racket player2;

    public Board(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Racket getPlayer1() {
        return player1;
    }

    public void setPlayer1(Racket player1) {
        this.player1 = player1;
    }

    public Racket getPlayer2() {
        return player2;
    }

    public void setPlayer2(Racket player2) {
        this.player2 = player2;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }
}
