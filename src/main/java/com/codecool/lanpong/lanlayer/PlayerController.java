package com.codecool.lanpong.lanlayer;

import java.io.IOException;
import java.net.Socket;

public interface PlayerController {

    public String getUserName();

    void start() throws IOException;
    Socket getSocket();
}
