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
    public final static double BALL_STARTING_ANGLE = (new Random()).nextBoolean() ? 0d : 180d;
    public final static double BALL_STARTING_SPEED = 10d;

    public final static double GAME_SPEED = 100;
    public final static long THROTTLE = 10;
}
