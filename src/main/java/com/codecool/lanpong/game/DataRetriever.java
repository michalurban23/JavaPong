package com.codecool.lanpong.game;

import com.codecool.lanpong.lanlayer.PlayerController;
import com.codecool.lanpong.models.GameStatus;
import com.codecool.lanpong.view.WindowDisplay;

public final class DataRetriever {

    public static double getBallXPos() {
        return WindowDisplay.getBallXPos();
    }

    public static double getBallYPos() {
        return WindowDisplay.getBallYPos();
    }

    public static double getRacket1YPos() {
        return WindowDisplay.getRacket1YPos();
    }

    public static double getRacket2YPos() {
        return WindowDisplay.getRacket2YPos();
    }

    public static GameStatus getGameStatus() {
        return GameController.getGameStatus();
    }

    public static PlayerController getPlayer() {
        return GameController.getGameOwner();}
}
