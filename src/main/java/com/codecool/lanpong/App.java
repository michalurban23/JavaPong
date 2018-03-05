package com.codecool.lanpong;

import com.codecool.lanpong.lanlayer.PlayerController;
import com.codecool.lanpong.lanlayer.PlayerControllerFactory;
import com.codecool.lanpong.lanlayer.PlayerControllerFactoryImpl;

import java.io.IOException;

public class App {

    public static void main(String[] args) {

        try {
            PlayerControllerFactory factory = new PlayerControllerFactoryImpl(args);
            PlayerController controller = factory.get();
            controller.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
