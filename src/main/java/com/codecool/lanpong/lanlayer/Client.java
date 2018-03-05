package com.codecool.lanpong.lanlayer;

import java.net.InetAddress;

public class Client implements PlayerController {

    private String userName;
    private InetAddress address;
    private int port;

    public Client(String userName, InetAddress address, int port) {

        this.userName = userName;
        this.address = address;
        this.port = port;
    }

    @Override
    public void start() {
        ;
    }
}
