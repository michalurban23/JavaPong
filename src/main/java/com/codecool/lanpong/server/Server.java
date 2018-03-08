package com.codecool.lanpong.server;

import com.codecool.lanpong.common.DataController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private InetAddress address;
    private int port;
    private ServerSocket serverSocket;
    private GameController gameController;

    public Server(String port) {

        this.address = InetAddress.getLoopbackAddress();
        this.port = Integer.parseInt(port);
    }

    public void setup(GameController gameController) {

        this.gameController = gameController;
    }

    public void start() throws IOException {

        serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connection on port :: " + port);

        for (int i=0; i<2; i++) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection from :: " + clientSocket.getInetAddress());
            DataController controller = new DataController(clientSocket);
            Thread t = new Thread(controller);
            t.start();
        }

        while (true) {
            System.out.println("Main controller working");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
