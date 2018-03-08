package com.codecool.lanpong;

import com.codecool.lanpong.lanlayer.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class App {

    public static void main(String[] args) {

        try {
            Server server = new Server(Integer.parseInt(args[1]), args[2]);
            server.start();
        } catch (NumberFormatException | UnknownHostException e) {
            System.out.println("Wrong parameters");
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}
