package com.codecool.lanpong;

import com.codecool.lanpong.lanlayer.PlayerController;
import com.codecool.lanpong.lanlayer.PlayerControllerFactory;
import com.codecool.lanpong.lanlayer.PlayerControllerFactoryImpl;

import java.io.IOException;
import java.net.UnknownHostException;

public class App {

    public static void main(String[] args) {

        try {
            PlayerControllerFactory factory = new PlayerControllerFactoryImpl(args);
            PlayerController controller = factory.get();
            controller.start();
<<<<<<< HEAD
            while (true) {
                ;
            }
        } catch (IOException e) {
            e.printStackTrace();
=======
        } catch (NumberFormatException | UnknownHostException e) {
            System.out.println("Wrong parameters");
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
>>>>>>> 633b60ddcbd413ad77f218a1ef5929c74a172aae
        }
    }
}
