package com.codecool.lanpong.lanlayer;

import com.codecool.lanpong.game.GameController;
import com.codecool.lanpong.game.WindowThreadBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements PlayerController {

    private String userName;
    private InetAddress address;
    private int port;
    private ServerSocket serverSocket;

    public Server(String userName, InetAddress address, int port) {

        this.userName = userName;
        this.address = address;
        this.port = port;
    }

    @Override
    public void start() throws IOException {

        initializeSocket();
        GameController gameController = new GameController();
        gameController.handleGame();
    }

    private void initializeSocket() throws IOException {

        serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connection on port :: " + port);
        Socket client = serverSocket.accept();

        Thread t = new Thread(new WindowThreadBuilder());
        t.start();

        System.out.println("Connection from :: " + client.getInetAddress());
    }
}
