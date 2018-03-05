package com.codecool.lanpong.lanlayer;

public class MainController implements Controller {

    private boolean running;

    @Override
    public void start() {

        this.running = true;
        run();
    }

    private void run() {

        while (running) {

        }
    }

    @Override
    public void stop() {

        this.running = false;
    }
}
