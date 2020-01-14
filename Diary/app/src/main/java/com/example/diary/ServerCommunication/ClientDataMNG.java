package com.example.diary.ServerCommunication;

import java.util.ArrayList;

public class ClientDataMNG {
    private DataPacket.Header HeaderPacket = new DataPacket.Header();
    private DataPacket.Pakcet MainPacket = new DataPacket.Pakcet();
    private Interface ClientInterface = new Interface();
    private ArrayList<String> DiaryData = new ArrayList();

    public ClientDataMNG(DataPacket.Header HeaderPacket1,  DataPacket.Pakcet MainPacket1)
    {
        HeaderPacket = HeaderPacket1;
        MainPacket = MainPacket1;
        DataAnalysis(MainPacket1.Type);
        DiaryData=MainPacket.Diary;
    }

    private void DataAnalysis(String Type)
    {
        switch (Type)
        {
            case "11" : ClientInterface.NoticeDiaryEditSuccess();
                break;
            case "12" : ClientInterface.NoticeDiaryRegistSuccess();
                break;
            case "13" : ClientInterface.NoticeBrokenCouple();
                break;
            case "14" : ClientInterface.NoticeQuestion();
                break;
            case "15" : ClientInterface.NoticeRegistQeustion(MainPacket.Diary);
                break;
            case "16" : ClientInterface.NoticeReFlashDiary(MainPacket.Diary);
            break;
            case "17" : ClientInterface.NoticeInitializeDiary(MainPacket.Diary);
                break;
            case "18" : ClientInterface.NoticeNextYMDiary();
                break;
            case "19" : ClientInterface.NoticeSetCpuleNotice();
                break;
        }
    }
}
