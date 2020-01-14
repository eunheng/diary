package com.example.diary;

import com.example.diary.ServerCommunication.Interface;
import com.example.diary.model.Diary;

import java.io.IOException;
import java.util.ArrayList;

public class DiaryAdapter {
    private int key, year, month, day;
    private String myID, coupleID, tYear, tMonth, tDay;
    private Interface clientInterface = new Interface();

    String[] questions = new String[31];
    String[] diarys = new String[31];
    String[] checkedDiarys = new String[31];

    public DiaryAdapter(int key, String myID, String coupleID,int year, int month, int day) {
        this.key = key;
        this.myID = myID;
        this.coupleID = coupleID;
        this.year = year;
        this.month = month;
        this.day = day;

        tYear = String.valueOf(year);
        tMonth = String.valueOf(month);

        try{
            switch (key){
                case 0: //앱 켰을 때, 일기에서 먼슬리로 넘어 올 때
                    clientInterface.InitSender(myID,tYear,tMonth);
                    break;
                case 1: //일,월 넘기거나 피커로 이동
                    clientInterface.NextMYSender(myID,tYear,tMonth);
                    break;
                case 2: //일력으로 넘어갈 때
                    clientInterface.ReFreshDiarySender(myID,tYear,tMonth);
                    break;
                default:
                    break;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Diary> results;
        Diary diary;


        /**
         ArrayList<String> diaryLists = clientInterface.Result;
         for(int i=0;i<diaryLists.size();i++){
            diary.splitString(diaryLists(i));
            results.add(diary);
         }
         for (Diary m : results) {
         questions[m.getDay() -1] = m.getQuestion();
         diarys[m.getDay() - 1] = m.getText();
         tYear = String.valueOf(m.getYear());
         tMonth = String.valueOf(m.getMonth());
         tDay = String.valueOf(m.getDay());
         if (!diarys[m.getDay() - 1].isEmpty())
         checkedDiarys[m.getDay() - 1] = tYear + ',' + tMonth + ',' + tDay;
         else
         checkedDiarys[m.getDay() - 1] = "NULL";

         }

         **/


/** TODO 서버에서 받아오기
 *
        RealmResults<Diary> results;

        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Diary> query = realm.where(Diary.class);
        results = query
                .equalTo("year", year)
                .equalTo("month", month)
                .findAll();

        for (Diary m : results) {
            questions[m.getDay() -1] = m.getQuestion();
            diarys[m.getDay() - 1] = m.getText();
            tYear = String.valueOf(m.getYear());
            tMonth = String.valueOf(m.getMonth());
            tDay = String.valueOf(m.getDay());
            if (!diarys[m.getDay() - 1].isEmpty())
                checkedDiarys[m.getDay() - 1] = tYear + ',' + tMonth + ',' + tDay;
            else
                checkedDiarys[m.getDay() - 1] = "NULL";

        }
 **/
    }

}
