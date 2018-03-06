package com.codecool.lanpong;

import com.codecool.lanpong.lanlayer.PlayerController;
import com.codecool.lanpong.lanlayer.PlayerControllerFactory;
import com.codecool.lanpong.lanlayer.PlayerControllerFactoryImpl;
import com.codecool.lanpong.view.WindowDisplay;

import java.io.IOException;
import java.net.UnknownHostException;

public class App {

    public static void main(String[] args) {

        try {
            PlayerControllerFactory factory = new PlayerControllerFactoryImpl(args);
            PlayerController controller = factory.get();
            controller.start();
        } catch (NumberFormatException | UnknownHostException e) {
            System.out.println("Wrong parameters");
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}
