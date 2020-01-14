package com.example.diary.model;

import io.realm.annotations.PrimaryKey;

public class Diary {
    @PrimaryKey
    private int year;
    private int month;
    private int day;
    private String name;
    private String question;
    private String text;

    public Diary() {

    }

    public Diary(String name, int year, int month, int day, String question, String text) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.question = question;
        this.text = text;
    }

    @Override
    public String toString() {
        return "year=" + year +
                ",month=" + month +
                ",day=" + day +
                ",name='" + name +
                ",question='" + question +
                ",text='" + text;
    }

    public void splitString(String s){
        String[] splitUnit = s.split(",");
        for(int i=0;i<splitUnit.length;i++){
            switch (i){
                case 0:
                    splitUnit[i].replace("year=","");
                    setYear(Integer.valueOf(splitUnit[i]));
                    break;
                case 1:
                    splitUnit[i].replace("month=","");
                    setMonth(Integer.valueOf(splitUnit[i]));
                    break;
                case 2:
                    splitUnit[i].replace("day=","");
                    setDay(Integer.valueOf(splitUnit[i]));
                    break;
                case 3:
                    splitUnit[i].replace("name=","");
                    setName(splitUnit[i]);
                    break;
                case 4:
                    splitUnit[i].replace("question=","");
                    setQuestion(splitUnit[i]);
                    break;
                case 5:
                    splitUnit[i].replace("text=","");
                    setText(splitUnit[i]);
                    break;
                default :
                    break;
            }

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public String getYearToStting(){
        return String.valueOf(year);

    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthToStting(){
        return String.valueOf(month);

    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public String getDayToStting(){
        return String.valueOf(day);

    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
