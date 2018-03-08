package com.codecool.lanpong.models;

import java.io.Serializable;

public class GameStatus implements Serializable {

    private double ballX;
    private double ballY;
    private double ballAngle;
    private double serverRacketPos;
    private double clientRacketPos;

    public double getBallX() {

        return ballX;
    }

    public void setBallX(double ballX) {

        this.ballX = ballX;
    }

    public double getBallY() {

        return ballY;
    }

    public void setBallY(double ballY) {

        this.ballY = ballY;
    }

    public double getBallAngle() {

        return ballAngle;
    }

    public void setBallAngle(double ballAngle) {

        if (ballAngle >= 360) {
            this.ballAngle = ballAngle - 360;
        } else
            this.ballAngle = ballAngle;
    }

    public double getServerRacketPos() {

        return serverRacketPos;
    }

    public void setServerRacketPos(double serverRacketPos) {

        this.serverRacketPos = serverRacketPos;
    }

    public double getClientRacketPos() {

        return clientRacketPos;
    }

    public void setClientRacketPos(double clientRacketPos) {

        this.clientRacketPos = clientRacketPos;
    }

    @Override
    public String toString() {

        return "ballAngle: " + this.ballAngle + ", " +
                "ballX: " + this.ballX + ", " +
                "ballY: " + this.ballY + ", " +
                "serverRacket: " + this.serverRacketPos + ", " +
                "clientRacket: " + this.clientRacketPos;
    }
}
