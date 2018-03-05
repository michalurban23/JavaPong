package com.codecool.lanpong;

import com.codecool.lanpong.lanlayer.ControllerFactory;
import com.codecool.lanpong.lanlayer.ControllerFactoryImpl;

public class App {

    public static void main(String[] args) {

        ControllerFactory factory = new ControllerFactoryImpl(args);

    }
}
