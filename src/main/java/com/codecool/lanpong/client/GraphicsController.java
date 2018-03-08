package com.codecool.lanpong.client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.net.InetAddress;

//
//import com.codecool.lanpong.common.model.GameStatus;
//import com.codecool.lanpong.game.GameController;
//import com.codecool.lanpong.lanlayer.DataReadWriteController;
//import com.codecool.lanpong.models.Ball;
//import com.codecool.lanpong.models.Racket;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.io.IOException;
//import java.net.Socket;
//
public class GraphicsController extends Application {

    private String playerName;
    private InetAddress serverAddress;
    private int serverPort;

    @Override
    public void start(Stage primaryStage) throws Exception {

        setup();
        connectToServer();
    }

    private void setup() {

        GraphicsController.Parameters parameters = getParameters();

    }

    private void connectToServer() {

    }
//
//    private Scene scene;
//    private BorderPane borderPane;
//    private HBox statusBar;
//
//    private GameStatus gameStatus;
//    private DataReadWriteController dataController;
//    private String name;
//
//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//
//    public void start(Stage stage) throws Exception {
//
//        WindowDisplay.Parameters parameters = getParameters();
//        System.out.println(parameters.getRaw());
//
//        dataController = new DataReadWriteController(new Socket(parameters.getRaw().get(0),
//                Integer.parseInt(parameters.getRaw().get(1))));
//        name = parameters.getRaw().get(2);
//        index = Integer.parseInt(parameters.getRaw().get(3));
//
//        Canvas canvas = new Canvas(board.getMaxWidth(), board.getMaxHeight());
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
//        // Timeline tl = new Timeline(new KeyFrame(Duration.millis(GameController.getGameSpeed()), e -> run(gc)));
//        tl.setCycleCount(Timeline.INDEFINITE);
//
//        setInitialPositions();
//        determineRacket(canvas);
//        // canvas.setOnMouseClicked(e -> gameStarted = true);
//        borderPane = new BorderPane();
//        borderPane.setCenter(canvas);
//        setupStatusBar();
//        borderPane.setBottom(statusBar);
//        scene = new Scene(borderPane);
//        stage.setScene(scene);
//        stage.show();
//        tl.play();
//    }
//
//    private void setupStatusBar() {
//        statusBar = new HBox(100);
//        Text name1 = new Text(gameStatus.getPlayer1Name() + "   " + gameStatus.getPlayer2Name());
//        statusBar.getChildren().add(name1);
//    }
//
//    private void run(GraphicsContext gc) {
//
//        refreshGC(gc);
//        updateGameStatus();
//        moveObjects(gc);
//        // System.out.println(gameStatus);
//        // System.out.println("Actual positions: " + gameStatus);
//    }
//
//    private void determineRacket(Canvas canvas) {
//
//        if (index == 1) {
//            canvas.setOnMouseMoved(e -> board.getRacket1().setyPos(e.getY()));
//        } else {
//            canvas.setOnMouseMoved(e -> board.getRacket2().setyPos(e.getY()));
//        }
//    }
//
//    private void moveObjects(GraphicsContext gc) {
//
//        gc.fillOval(board.getBall().getxPos(), board.getBall().getyPos(),
//                board.getBall().getRadius(), board.getBall().getRadius());
//        gc.fillRect(board.getRacket1().getxPos(), board.getRacket1().getyPos(),
//                board.getRacket1().getWidth(), board.getRacket1().getHeight());
//        gc.fillRect(board.getRacket2().getxPos(), board.getRacket2().getyPos(),
//                board.getRacket2().getWidth(), board.getRacket2().getHeight());
//    }
//
//    private void setInitialPositions() {
//
//        GameController.createStartingState();
//        gameStatus = GameController.getGameStatus();
//
//        board.setRacket1(new Racket(0, gameStatus.getPlayer1RacketPos()));
//        board.setRacket2(new Racket(board.getMaxWidth()-board.getRacket1().getWidth(),
//                gameStatus.getPlayer2RacketPos()));
//        board.setBall(new Ball(gameStatus.getBallX(), gameStatus.getBallY(), GameController.getBallRadius()));
//    }
//
//    private void refreshGC(GraphicsContext gc) {
//        gc.setFill(Color.BLACK);
//        gc.fillRect(0, 0, board.getMaxWidth(), board.getMaxHeight());
//        gc.setFill(Color.WHITE);
//        gc.setFont(Font.font(25));
//    }
//
//    private void updateGameStatus() {
//
//        try {
//            gameStatus = dataController.readData();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        // Update ball
//        board.getBall().setxPos(gameStatus.getBallX());
//        board.getBall().setyPos(gameStatus.getBallY());
//        // Update rackets
//        if (index == 1) {
//            board.getRacket2().setyPos(gameStatus.getPlayer2RacketPos());
//        } else {
//            board.getRacket1().setyPos(gameStatus.getPlayer1RacketPos());
//        }
//    }
//
//    public static double getBallXPos() {
//        return board.getBall().getxPos();
//    }
//
//    public static double getBallYPos() {
//        return board.getBall().getyPos();
//    }
//
//    public static double getRacket1YPos() {
//        return board.getRacket1().getyPos();
//    }
//
//    public static double getRacket2YPos() {
//        return board.getRacket2().getyPos();
//    }
}
