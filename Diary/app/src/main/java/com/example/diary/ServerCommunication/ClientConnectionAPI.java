package com.example.diary.ServerCommunication;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnectionAPI implements Runnable{
    public Socket ServerSocket = null;
    private String IP = "202.31.137.39";
    private int Port = 5001;
    public static ClientConnectionAPI singleton = new ClientConnectionAPI();

    private ClientConnectionAPI()
    {

    }

    public static ClientConnectionAPI getInstance()
    {
        return singleton;
    }

    @Override
    public void run() {
        try
        {
            ServerSocket = new Socket(IP,Port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
