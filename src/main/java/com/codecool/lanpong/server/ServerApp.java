package com.codecool.lanpong.server;

import java.io.IOException;

public class ServerApp {

    public static void main(String[] args) {

        GameController gameController = new GameController();
        Server server = new Server(args[0]);

        try {
            server.setup(gameController);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
