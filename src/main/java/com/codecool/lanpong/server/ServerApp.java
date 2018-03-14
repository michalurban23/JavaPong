package com.codecool.lanpong.server;

import java.io.IOException;

public class ServerApp {

    public static void main(String[] args) {


        try {
            Server server = new Server(args[0]);
            server.setup();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
