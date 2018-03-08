package com.codecool.lanpong.view;

import com.codecool.lanpong.game.DataRetriever;
import com.codecool.lanpong.game.GameController;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

public class WindowDisplay extends Application implements Display {

    private static Board board = new Board(GameController.getBoardWidth(), GameController.getBoardHeight());
    private Scene scene;
    private BorderPane borderPane;
    private HBox statusBar;
    private GameStatus gameStatus;

    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(board.getMaxWidth(), board.getMaxHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
         Timeline tl = new Timeline(new KeyFrame(Duration.millis(1), e -> run(gc)));
//        Timeline tl = new Timeline(new KeyFrame(Duration.millis(GameController.getGameSpeed()), e -> run(gc)));
//        Timeline tl = new Timeline(new KeyFrame(Duration.millis(GameController.getGameSpeed()), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        setInitialPositions();
        determineRacket(canvas);
        // canvas.setOnMouseClicked(e -> gameStarted = true);
        borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        setupStatusBar();
        borderPane.setBottom(statusBar);
        scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
        tl.play();
    }

    private void setupStatusBar() {
        statusBar = new HBox(100);
        Text name1 = new Text(DataRetriever.getUserName());
        statusBar.getChildren().add(name1);
    }

    private void run(GraphicsContext gc) {

        refreshGC(gc);
        updateGameStatus();
        moveObjects(gc);
        System.out.println(gameStatus);
        // System.out.println("Actual positions: " + gameStatus);
    }

    private void determineRacket(Canvas canvas) {

        if (DataRetriever.getPlayer() instanceof Server) {
            canvas.setOnMouseMoved(e -> board.getRacket1().setyPos((int) e.getY()));
        } else {
            canvas.setOnMouseMoved(e -> board.getRacket2().setyPos((int) e.getY()));
        }
    }

    private void moveObjects(GraphicsContext gc) {

        gc.fillOval(board.getBall().getxPos(), board.getBall().getyPos(),
                board.getBall().getRadius(), board.getBall().getRadius());
        gc.fillRect(board.getRacket1().getxPos(), board.getRacket1().getyPos(),
                board.getRacket1().getWidth(), board.getRacket1().getHeight());
        gc.fillRect(board.getRacket2().getxPos(), board.getRacket2().getyPos(),
                board.getRacket2().getWidth(), board.getRacket2().getHeight());
    }

    private void setInitialPositions() {

        gameStatus = DataRetriever.getGameStatus();

        board.setRacket1(new Racket(0, gameStatus.getServerRacketPos()));
        board.setRacket2(new Racket(board.getMaxWidth()-board.getRacket1().getWidth(),
                gameStatus.getClientRacketPos()));
        board.setBall(new Ball(gameStatus.getBallX(), gameStatus.getBallY(), GameController.getBallRadius()));
    }

    private void refreshGC(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, board.getMaxWidth(), board.getMaxHeight());
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));
    }

    private void updateGameStatus() {

        gameStatus = DataRetriever.getGameStatus();
        // Update ball
        board.getBall().setxPos(gameStatus.getBallX());
        board.getBall().setyPos(gameStatus.getBallY());
        // Update rackets
        if (DataRetriever.getPlayer() instanceof Server) {
            board.getRacket2().setyPos(gameStatus.getClientRacketPos());
        } else {
            board.getRacket1().setyPos(gameStatus.getServerRacketPos());
        }
    }

    public static int getBallXPos() {
        return board.getBall().getxPos();
    }

    public static int getBallYPos() {
        return board.getBall().getyPos();
    }

    public static int getRacket1YPos() {
        return board.getRacket1().getyPos();
    }

    public static int getRacket2YPos() {
        return board.getRacket2().getyPos();
    }
}
