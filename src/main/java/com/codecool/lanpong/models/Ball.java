package com.codecool.lanpong.models;

public class Ball {

    private int xPos;
    private int yPos;
    private int[] direction;

    public Ball() {
        this.xPos = 0;
        this.yPos = 0;
        this.direction = new int[]{0, 0};
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

    public int[] getDirection() {
        return direction;
    }

    public void setDirection(int[] direction) {
        this.direction = direction;
    }
}
