package com.example.diary.ServerCommunication;

import org.apache.commons.lang3.SerializationUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Sender implements Runnable , Serializable{



    private DataPacket.Pakcet Packet = new DataPacket.Pakcet();
    private byte[] SenderPacket = new byte[2000];
    Sender (String Packet)
    {
        Data = Packet;
    }
    String Data = "";
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;

    byte[] B = new byte[0];
    @Override
    public void run()
    {
        try
        {

            B = SerializationUtils.serialize((Serializable)Data);
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(ClientConnectionAPI.getInstance().ServerSocket.getOutputStream());
            oos.writeObject(B);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
