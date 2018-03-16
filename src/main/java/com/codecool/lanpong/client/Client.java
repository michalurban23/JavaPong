package com.codecool.lanpong.client;

import com.codecool.lanpong.common.GameParameters;
import com.codecool.lanpong.common.GameStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Application {

    private String playerName;
    private InetAddress serverAddress;
    private int serverPort;
    private ClientDao dao;
    private GameStatus gameStatus;

    private Scene scene;
    private BorderPane borderPane;
    private HBox statusBar;

    @Override
    public void start(Stage primaryStage) throws Exception {

        setup();
        connectToServer();
        runGUI();
    }

    private void setup() throws UnknownHostException {

        Client.Parameters parameters = getParameters();
        serverAddress = InetAddress.getByName(parameters.getRaw().get(0));
        serverPort = Integer.parseInt(parameters.getRaw().get(1));
        askForName();
    }

    private void askForName() {

        Scanner sc = new Scanner(System.in);
        System.out.println("What's your name?");
        playerName = sc.nextLine();
        sc.close();
    }

    private void connectToServer() throws IOException {

        dao = new ClientDao(playerName);
        dao.setup(serverAddress, serverPort);
        gameStatus = dao.getGameStatus();

        Thread t = new Thread(dao);
        t.start();
    }

    private void runGUI() {

        Canvas canvas = new Canvas(GameParameters.BOARD_WIDTH, GameParameters.BOARD_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(GameParameters.GAME_SPEED), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

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
        Text name1 = new Text(gameStatus.getPlayer1Name() + "   " + gameStatus.getPlayer2Name());
        statusBar.getChildren().add(name1);
    }

    private void run(GraphicsContext gc) {

        refreshGC(gc);
        updateGameStatus();
        moveObjects(gc);
    }

    private void determineRacket(Canvas canvas) {

        if (gameStatus.getPlayer1Name().equals(playerName)) {
            canvas.setOnMouseMoved(e -> gameStatus.setPlayer1racket(e.getY()));
        } else {
            canvas.setOnMouseMoved(e -> gameStatus.setPlayer2racket(e.getY()));
        }
    }

    private void moveObjects(GraphicsContext gc) {

        gc.fillOval(gameStatus.getBallXpos(), gameStatus.getBallYpos(),
                GameParameters.BALL_RADIUS, GameParameters.BALL_RADIUS);
        gc.fillRect(0, gameStatus.getPlayer1racket(),
                GameParameters.RACKET_WIDTH, GameParameters.RACKET_HEIGHT);
        gc.fillRect(GameParameters.BOARD_WIDTH - GameParameters.RACKET_WIDTH, gameStatus.getPlayer2racket(),
                GameParameters.RACKET_WIDTH, GameParameters.RACKET_HEIGHT);
    }

    private void refreshGC(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameParameters.BOARD_HEIGHT, GameParameters.BOARD_WIDTH);
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));
    }

    private void updateGameStatus() {

    }
}
