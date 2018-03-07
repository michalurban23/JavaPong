package com.codecool.lanpong.lanlayer;

import com.codecool.lanpong.game.GameController;
import com.codecool.lanpong.game.WindowThreadBuilder;
import com.codecool.lanpong.models.GameStatus;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements PlayerController {

    private String userName;
    private InetAddress address;
    private int port;
    private Socket socket;

    public Client(String userName, InetAddress address, int port) {

        this.userName = userName;
        this.address = address;
        this.port = port;
    }

    @Override
    public void start() throws IOException {

        initializeSocket();
        GameController gameController = new GameController(this);
        gameController.handleGame();
    }

    private void initializeSocket() throws IOException {

        socket = new Socket(address, port);
        System.out.println("Connected to server on :: " + address);

        Thread t = new Thread(new WindowThreadBuilder());
        t.start();
    }

    @Override
    public Socket getSocket() {

        return socket;
    }
}
