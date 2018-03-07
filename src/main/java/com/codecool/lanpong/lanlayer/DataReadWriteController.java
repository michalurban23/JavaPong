package com.codecool.lanpong.lanlayer;

import com.codecool.lanpong.models.GameStatus;

import java.io.*;
import java.net.Socket;

public class DataReadWriteController {

    private Socket socket;
    private OutputStream os;
    private ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream ois;

    public DataReadWriteController(Socket socket) throws IOException {

        this.socket = socket;
    }

    public void setup() throws IOException {

        this.os = socket.getOutputStream();
        this.is = socket.getInputStream();

        this.oos = new ObjectOutputStream(os);
        this.ois = new ObjectInputStream(is);
    }

    public GameStatus readData() throws IOException, ClassNotFoundException {

        return (GameStatus) ois.readObject();
    }

    public void sendData(GameStatus gs) throws IOException {

        oos.writeObject(gs);
        oos.flush();
    }

}
