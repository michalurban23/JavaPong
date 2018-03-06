package com.codecool.lanpong.game;

import com.codecool.lanpong.view.WindowDisplay;
import javafx.application.Application;

public class WindowThreadBuilder implements Runnable {

    public WindowThreadBuilder() {
    }

    @Override
    public void run() {
        Application.launch(WindowDisplay.class);
    }
}
