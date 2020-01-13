package com.example.diary.ServerCommunication;

public class ClientDataMNG {
    private DataPacket.Header HeaderPacket = new DataPacket.Header();
    private DataPacket.Pakcet MainPacket = new DataPacket.Pakcet();
    private Interface ClientInterface = new Interface();
    public ClientDataMNG(DataPacket.Header HeaderPacket1,  DataPacket.Pakcet MainPacket1)
    {
        HeaderPacket = HeaderPacket1;
        MainPacket = MainPacket1;
        DataAnalysis(MainPacket1.Type);
    }

    private void DataAnalysis(int Type)
    {
        switch (Type)
        {
            case 11 : ClientInterface.NoticeDiaryEditSuccess();
                break;
            case 12 : ClientInterface.NoticeDiaryRegistSuccess();
                break;
            case 13 : ClientInterface.NoticeBrokenCouple();
                break;
            case 14 : ClientInterface.NoticeQuestion();
                break;
            case 15 : ClientInterface.NoticeRegistQeustion();
                break;
            case 16 : ClientInterface.NoticeReFlashDiary();
                break;
            case 17 : ClientInterface.NoticeInitializeDiary();
                break;
            case 18 : ClientInterface.NoticeNextYMDiary();
                break;
            case 19 : ClientInterface.NoticeSetCpuleNotice();
                break;
        }
    }
}
