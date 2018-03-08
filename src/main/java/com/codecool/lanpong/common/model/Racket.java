package com.codecool.lanpong.common.model;

public class Racket {

    private double xPos;
    private double yPos;
    private final int height = 100;
    private final int width = 15;

    public Racket() {
    }

    public Racket(double xPos, double yPos) {
        this();
        setxPos(xPos);
        setyPos(yPos);
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}