package com.example.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class DailyViewActivity extends Activity implements View.OnClickListener {

    //widget
    private ImageButton btn_convert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyview);
        init();
    }

    private void init() {
        btn_convert = (ImageButton) findViewById(R.id.btn_convertView);
        btn_convert.setOnClickListener(this);       //뷰 전환 버튼 클릭 이벤트
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_convertView:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
