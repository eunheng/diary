package com.example.diary.ServerCommunication;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import org.apache.commons.lang3.SerializationUtils;

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
        boolean RecvFlag = true;
    }
}
