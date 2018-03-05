package com.codecool.lanpong.lanlayer;

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
    public void start() throws IOException{

        initializeSocket();
    }

    private void initializeSocket() throws IOException {

        serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connection on port :: " + port);
        Socket client = serverSocket.accept();
        System.out.println("Connection from :: " + client.getInetAddress());
    }

}
