package com.codecool.lanpong.models;

public class Racket {

    private int xPos;
    private int yPos;
    private int height;
    private int width;

    public Racket() {
        this.xPos = 0;
        this.yPos = 0;
        this.height = 100;
        this.width = 15;
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
