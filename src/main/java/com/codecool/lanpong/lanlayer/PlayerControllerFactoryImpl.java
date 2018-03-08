package com.codecool.lanpong.lanlayer;

import java.io.IOException;
import java.net.InetAddress;

public class PlayerControllerFactoryImpl implements PlayerControllerFactory {

    private String playerMode;
    private String userName;
    private InetAddress address;
    private int port;

    public PlayerControllerFactoryImpl(String[] settings) throws IOException {

        try {
            this.playerMode = settings[0];
            determineParameters(settings);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Wrong input parameters");
        }
    }

    private void determineParameters(String[] settings) throws IOException {

        switch (playerMode) {
            case "server":
                this.address = InetAddress.getLocalHost();
                this.port = Integer.parseInt(settings[1]);
                this.userName = settings[2];
                break;
            case "client":
                this.address = InetAddress.getByName(settings[1]);
                this.port = Integer.parseInt(settings[2]);
                this.userName = settings[3];
                break;
            default:
                throw new IllegalArgumentException("Wrong input parameters");
        }
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

    public String getUserName() {
        return userName;
    }
}
