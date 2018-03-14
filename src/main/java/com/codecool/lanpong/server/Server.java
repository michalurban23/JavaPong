package com.codecool.lanpong.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private InetAddress address;
    private int port;
    private ServerSocket serverSocket;
    private List<Socket> players;
    private boolean serverRunning;

    public Server(String port) throws UnknownHostException {

        this.port = Integer.parseInt(port);
    }

    public void setup() throws IOException {

        this.address = InetAddress.getLocalHost();
        this.players = new ArrayList<>();
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {

        serverRunning = true;

        while (serverRunning) {
            System.out.println("Waiting for connections on " + address + ":" + port);

            Socket client1Socket = serverSocket.accept();
            System.out.println("Connection from :: " + client1Socket.getInetAddress());
            Socket client2Socket = serverSocket.accept();
            System.out.println("Connection from :: " + client2Socket.getInetAddress());
            System.out.println("Initializing match");

            MatchController match = new MatchController(client1Socket, client2Socket);
            Thread t = new Thread(match);
            t.start();
        }
    }
}
