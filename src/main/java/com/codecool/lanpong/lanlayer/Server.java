package com.codecool.lanpong.lanlayer;

import com.codecool.lanpong.game.GameController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    private String userName;
    private InetAddress address;
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket1;
    private Socket clientSocket2;
    private DataReadWriteController player1ioController;
    private DataReadWriteController player2ioController;

    public Server(int port, String userName) throws UnknownHostException {

        this.userName = userName;
        this.address = InetAddress.getLocalHost();
        this.port = port;
    }

    public void start() throws IOException {

        GameController.createStartingState();

        serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connection on port :: " + port);

        clientSocket1 = serverSocket.accept();
        player1ioController = new DataReadWriteController(clientSocket1);
        player1ioController.setGameStatus(GameController.getGameStatus());
        Thread player1 = new Thread(player1ioController);
        player1.start();
        System.out.println("Connection from :: " + clientSocket1.getInetAddress());

        clientSocket2 = serverSocket.accept();
        player2ioController = new DataReadWriteController(clientSocket2);
        Thread player2 = new Thread(player2ioController);
        player2.start();
        System.out.println("Connection from :: " + clientSocket2.getInetAddress());

        GameController gameController = new GameController();
        gameController.setup(player1ioController, player2ioController);
        gameController.handleGame();

    }
}
