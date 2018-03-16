package com.codecool.lanpong.common;

import java.util.Random;

public class GameParameters {

    public final static double BOARD_WIDTH = 800d;
    public final static double BOARD_HEIGHT = 600d;

    public final static double BALL_RADIUS = 15d;
    public final static double RACKET_WIDTH = 15d;
    public final static double RACKET_HEIGHT = 100d;

    public final static double BALL_STARTING_X = BOARD_WIDTH / 2;
    public final static double BALL_STARTING_Y = BOARD_HEIGHT / 2;
    public final static double BALL_STARTING_ANGLE = calculateRandomAngle();

    public final static double BALL_STARTING_SPEED = 1d;
    public final static double TIME_FACTOR = 1.2d;

    public final static double GAME_SPEED = 10;
    public final static long THROTTLE = 10;

    private static double calculateRandomAngle() {

        Random rand = new Random();
        double angle = 90 + 180*rand.nextInt(1) + rand.nextInt(90);

        return 2 * Math.PI * angle/360;
    }
}
