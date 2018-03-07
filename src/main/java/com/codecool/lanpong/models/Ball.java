package com.codecool.lanpong.models;

public class Ball {

    private double radius;
    private int xPos;
    private int yPos;
    private boolean goesRight;

    public Ball() {
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
