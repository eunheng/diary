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
    private Interface ClientInterface=new Interface();
    Intent intent = new Intent(mContext, MainActivity.class);

    private String myID, coupleID;

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
                myID = et_userID.getText().toString();
                ClientCommunication RecvThread = new ClientCommunication(Con.ServerSocket);
                Runnable Recv = RecvThread;
                Thread RecvStart = new Thread(Recv);
                RecvStart.start();
                if (nullCheck(myID)){
                    Toast.makeText(mContext, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        ClientInterface.NameNoticeSender(myID);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    et_userID.setFocusableInTouchMode(false);
                    et_userID.setFocusable(false);
                    et_userID.setClickable(false);
                    btn_start.setVisibility(View.GONE);
                    coupleLayout.setVisibility(View.VISIBLE);
                    intent.putExtra("myID",myID);
                }
                break;
            case R.id.btn_solo: //커플 맺지 않고 바로 먼슬리로
                startActivity(intent);
                finish();
                break;
            case R.id.btn_couple:
                coupleID = et_userID.getText().toString();
                if (nullCheck(coupleID)){
                    Toast.makeText(mContext, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        ClientInterface.SetCoupleSender(myID,coupleID);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("coupleID",coupleID);
                    startActivity(intent);
                    finish();
                }
                break;
            default:
                break;
        }


    }
}
