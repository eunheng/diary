package com.example.diary.ServerCommunication;

import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;

public class SerializeDe implements Serializable {

    private static final long serialVersionUID = 1L;
    //private ValueDefine Define = new ValueDefine();
    //-------------private------------------------------------------
    private DataPacket WholePacket = new DataPacket();
    private DataPacket.Header HeaderSize  = new DataPacket.Header();
    private DataPacket.Pakcet MainPacket  = new DataPacket.Pakcet();

    //--------------------------------------------------------------

    public byte[] PacketSerialize(DataPacket.Header WholePacketSize, DataPacket.Pakcet MainPacketSerialize)
    {
        byte[] SerializePacket = new byte[2000];
        byte[] HeaderSerialize = new byte[64];
        byte[] MainSerialize = new byte [1936];
        MainSerialize = SerializationUtils.serialize((Serializable) MainPacketSerialize);
        WholePacketSize.PacketSize = MainSerialize.length;
        HeaderSerialize = SerializationUtils.serialize((Serializable) WholePacketSize);

        System.arraycopy(HeaderSerialize, 0, SerializePacket,0, HeaderSerialize.length);
        System.arraycopy(MainSerialize,0,SerializePacket,HeaderSerialize.length,MainSerialize.length);

        //System.out.println(HeaderSerialize.length);
        //System.out.println(MainSerialize.length);
        return SerializePacket;
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
