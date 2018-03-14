package com.codecool.lanpong.server;

import com.codecool.lanpong.common.GameStatus;

import java.io.*;
import java.net.Socket;

public class ServerDao {

    private Socket clientSocket;

    private InputStream is;
    private ObjectInputStream ois;
    private OutputStream os;
    private ObjectOutputStream oos;

    public ServerDao(Socket clientSocket) {

        this.clientSocket = clientSocket;
    }

    public String getPlayerName() throws IOException {

        is = clientSocket.getInputStream();
        ois = new ObjectInputStream(is);

        try {
            return (String) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException();
        }
    }

    public double getRacketPosition() throws IOException {

        is = clientSocket.getInputStream();
        ois = new ObjectInputStream(is);

        try {
            return (double) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException();
        }
    }

    public void sendGameStatus(GameStatus gameStatus) throws IOException {

        os = clientSocket.getOutputStream();
        oos = new ObjectOutputStream(os);

        oos.writeObject(gameStatus);
        oos.flush();
    }

}
