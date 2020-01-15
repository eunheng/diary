package com.example.diary;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class AlarmActivity extends Activity implements View.OnClickListener, TimePicker.OnTimeChangedListener, CompoundButton.OnCheckedChangeListener {

    TextView tv_title;
    ImageView backArrow;
    TimePicker tp_alram;
    Switch sw_alram;

    String [] item;
    //TODO 임의로 아무 시간이나 설정해두기
    private int tpHour,tpMinute;
    private Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        init();
        setNotification();

    }

    private void init() {
        backArrow = (ImageView) findViewById(R.id.backArrow);
        tv_title = (TextView)findViewById(R.id.setTitle);
        tp_alram = (TimePicker) findViewById(R.id.tp_alram);
        sw_alram = (Switch)findViewById(R.id.sw_alram);

        item = getResources().getStringArray(R.array.setting_item);

        backArrow.setOnClickListener(this);
        tp_alram.setOnTimeChangedListener(this);
        sw_alram.setOnCheckedChangeListener(this);
        tv_title.setText(item[1]);
    }

    private void setNotification() {
        Intent mAlarmIntent = new Intent("com.example.diary.ALARM_START");
        mAlarmIntent.putExtra("hour",String.valueOf(tpHour));
        mAlarmIntent.putExtra("minute",String.valueOf(tpMinute));
        //TODO 질문 리스트에서 랜덤으로 골라서 질문하기, 데일리 뷰에 질문 띄우기
        mAlarmIntent.putExtra("question","오늘의 일기를 작성해 주세요");
        int id = Integer.valueOf(String.valueOf(tpHour)+String.valueOf(tpMinute));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,id,mAlarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //alarmManager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backArrow:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }

    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int hh, int mm) {
        tpHour = hh;
        tpMinute = mm;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            //long time = c
            setNotification();
        }
    }
}