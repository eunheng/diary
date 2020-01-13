package com.example.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoActivity extends Activity implements View.OnClickListener {

    TextView tv_title,tv_myID,tv_coupleID;
    ImageView backArrow,iv_myColor,iv_coupleColor;
    ImageButton btn_disconnect;

    String [] item;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        init();
        //TODO 유저 정보 받아오기,커플 있는지 없는지 확인 후 커플정보 보여주기

    }

    private void init() {
        backArrow = (ImageView) findViewById(R.id.backArrow);
        iv_myColor = (ImageView) findViewById(R.id.iv_myColor);
        iv_coupleColor = (ImageView) findViewById(R.id.iv_coupleColor);
        tv_title = (TextView)findViewById(R.id.setTitle);
        tv_myID = (TextView)findViewById(R.id.tv_myID);
        tv_coupleID = (TextView)findViewById(R.id.tv_coupleID);
        btn_disconnect = (ImageButton)findViewById(R.id.btn_disconnect);

        item = getResources().getStringArray(R.array.setting_item);

        backArrow.setOnClickListener(this);
        btn_disconnect.setOnClickListener(this);
        tv_title.setText(item[1]);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backArrow:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_disconnect:
                break;
            default:
                break;
        }

    }
}
