package com.codecool.lanpong.game;

import com.codecool.lanpong.view.WindowDisplay;

public final class DataRetriever {

    public static int getBallXPos() {
        return WindowDisplay.getBallXPos();
    }

    public static int getBallYPos() {
        return WindowDisplay.getBallYPos();
    }

    public static int getRacket1XPos() {
        return WindowDisplay.getRacket1XPos();
    }

    public static int getRacket2XPos() {
        return WindowDisplay.getRacket2XPos();
    }

    public static boolean getBallDirection() {
        return WindowDisplay.getBallDirection();
    }
}
