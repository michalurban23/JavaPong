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
    private Socket clientSocket;

    public Server(String userName, InetAddress address, int port) {

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

        serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connection on port :: " + port);
        clientSocket = serverSocket.accept();
        // clientSocket = new Socket(serverSocket.getInetAddress(), serverSocket.getLocalPort());

        Thread t = new Thread(new WindowThreadBuilder());
        t.start();

        System.out.println("Connection from :: " + clientSocket.getInetAddress());
    }

    public Socket getSocket() {

        return clientSocket;
    }

    @Override
    public String getUserName() {
        return userName;
    }
}
