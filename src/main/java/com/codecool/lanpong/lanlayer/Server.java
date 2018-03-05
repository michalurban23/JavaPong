package com.codecool.lanpong.lanlayer;

import java.net.InetAddress;

public class Server implements PlayerController {

    private String userName;
    private InetAddress address;
    private int port;

    public Server(String userName, InetAddress address, int port) {

        this.userName = userName;
        this.address = address;
        this.port = port;
    }

    @Override
    public void start() {
        ;
    }

}
