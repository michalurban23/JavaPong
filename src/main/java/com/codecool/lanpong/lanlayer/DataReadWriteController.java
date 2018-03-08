package com.codecool.lanpong.lanlayer;

import com.codecool.lanpong.models.GameStatus;

import java.io.*;
import java.net.Socket;

public class DataReadWriteController implements Runnable {

    private GameStatus gameStatus;

    private Socket socket1;
    // private Socket socket2;

    private OutputStream os1;
    // private OutputStream os2;
    private ObjectOutputStream oos1;
    // private ObjectOutputStream oos2;
    private InputStream is1;
    // private InputStream is2;
    private ObjectInputStream ois1;
    // private ObjectInputStream ois2;

    public DataReadWriteController(Socket socket1) throws IOException {

        this.socket1 = socket1;
        this.os1 = socket1.getOutputStream();
        this.is1 = socket1.getInputStream();
        this.oos1 = new ObjectOutputStream(os1);
        this.ois1 = new ObjectInputStream(is1);
    }

    public GameStatus getGameStatus() {

        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {

        this.gameStatus = gameStatus;
    }

    public GameStatus readData() throws IOException, ClassNotFoundException {

        // try {
        //     if (index == 1) {
                return (GameStatus) ois1.readObject();
            // } else {
            //     return (GameStatus) ois2.readObject();
            // }
        // } catch (Exception e) {
        //     throw new IOException();
        // }
    }

    public void sendData() throws IOException {

        oos1.writeObject(gameStatus);
        // oos2.writeObject(gs);
        oos1.flush();
        // oos2.flush();
    }

    public void sendIndexes() throws IOException {

        os1.write(1);
        os1.flush();
        // os2.write(2);
        // os2.flush();
    }

    public Socket getSocket1() {

        return socket1;
    }

    public void setSocket1(Socket socket1) {

        this.socket1 = socket1;
    }
    //
    // public Socket getSocket2() {
    //
    //     return socket2;
    // }
    //
    // public void setSocket2(Socket socket2) {
    //
    //     this.socket2 = socket2;
    // }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
                sendData();
                readData();
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
