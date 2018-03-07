package com.codecool.lanpong.models;

public class Racket {

    private int xPos;
    private int yPos;
    private final int height = 100;
    private final int width = 15;

    public Racket() {
    }

    public Racket(int xPos, int yPos) {
        this();
        setxPos(xPos);
        setyPos(yPos);
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
