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

    private Board board = new Board(800, 600);
    private Racket racket1 = new Racket();
    private Racket racket2 = new Racket();
    private Ball ball = new Ball();
    private boolean gameStarted;

    public WindowDisplay(){}

    public void start(Stage stage) throws Exception {
        ball.setxPos(board.getMaxWidth() / 2);
        ball.setyPos(board.getMaxHeight() / 2);
        racket1.setyPos(board.getMaxHeight() / 2);
        racket2.setyPos(board.getMaxHeight() / 2);
        racket1.setxPos(0);
        racket2.setxPos(board.getMaxWidth() - racket2.getWidth());
        Canvas canvas = new Canvas(board.getMaxWidth(), board.getMaxHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        canvas.setOnMouseMoved(e -> racket1.setyPos((int) e.getY()));
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

        if (gameStarted) {
            if (ball.getGoesRight()) {
                ball.setxPos(ball.getxPos() + 1);
            } else {
                ball.setxPos(ball.getxPos() - 1);
            }
            gc.fillOval(ball.getxPos(), ball.getyPos(), ball.getRadius(), ball.getRadius());
        } else {
            gc.setStroke(Color.YELLOW);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click to start", board.getMaxWidth() /2, board.getMaxHeight() / 2);
            ball.setxPos(board.getMaxWidth() / 2);
            ball.setyPos(board.getMaxHeight() / 2);
        }

        gc.fillRect(racket1.getxPos(), racket1.getyPos(), racket1.getWidth(), racket1.getHeight());
        gc.fillRect(racket2.getxPos(), racket2.getyPos(), racket2.getWidth(), racket2.getHeight());
    }

}
