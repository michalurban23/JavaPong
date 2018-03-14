package com.codecool.lanpong.server;

import com.codecool.lanpong.common.GameParameters;
import com.codecool.lanpong.common.GameStatus;

import java.io.IOException;
import java.net.Socket;

public class MatchController implements Runnable {

    private Socket player1Socket;
    private Socket player2Socket;
    private GameController controller;
    private ServerDao player1Dao;
    private ServerDao player2Dao;
    private boolean matchRunning;
    private GameStatus gameStatus;

    public MatchController(Socket player1Socket, Socket player2Socket) {

        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
    }

    private void setup() {

        gameStatus = GameStatus.createInitialStatus();
        this.controller = new GameController(gameStatus);
        this.player1Dao = new ServerDao(player1Socket);
        this.player2Dao = new ServerDao(player2Socket);
    }

    public void run() {

        setup();
        determinePlayers();

        while (matchRunning) {
            sendData();
            readData();
            controller.analyzeGameStatus();
        }
    }

    private void determinePlayers() {

        try {
            gameStatus.setPlayer1Name(player1Dao.getPlayerName());
            gameStatus.setPlayer2Name(player2Dao.getPlayerName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameStatus.setPlayer1Ready(true);
        gameStatus.setPlayer2Ready(true);

        matchRunning = true;
    }

    private void sendData() {

        try {
            player1Dao.sendGameStatus(gameStatus);
            player2Dao.sendGameStatus(gameStatus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() {

        try {
            gameStatus.setPlayer1racket(player1Dao.getRacketPosition());
            gameStatus.setPlayer2racket(player2Dao.getRacketPosition());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
