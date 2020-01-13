package com.example.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingActivity extends Activity implements View.OnClickListener {

    private ListView listView;
    TextView tv_title;
    ImageView backArrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        listView = (ListView) findViewById(R.id.listview);
        backArrow = (ImageView) findViewById(R.id.backArrow);
        tv_title = (TextView)findViewById(R.id.setTitle);

        backArrow.setOnClickListener(this);
        tv_title.setText("설정");

        List<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.setting_item)));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_item = (String) adapterView.getItemAtPosition(i);
                switch (i) {
                    case 0://내정보보기
                        Intent intent1 = new Intent(view.getContext(), UserInfoActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case 1://알람
                        Intent intent2 = new Intent(view.getContext(), AlramActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backArrow:  //뷰 전환
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }

    }
}
