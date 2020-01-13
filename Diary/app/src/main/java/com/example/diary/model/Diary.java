package com.example.diary.model;

import androidx.annotation.NonNull;

import io.realm.annotations.PrimaryKey;

public class Diary {
    @PrimaryKey
    private int year;
    private int month;
    private int day;
    private String question;
    private String text;

    public Diary() {

    }

    public Diary(int year,int month,int day, String question, String text) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.question = question;
        this.text = text;
    }


    @NonNull
    @Override
    public String toString() {
        return "Diary{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", question='" + question + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
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
