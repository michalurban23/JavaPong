package com.codecool.lanpong.models;

public class Board {

    private int maxWidth;
    private int maxHeight;
    private Ball ball;
    private Racket racket1;
    private Racket racket2;

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

    public Racket getRacket1() {
        return racket1;
    }

    public void setRacket1(Racket racket1) {
        this.racket1 = racket1;
    }

    public Racket getRacket2() {
        return racket2;
    }

    public void setRacket2(Racket racket2) {
        this.racket2 = racket2;
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
