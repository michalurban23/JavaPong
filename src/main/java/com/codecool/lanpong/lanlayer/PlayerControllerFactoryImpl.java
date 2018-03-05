package com.codecool.lanpong.lanlayer;

import java.io.IOException;
import java.net.InetAddress;

public class PlayerControllerFactoryImpl implements PlayerControllerFactory {

    private String playerMode;
    private String userName;
    private InetAddress address;
    private int port;

    public PlayerControllerFactoryImpl(String[] settings) throws IOException {

        this.playerMode = settings[0];
        this.address = InetAddress.getByName(settings[1]);
        this.port = Integer.parseInt(settings[2]);
        this.userName = settings[3];
    }

    @Override
    public PlayerController get() {

        switch (playerMode) {
            case "server":
                return new Server(userName, address, port);
            case "client":
                return new Client(userName, address, port);
            default:
                throw new IllegalArgumentException();
        }
    }
}
