package com.codecool.lanpong.client;

import com.codecool.lanpong.common.GameParameters;
import com.codecool.lanpong.common.GameStatus;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientDao implements Runnable {

    private String playerName;
    private Socket socket;
    private GameStatus gameStatus;
    private boolean gameRunning;

    private InputStream is;
    private ObjectInputStream ois;
    private OutputStream os;
    private ObjectOutputStream oos;

    public ClientDao(String playerName) {

        this.playerName = playerName;
    }

    public void setup(InetAddress serverAddress, int serverPort) throws IOException {

        socket = new Socket(serverAddress, serverPort);
        gameRunning = true;
        sendPlayerName();
    }

    @Override
    public void run() {

        while (gameRunning) {
            try {
                Thread.sleep(GameParameters.THROTTLE);
                readGameStatus();
                sendRacketPosition();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPlayerName() throws IOException {

        os = socket.getOutputStream();
        oos = new ObjectOutputStream(os);

        oos.writeObject(playerName);
        oos.flush();
    }

    private void sendRacketPosition() throws IOException {

        os = socket.getOutputStream();
        oos = new ObjectOutputStream(os);

        if (gameStatus.getPlayer1Name().equals(playerName)) {
            oos.writeDouble(gameStatus.getPlayer1racket());
        } else {
            oos.writeDouble(gameStatus.getPlayer2racket());
        }
        oos.flush();
    }

    private void readGameStatus() throws IOException {

        is = socket.getInputStream();
        ois = new ObjectInputStream(is);

        try {
            gameStatus = (GameStatus) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public GameStatus getGameStatus() {

        return gameStatus;
    }

    public void setGameRunning(boolean gameRunning) {

        this.gameRunning = gameRunning;
    }
}
