package com.codecool.lanpong.lanlayer;

import java.net.InetAddress;

public class ControllerFactoryImpl implements ControllerFactory {

    private String userName;
    private InetAddress address;
    private int port;

    public ControllerFactoryImpl(String[] settings) {


    }

    @Override
    public Controller get() {

        return null;
    }
}
