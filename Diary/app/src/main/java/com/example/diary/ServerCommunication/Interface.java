package com.example.diary.ServerCommunication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Interface implements Serializable {

    private ClientConnectionAPI ServerSock = ClientConnectionAPI.getInstance();
    private Socket ServerSocket = null;
    private SerializeDe PacketSerialize = new SerializeDe();
    private static final long serialVersionUID = 1L;


    public Interface()
    {
        ServerSocket = ServerSock.ServerSocket;
    }
    //------------------------------Recv--------------------
    /**
    public ArrayList<String> Result(입력값){
        return 입력값;
    }**/

    public void NoticeDiaryEditSuccess() //Type 11
    {

    }

    public void NoticeDiaryRegistSuccess() //Type 12
    {

    }

    public void NoticeBrokenCouple() //Type 13
    {

    }

    public void NoticeQuestion() //Type 14
    {

    }

    public void NoticeRegistQeustion() //Type 15
    {

    }

    public void NoticeReFlashDiary() //Type 16
    {

    }

    public void NoticeInitializeDiary() //Type 17
    {

    }

    public void NoticeNextYMDiary() //Type 18
    {

    }

    public void NoticeSetCpuleNotice() //Type 19
    {

    }



//------------------------------Sender-----------------------------------
    public void NameNoticeSender(String Name) throws IOException
    {
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        MainDataPacketSender.Name = Name;
        setPacket(1, MainDataPacketSender);

    }

    public void DiaryRegistSender(String Name, String Contents, String Year, String Month, String Date) throws IOException
    {//일기등록
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        MainDataPacketSender.Name = Name;
        MainDataPacketSender.Comments = Contents;
        MainDataPacketSender.Year = Year;
        MainDataPacketSender.Month = Month;
        MainDataPacketSender.Date = Date;
        setPacket(2,MainDataPacketSender);
    }

    public void DiaryEditSender(String Name, String Contents, String Year, String Month, String Date) throws IOException
    {//일기 수정
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        MainDataPacketSender.Name = Name;
        MainDataPacketSender.Comments = Contents;
        MainDataPacketSender.Year = Year;
        MainDataPacketSender.Month = Month;
        MainDataPacketSender.Date = Date;
        setPacket(3,MainDataPacketSender);
    }

    public void RegistQuestionSender(String Name, String Question, String Year, String Month, String Date) throws IOException
    {//질문 등록
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        MainDataPacketSender.Name = Name;
        MainDataPacketSender.Question = Question;
        MainDataPacketSender.Year = Year;
        MainDataPacketSender.Month = Month;
        MainDataPacketSender.Date = Date;
        setPacket(4,MainDataPacketSender);
    }

    public void SetCoupleSender(String Name, String CoupleName) throws IOException
    {//커플 맺기
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        MainDataPacketSender.Name = Name;
        MainDataPacketSender.CoupleName = CoupleName;
        setPacket(5,MainDataPacketSender);
    }

    public void BrokenCoupleSender(String Name) throws IOException
    {//커플끊기
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        MainDataPacketSender.Name = Name;
        setPacket(6,MainDataPacketSender);
    }

    public void DeleteUserInfoSender()  throws IOException
    {//없음
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        setPacket(7,MainDataPacketSender);
    }

    public void ReFreshDiarySender(String Name, String Year, String Month)  throws IOException
    {
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        MainDataPacketSender.Name =Name;
        MainDataPacketSender.Year = Year;
        MainDataPacketSender.Month = Month;
        setPacket(8,MainDataPacketSender);
    }

    public void InitSender(String Name, String Year, String Month)  throws IOException
    {
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        MainDataPacketSender.Name =Name;
        MainDataPacketSender.Year = Year;
        MainDataPacketSender.Month = Month;
        setPacket(9,MainDataPacketSender);
    }

    public void NextMYSender(String Name, String Year, String Month)  throws IOException
    {
        DataPacket.Pakcet MainDataPacketSender = new DataPacket.Pakcet();
        MainDataPacketSender.Name =Name;
        MainDataPacketSender.Year = Year;
        MainDataPacketSender.Month = Month;
        setPacket(10,MainDataPacketSender);
    }


    public void setPacket(int Type, DataPacket.Pakcet SenderMainPacket) throws IOException
    {
        byte[] TotalPacketSender = new byte[2000];

        DataPacket.Header HeaderDataPacketSender = new DataPacket.Header();

        SenderMainPacket.Type = Type;
        TotalPacketSender = PacketSerialize.PacketSerialize(HeaderDataPacketSender,SenderMainPacket);
        ObjectOutputStream oos;
        oos = new ObjectOutputStream(ServerSock.ServerSocket.getOutputStream());
        oos.writeObject(TotalPacketSender);
        oos.flush();
    }
}
