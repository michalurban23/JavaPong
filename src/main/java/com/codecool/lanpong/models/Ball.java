package com.codecool.lanpong.models;

public class Ball {

    private int radius;
    private int xPos;
    private int yPos;
    private double angle;  // 0-360, where 0 is right, 90 is top

    public Ball() {
    }

    public Ball(int xPos, int yPos, int radius) {

        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
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

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return this.angle;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(int radius) {

        this.radius = radius;
    }
}
