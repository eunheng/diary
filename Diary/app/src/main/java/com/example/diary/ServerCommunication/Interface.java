package com.example.diary.ServerCommunication;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class Interface implements Serializable, onDiaryData {

    private ClientConnectionAPI ServerSock = ClientConnectionAPI.getInstance();
    private Socket ServerSocket = null;
    private SerializeDe PacketSerialize = new SerializeDe();
    private ClientCommunication SendeMSG = new ClientCommunication(ServerSock.ServerSocket);
    private static final long serialVersionUID = 1L;
    private int Time = 10;
    private onDiaryData OnDiaryData = null;

    public static ArrayList DiaryList = new ArrayList();

    public void DirtySetting()
    {
        OnDiaryData.onDiaryList(DiaryList);
    }
    public void onDiaryListener(onDiaryData listener)
    {
        this.OnDiaryData = listener;
    }

    public Interface() {
        ServerSocket = ServerSock.ServerSocket;
    }
    //------------------------------Recv--------------------


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

    public void NoticeRegistQeustion(ArrayList DiaryList1) //Type 15
    {
        this.DiaryList = DiaryList1;
    }

    public void NoticeReFlashDiary(ArrayList DiaryList1) //Type 16
    {
        this.DiaryList = DiaryList1;
    }

    public void NoticeInitializeDiary(ArrayList DiaryList1) //Type 17
    {
        this.DiaryList = DiaryList1;
        int a;
        a =100;
    }

    public void NoticeNextYMDiary() //Type 18
    {

    }

    public void NoticeSetCpuleNotice() //Type 19
    {

    }


    //------------------------------Sender-----------------------------------
    public void NameNoticeSender(String Name) throws IOException {

        setPacket("1");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Name);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}

    }

    public void DiaryRegistSender(String Name, String Contents, String Year, String Month, String Date) throws IOException {
        setPacket("2");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Name);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Contents);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Year);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Month);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Date);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        //  setPacket(2,MainDataPacketSender);
    }

    public void DiaryEditSender(String Name, String Contents, String Year, String Month, String Date) throws IOException {
        setPacket("3");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Name);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Contents);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Year);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Month);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Date);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        //  setPacket(2,MainDataPacketSender);
    }

    public void RegistQuestionSender(String Name, String Question, String Year, String Month, String Date) throws IOException {
        setPacket("4");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Name);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Year);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Month);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Date);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Question);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        //  setPacket(2,MainDataPacketSender);
    }

    public void SetCoupleSender(String Name, String CoupleName) throws IOException {
        setPacket("5");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Name);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(CoupleName);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        //  setPacket(2,MainDataPacketSender);
    }

    public void BrokenCoupleSender(String Name) throws IOException {
        setPacket("6");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Name);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        //  setPacket(2,MainDataPacketSender);
    }

    public void DeleteUserInfoSender() throws IOException {
        setPacket("7");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        //  setPacket(2,MainDataPacketSender);
    }

    public void ReFreshDiarySender(String Name, String Year, String Month) throws IOException {
        setPacket("8");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Name);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(""); //Comments
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Year);  //year
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Month); //Month
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(""); //Date
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        //  setPacket(2,MainDataPacketSender);
    }

    public void InitSender(String Name, String Year, String Month) throws IOException {
        setPacket("9");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Name);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(""); //Comments
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Year);  //year
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Month); //Month
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(""); //Date
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        //  setPacket(2,MainDataPacketSender);
    }

    public void NextMYSender(String Name, String Year, String Month) throws IOException {
        setPacket("10");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Name);
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(""); //Comments
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Year);  //year
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(Month); //Month
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket(""); //Date
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        setPacket("");
        try {Thread.sleep(100); }
        catch (InterruptedException e) {e.printStackTrace();}
        //  setPacket(2,MainDataPacketSender);
    }

    public void test() {
        //String a = "경선";
        //byte[] Q = new byte[0];
        //SendeMSG.Send(ClientConnectionAPI.getInstance().ServerSocket, Q);
    }


    public void setPacket(String Data) throws IOException {
        String DataR = Data;
        SendeMSG.TypeSend(ClientConnectionAPI.getInstance().ServerSocket, DataR);


    }

    @Override
    public void onDiaryList(ArrayList<String> Data) {

    }
}