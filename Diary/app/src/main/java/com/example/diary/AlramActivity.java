package com.example.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

public class AlramActivity extends Activity implements View.OnClickListener {

    TextView tv_title;
    ImageView backArrow;
    TimePicker tp_alram;
    Switch sw_alram;

    String [] item;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alram);

        init();

    }

    private void init() {
        backArrow = (ImageView) findViewById(R.id.backArrow);
        tv_title = (TextView)findViewById(R.id.setTitle);
        tp_alram = (TimePicker) findViewById(R.id.tp_alram);
        sw_alram = (Switch)findViewById(R.id.sw_alram);

        item = getResources().getStringArray(R.array.setting_item);

        backArrow.setOnClickListener(this);
        tv_title.setText(item[2]);
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
}