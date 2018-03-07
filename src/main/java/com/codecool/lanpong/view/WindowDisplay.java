package com.codecool.lanpong.view;

import com.codecool.lanpong.game.DataRetriever;
import com.codecool.lanpong.lanlayer.Server;
import com.codecool.lanpong.models.Ball;
import com.codecool.lanpong.models.Board;
import com.codecool.lanpong.models.GameStatus;
import com.codecool.lanpong.models.Racket;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WindowDisplay extends Application implements Display {

    private static Board board = new Board(800, 600);
    private GameStatus gameStatus;

    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(board.getMaxWidth(), board.getMaxHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        setInitialPositions();
        determineRacket(canvas);
        // canvas.setOnMouseClicked(e -> gameStarted = true);
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
    }

    private void determineRacket(Canvas canvas) {

        if (DataRetriever.getPlayer() instanceof Server) {
            canvas.setOnMouseMoved(e -> board.getRacket1().setyPos((int) e.getY()));
        } else {
            canvas.setOnMouseMoved(e -> board.getRacket2().setyPos((int) e.getY()));
        }
    }

    private void run(GraphicsContext gc) {

        refreshGC(gc);
        moveBall(gc);
        moveRackets(gc);
    }

    private void moveRackets(GraphicsContext gc) {

        gc.fillRect(board.getRacket1().getxPos(), board.getRacket1().getyPos(),
                board.getRacket1().getWidth(), board.getRacket1().getHeight());
        gc.fillRect(board.getRacket2().getxPos(), board.getRacket2().getyPos(),
                board.getRacket2().getWidth(), board.getRacket2().getHeight());
    }

    private void moveBall(GraphicsContext gc) {

        if (board.getBall().getGoesRight()) {
            board.getBall().setxPos(board.getBall().getxPos() + 1);
        } else {
            board.getBall().setxPos(board.getBall().getxPos() - 1);
        }
        gc.fillOval(board.getBall().getxPos(), board.getBall().getyPos(),
                board.getBall().getRadius(), board.getBall().getRadius());
    }

    private void setInitialPositions() {

        updateGameStatus();
        board.setRacket1(new Racket(0, gameStatus.getServerRacketPos()));
        board.setRacket2(new Racket(board.getMaxWidth()-board.getRacket1().getWidth(),
                gameStatus.getClientRacketPos()));
        board.setBall(new Ball(gameStatus.getBallX(), gameStatus.getBallY()));
    }

    private void refreshGC(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, board.getMaxWidth(), board.getMaxHeight());
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));
    }

    private void updateGameStatus() {

        gameStatus = DataRetriever.getGameStatus();
    }

    private void readDataFromGameStatus() {

    }

    public static int getBallXPos() {
        return board.getBall().getxPos();
    }

    public static int getBallYPos() {
        return board.getBall().getyPos();
    }

    public static int getRacket1XPos() {
        return board.getRacket1().getxPos();
    }

    public static int getRacket2XPos() {
        return board.getRacket2().getxPos();
    }

    public static boolean getBallDirection() {
        return board.getBall().getGoesRight();
    }
}
