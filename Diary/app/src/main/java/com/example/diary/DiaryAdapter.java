package com.example.diary;

public class DiaryAdapter {
    private int year, month, day;
    private String tYear, tMonth, tDay;

    String[] questions = new String[31];
    String[] diarys = new String[31];
    String[] checkedDiarys = new String[31];

    public DiaryAdapter(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

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
