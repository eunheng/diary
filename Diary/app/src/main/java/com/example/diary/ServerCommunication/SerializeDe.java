package com.example.diary.ServerCommunication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

import org.apache.commons.lang3.SerializationUtils;

public class SerializeDe implements Serializable {

    private static final long serialVersionUID = 1L;
    //private ValueDefine Define = new ValueDefine();
    //-------------private------------------------------------------
    private DataPacket WholePacket = new DataPacket();
    private DataPacket.Header HeaderSize  = new DataPacket.Header();
    private DataPacket.Pakcet MainPacket  = new DataPacket.Pakcet();

    //--------------------------------------------------------------

    public byte[] PacketSerialize(DataPacket.Header HeaderPacketSize, DataPacket.Pakcet MainPacketSerialize)
    {
        byte[] MainPacket = new byte[0];
        byte[] HeaderPacket = new byte[0];
        byte[] Total = new byte[2000];
        try(ByteArrayOutputStream boas = new ByteArrayOutputStream()) {
            try(ObjectOutputStream oos = new ObjectOutputStream(boas))
            {
                oos.writeObject(MainPacketSerialize);
                MainPacket =  boas.toByteArray();
                HeaderPacketSize.PacketSize = MainPacket.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(ByteArrayOutputStream boas1 = new ByteArrayOutputStream()) {
            try(ObjectOutputStream oos1 = new ObjectOutputStream(boas1))
            {
                oos1.writeObject(HeaderPacketSize);
                HeaderPacket =  boas1.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.arraycopy(HeaderPacket, 0, Total,0, HeaderPacket.length);
        System.arraycopy(MainPacket,0,Total,HeaderPacket.length,MainPacket.length);
        /*byte[] SerializePacket = new byte[2000];
        byte[] HeaderSerialize;
        byte[] MainSerialize;
        MainSerialize = SerializationUtils.serialize((Serializable)MainPacketSerialize);
        HeaderPacketSize.PacketSize = MainSerialize.length;
        HeaderSerialize = SerializationUtils.serialize((Serializable)HeaderPacketSize);

        System.arraycopy(HeaderSerialize, 0, SerializePacket,0, HeaderSerialize.length);
        System.arraycopy(MainSerialize,0,SerializePacket,HeaderSerialize.length,MainSerialize.length);

        //System.out.println(HeaderSerialize.length);
        //System.out.println(MainSerialize.length);
        return SerializePacket;*/
        return Total;
    }

    public DataPacket.Header HeaderDeSerialize(byte[] TotalPacket)
    {
        byte[] SerializePacket = new byte[2000];
        byte[] HeaderSerialize = new byte[64];
        byte[] MainSerialize = new byte [1936];
        SerializePacket = TotalPacket;
        System.arraycopy(SerializePacket, 0, HeaderSerialize, 0, HeaderSerialize.length);
        return HeaderSize;
    }

    public DataPacket.Pakcet MainDeSerialize(byte[] TotalPacket)
    {
        byte[] SerializePacket = new byte[2000];
        byte[] HeaderSerialize = new byte[64];
        byte[] MainSerialize = new byte [1936];
        SerializePacket = TotalPacket;
        System.arraycopy(SerializePacket, HeaderSerialize.length,MainSerialize, 0, MainSerialize.length);
        return MainPacket;
    }

    public void ClearPacket()
    {
        byte[] SerializePacket = new byte[2000];
        byte[] HeaderSerialize = new byte[64];
        byte[] MainSerialize = new byte [1936];
        SerializePacket = new byte[2000];
        HeaderSerialize = new byte[64];
        MainSerialize = new byte[1936];
    }
}
