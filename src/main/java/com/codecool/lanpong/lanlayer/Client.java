package com.codecool.lanpong.lanlayer;

import com.codecool.lanpong.game.GameController;

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
        try {
            run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void run() throws InterruptedException {

        Thread t = new Thread(new GameController());

    }

    private void initializeSocket() throws IOException{

        socket = new Socket(address, port);
    }
}
