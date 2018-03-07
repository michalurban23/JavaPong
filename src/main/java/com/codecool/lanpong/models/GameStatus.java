package com.codecool.lanpong.models;

import java.io.Serializable;

public class GameStatus implements Serializable {

    private int ballX;
    private int ballY;
    private boolean ballDirection;
    private int serverRacketPos;
    private int clientRacketPos;

    public int getBallX() {

        return ballX;
    }

    public void setBallX(int ballX) {

        this.ballX = ballX;
    }

    public int getBallY() {

        return ballY;
    }

    public void setBallY(int ballY) {

        this.ballY = ballY;
    }

    public boolean isBallDirection() {

        return ballDirection;
    }

    public void setBallDirection(boolean ballDirection) {

        this.ballDirection = ballDirection;
    }

    public int getServerRacketPos() {

        return serverRacketPos;
    }

    public void setServerRacketPos(int serverRacketPos) {

        this.serverRacketPos = serverRacketPos;
    }

    public int getClientRacketPos() {

        return clientRacketPos;
    }

    public void setClientRacketPos(int clientRacketPos) {

        this.clientRacketPos = clientRacketPos;
    }

    @Override
    public String toString() {

        return "ballDirection: " + this.ballDirection + ", " +
                "ballX: " + this.ballX + ", " +
                "ballY: " + this.ballY + ", " +
                "serverRacket: " + this.serverRacketPos + ", " +
                "clientRacket: " + this.clientRacketPos;
    }
}
