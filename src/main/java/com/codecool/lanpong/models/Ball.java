package com.codecool.lanpong.models;

public class Ball {

    private final double radius = 15;
    private int xPos;
    private int yPos;
    private boolean goesRight;

    public Ball() {
    }

    public Ball(int xPos, int yPos) {

        this.xPos = xPos;
        this.yPos = yPos;
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

    public boolean getGoesRight() {
        return goesRight;
    }

    public void setGoesRight(boolean goesRight) {
        this.goesRight = goesRight;
    }

    public double getRadius() {
        return radius;
    }
}
