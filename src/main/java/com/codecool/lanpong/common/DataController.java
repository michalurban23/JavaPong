package com.codecool.lanpong.common;

import java.net.Socket;

public class DataController implements Runnable {

    private Socket socket;

    public DataController(Socket socket) {

        this.socket = socket;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 30) {
            System.out.println(socket);
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
