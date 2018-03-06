package com.codecool.lanpong.view;

import com.codecool.lanpong.models.Ball;
import com.codecool.lanpong.models.Board;
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
    private Racket racket1 = new Racket();
    private Racket racket2 = new Racket();
    private Ball ball = new Ball();
    private boolean gameStarted = false;

    public WindowDisplay(){
    }

    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(board.getMaxWidth(), board.getMaxHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        canvas.setOnMouseMoved(e -> board.getRacket1().setyPos((int) e.getY()));
        canvas.setOnMouseClicked(e -> gameStarted = true);
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
    }

    private void run(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, board.getMaxWidth(), board.getMaxHeight());
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));
        ball.setyPos(300);
        ball.setxPos(400);
        ball.setGoesRight(true);
        racket1.setyPos(board.getMaxHeight() / 2);
        racket1.setxPos(0);
        racket2.setyPos(board.getMaxHeight() / 2);
        racket2.setxPos(board.getMaxWidth() - racket2.getWidth());
        board.setRacket1(racket1);
        board.setRacket2(racket2);
        board.setBall(ball);

        if (gameStarted) {
            if (board.getBall().getGoesRight()) {
                board.getBall().setxPos(board.getBall().getxPos() + 1);
            } else {
                board.getBall().setxPos(board.getBall().getxPos() - 1);
            }
            gc.fillOval(board.getBall().getxPos(), board.getBall().getyPos(), board.getBall().getRadius(), board.getBall().getRadius());
        } else {
            gc.setStroke(Color.YELLOW);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click to start", board.getMaxWidth() /2, board.getMaxHeight() / 2);
        }

        gc.fillRect(board.getRacket1().getxPos(), board.getRacket1().getyPos(), board.getRacket1().getWidth(), board.getRacket1().getHeight());
        gc.fillRect(board.getRacket2().getxPos(), board.getRacket2().getyPos(), board.getRacket2().getWidth(), board.getRacket2().getHeight());
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
