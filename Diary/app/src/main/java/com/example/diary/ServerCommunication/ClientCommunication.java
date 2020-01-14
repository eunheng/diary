package com.example.diary.ServerCommunication;

import org.apache.commons.lang3.SerializationUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientCommunication implements Runnable{
    private ObjectInputStream Recver;
    private Socket SeverSocket = null;
    private byte[] TotalPacket = new byte[2000];
    private byte[] HeaderPacket = new byte[64];
    private byte[] MainPacket = new byte[1936];

    private byte[] TotalPacketReset = new byte[2000];
    private byte[] HeaderPacketReset = new byte[64];
    private byte[] MainPacketReset = new byte[1936];
    private DataPacket.Header HeaderDataPacket = new DataPacket.Header();
    private String Data="";
    private int count=0;
    private ArrayList DiaryList = new ArrayList();
    public DataPacket.Pakcet MainDataPacket = new DataPacket.Pakcet();
    //private ObjectOutputStream oos;

    public ClientCommunication(Socket ServerSock)
    {
        SeverSocket = ServerSock;
    }


    @Override
    public void run() {
        while(true)
        {

            try
            {
                Recver = new ObjectInputStream(SeverSocket.getInputStream());
                TotalPacket = (byte[])Recver.readObject();
                if(count != 1)
                {
                    Data = (String) SerializationUtils.deserialize(TotalPacket);
                }
                else if(count == 1)
                {
                    DiaryList = (ArrayList) SerializationUtils.deserialize(TotalPacket);
                }
                switch (count)
                {
                    case 0: MainDataPacket.Type = Data;
                        count ++;
                        break;
                    case 1 : MainDataPacket.Diary =DiaryList;
                        count ++;
                        break;
                }

                if(count == 2)
            {
                count = 0;
                ClientDataMNG DataAnalysis = new ClientDataMNG(HeaderDataPacket, MainDataPacket);
            }
                //System.arraycopy(TotalPacket, 0, HeaderPacket, 0, HeaderPacket.length);
                //System.arraycopy(TotalPacket, HeaderPacket.length, MainPacket, 0, MainPacket.length);
               // HeaderDataPacket = (DataPacket.Header) SerializationUtils.deserialize(HeaderPacket);
                //MainDataPacket = (DataPacket.Pakcet) SerializationUtils.deserialize(MainPacket);
                //ClientDataMNG DataAnalysis = new ClientDataMNG(HeaderDataPacket, MainDataPacket);

                //System.arraycopy(TotalPacketReset, 0, TotalPacket, 0, TotalPacket.length);
                //System.arraycopy(HeaderPacketReset, 0, HeaderPacket, 0, HeaderPacket.length);
                //System.arraycopy(MainPacketReset, 0, MainPacket, 0, MainPacket.length);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void TypeSend(Socket Sock, String Data)
    {
        Sender TrySend = new Sender(Data);
        Runnable Send = TrySend;
        Thread Start = new Thread(Send);
        Start.start();
    }
}
