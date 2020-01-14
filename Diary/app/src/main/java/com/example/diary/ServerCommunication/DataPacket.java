package com.example.diary.ServerCommunication;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class DataPacket  {
    public class DiaryList
    {
        String Name= "";
        String Question= "";
        String DiaryContents= "";
        String Year= "";
        String Month= "";
        String Date= "";
    }
    static public class Header implements Serializable
    {
        int PacketSize;
    }

    static public class Pakcet implements Serializable
    {
        //String으로
        int Type = 0;
        String Name = "";
        String CoupleName = "";
        String Comments = "";
        String Year= "";
        String Month= "";
        String Date= "";
        String Question= "";
        Socket SocketValue;
        ArrayList<DiaryList> Diary;
        //없어짐
        boolean RecvFlag = true;
    }
}
