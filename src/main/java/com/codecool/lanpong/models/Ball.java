package com.codecool.lanpong.models;

public class Ball {

    private double radius;
    private double xPos;
    private double yPos;
    private double angle;  // 0-360, where 0 is right, 90 is top

    public Ball() {
    }

    public Ball(double xPos, double yPos, double radius) {

        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
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

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return this.angle;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {

        this.radius = radius;
    }
}
