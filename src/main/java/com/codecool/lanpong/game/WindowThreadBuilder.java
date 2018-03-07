package com.codecool.lanpong.game;

import com.codecool.lanpong.view.WindowDisplay;
import javafx.application.Application;

public class WindowThreadBuilder implements Runnable {

    public WindowThreadBuilder() {
    }

    @Override
    public void run() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Application.launch(WindowDisplay.class);
    }
}
