package com.example.diary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diary.ServerCommunication.ClientCommunication;
import com.example.diary.ServerCommunication.ClientConnectionAPI;
import com.example.diary.ServerCommunication.Interface;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {
    private EditText et_userID,et_coupleID;
    private Button btn_start,btn_solo,btn_couple;
    private LinearLayout coupleLayout;
    private Context mContext = LoginActivity.this;
    private ClientConnectionAPI Con = ClientConnectionAPI.getInstance();
    private Interface ClientInterface = new Interface();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Runnable Connect = Con;
        Thread Connection = new Thread(Connect);
        Connection.start();
        init();

    }

    private void init() {
        et_userID = (EditText) findViewById(R.id.et_userID);
        et_coupleID = (EditText) findViewById(R.id.et_coupleID);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_solo = (Button) findViewById(R.id.btn_solo);
        btn_couple = (Button) findViewById(R.id.btn_couple);
        coupleLayout = (LinearLayout) findViewById(R.id.coupleLayout);

        btn_start.setOnClickListener(this);
        btn_solo.setOnClickListener(this);
        btn_couple.setOnClickListener(this);
        //TODO 자동로그인
    }

    private boolean nullCheck(String string){

        if(string.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_start:
                ClientCommunication RecvThread = new ClientCommunication(Con.ServerSocket);
                Runnable Recv = RecvThread;
                Thread RecvStart = new Thread(Recv);
                RecvStart.start();
                String userID = et_userID.getText().toString();
                try {
                    ClientInterface.NameNoticeSender(userID);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (nullCheck(userID)){
                    Toast.makeText(mContext, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    //TODO 서버로 이름 보내고 결과 받아오기
                    //if 유저 이름을 체크해서 커플이 있다면 바로 먼슬리로
                    //else 없다면 커플 맺기 뜨기
                    {
                        et_userID.setFocusableInTouchMode(false);
                        et_userID.setFocusable(false);
                        et_userID.setClickable(false);
                        btn_start.setVisibility(View.GONE);
                        coupleLayout.setVisibility(View.VISIBLE);
                    }
                }

                break;
            case R.id.btn_solo:
                //커플 맺지 않고 바로 먼슬리로
                Intent intent1 = new Intent(mContext, MainActivity.class);
                //TODO 유저 네임 넘겨주기?
                startActivity(intent1);
                break;
            case R.id.btn_couple:
                String coupleID = et_userID.getText().toString();
                if (nullCheck(coupleID)){
                    Toast.makeText(mContext, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    //TODO 서버로 커플 이름 보내기, 먼슬리로 이동
                    Intent intent2 = new Intent(mContext, MainActivity.class);
                    //TODO 유저 네임 넘겨주기?
                    startActivity(intent2);
                }
                break;
            default:
                break;
        }


    }
}
