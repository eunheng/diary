package com.example.diary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.diary.ServerCommunication.Interface;
import com.example.diary.model.Diary;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.IOException;
import java.util.Calendar;

public class DailyViewActivity extends Activity implements View.OnClickListener, View.OnTouchListener{

    //widget
    private ImageButton btn_convert;
    private GestureDetector gestureScanner = null;
    private Button btn_datemove, btn_done, btn_edit;
    private DatePicker dp_fixedPicker;
    private TextView tv_day,tv_myID,tv_coupleID,tv_diary;
    private EditText et_diary;
    private Spinner sp_question;
    private LinearLayout diaryLayout;
    private LinearLayout coupleLayout;

    private DiaryAdapter diaryAdapter;
    private Diary mDiary = new Diary();
    private Interface clientInterface=new Interface();

    //val
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private int year,month,day; //현재 보여주고 있는 일기의 날짜
    private CalendarDay mCalendarDay;
    private int dpYear, dpMonth, dpDay;
    private Calendar calendar;
    private String[] myQuestions, myDiarys,coupleQuestions,coupleDiarys;
    private String myQuestion, myDiary,coupleQuestion,coupleDiary;
    private ArrayAdapter<String> questionAdpater;
    private String[] questionList;
    private int btnDone=8,btnEdit=0;
    private String myID, coupleID=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyview);
        init();
        showDiary();
        checkDatePicker();
        //swipe();
    }

    private void init() {
        btn_convert = (ImageButton) findViewById(R.id.btn_convertView);
        btn_datemove = (Button) findViewById(R.id.btn_datemove);
        btn_done = (Button) findViewById(R.id.btn_done);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        dp_fixedPicker = (DatePicker) findViewById(R.id.dp_fixedPicker);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_myID = (TextView) findViewById(R.id.tv_myID);
        tv_coupleID = (TextView) findViewById(R.id.tv_coupleID);
        tv_diary = (TextView) findViewById(R.id.tv_diary);
        et_diary = (EditText) findViewById(R.id.et_diary);
        sp_question = (Spinner) findViewById(R.id.sp_question);
        coupleLayout = (LinearLayout) findViewById(R.id.coupleLayout);
        diaryLayout = (LinearLayout) findViewById(R.id.diaryLayout);
        mCalendarDay = CalendarDay.from(Calendar.getInstance());
        btn_convert.setOnClickListener(this);
        btn_datemove.setOnClickListener(this);
        btn_done.setOnClickListener(this);
        btn_edit.setOnClickListener(this);
        diaryLayout.setOnTouchListener(this);
        questionList = getResources().getStringArray(R.array.defalt_questrons);
        questionAdpater = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,questionList){
            @Override
            public int getCount() {
                return super.getCount()-1;
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }
        };
        sp_question.setAdapter(questionAdpater);
        sp_question.setSelection(0);
        sp_question.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                myQuestion = questionList[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public static Intent intentToDaily(Context context, String myID, String coupleID, int year, int month, int day, String[] myQuestions , String[] myDiarys, String[] coupleQuestions , String[] coupleDiarys) {
        Intent intent = new Intent(context, DailyViewActivity.class);
        intent.putExtra("myID",myID);
        intent.putExtra("coupleID",coupleID);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("myQuestions", myQuestions);
        intent.putExtra("myDiarys",myDiarys);
        intent.putExtra("coupleQuestions", coupleQuestions);
        intent.putExtra("coupleDiarys",coupleDiarys);
        return intent;
    }

    private void intentToMonthly() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("myID",myID);
        intent.putExtra("coupleID",coupleID);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("myQuestions", myQuestions);
        intent.putExtra("myDiarys",myDiarys);
        intent.putExtra("coupleQuestions", coupleQuestions);
        intent.putExtra("coupleDiarys",coupleDiarys);
        startActivity(intent);
        finish();
    }

    private void showDiary() {
        Intent intent = getIntent();
        if (intent != null) {
            calendar = Calendar.getInstance();
            year = intent.getIntExtra("year", calendar.get(Calendar.YEAR));
            month = intent.getIntExtra("month", calendar.get(Calendar.MONTH));
            day = intent.getIntExtra("day", calendar.get(Calendar.DATE));
            dpYear = year;
            dpMonth= month;
            dpDay = day;
            calendar.set(year,month,day);
            dp_fixedPicker.updateDate(year,month,year);
            tv_day.setText(String.valueOf(year)+"년 "+String.valueOf(month+1)+"월 "+String.valueOf(day)+"일");
            myID = intent.getStringExtra("myID");
            tv_myID.setText(myID);
            myQuestions = intent.getStringArrayExtra("myQuestions");
            myDiarys = intent.getStringArrayExtra("myDiarys");
            if (!TextUtils.isEmpty(myDiarys[day-1]))
            {
                et_diary.setText(myDiarys[day-1]);
                for (int i=0; i<questionList.length; i++)
                    if (questionList[i].equals(myQuestions[day-1]))
                        sp_question.setSelection(i);
            }
            coupleID = intent.getStringExtra("coupleID");
            if (coupleID!=null){
                coupleLayout.setVisibility(View.VISIBLE);
                coupleQuestions = intent.getStringArrayExtra("coupleQuestions");
                coupleDiarys = intent.getStringArrayExtra("coupleDiarys");
                if (!TextUtils.isEmpty(coupleDiarys[day-1]))
                    tv_diary.setText(coupleQuestions[day-1]+"\n"+coupleDiarys[day-1]);
            }else{
                coupleLayout.setVisibility(View.GONE);
            }

        }

    }

    //상단 스피너 날짜 이동값 저장(초기값 : 먼슬리에서 받아온 날짜)
    private void checkDatePicker(){
        dp_fixedPicker.init(year, month,day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int yy, int mm, int dd) {
                dpYear = yy;
                dpMonth = mm;
                dpDay = dd;
            }
        });
    }

    //EditText 영역에 대한 swipe gesture 인식
    private void swipe(){

    }

    private void setCalendar(Calendar calendar){
        mCalendarDay.from(calendar);
        year = mCalendarDay.getYear();
        month = mCalendarDay.getMonth();
        day = mCalendarDay.getDay();
        updateDiary(year,month,day);
    }

    private void updateDiary(int year, int month, int day){
        int key = 1;
        tv_day.setText(String.valueOf(year)+"년 "+String.valueOf(month+1)+"월 "+String.valueOf(day)+"일");
        if (!((year==this.year)||(month==this.month)))
        {
            diaryAdapter = new DiaryAdapter(key,myID,coupleID,year,month,day);
            myQuestions = diaryAdapter.myQuestions;
            myDiarys = diaryAdapter.myDiarys;
            myQuestion = diaryAdapter.myQuestions[day-1];
            myDiary = diaryAdapter.myDiarys[day-1];
            if(coupleID!=null){
                coupleQuestions = diaryAdapter.coupleQuestions;
                coupleDiarys = diaryAdapter.coupleDiarys;
                coupleQuestion = diaryAdapter.coupleQuestions[day-1];
                coupleDiary = diaryAdapter.coupleDiarys[day-1];
            }
        }else {
            myQuestion = myQuestions[day-1];
            myDiary = myDiarys[day-1];
            if(coupleID!=null){
                coupleQuestion = coupleQuestions[day-1];
                coupleDiary = coupleDiarys[day-1];
            }

        }

        if (!TextUtils.isEmpty(myDiary))
        {
            et_diary.setText(myDiary);
            for (int i=0; i<questionList.length; i++)
                if (questionList[i].equals(myQuestions[day-1]))
                    sp_question.setSelection(i);
        }

        if (coupleID!=null) {
            if (!TextUtils.isEmpty(coupleDiarys[day-1]))
                tv_diary.setText(coupleQuestions[day-1]+"\n"+coupleDiarys[day-1]);
        }

    }

    private void setDiaryData(){
        mDiary.setName(myID);
        mDiary.setYear(year);
        mDiary.setMonth(month);
        mDiary.setDay(day);
        mDiary.setQuestion(myQuestion);
        mDiary.setText(et_diary.getText().toString());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_convertView:  //뷰 전환
                intentToMonthly();
                break;
            case R.id.btn_datemove: //피커에서 받아온 값으로 일기 업데이트
                calendar.set(dpYear,dpMonth,dpDay);
                updateDiary(dpYear,dpMonth,dpDay);
                break;
            case R.id.btn_done: //메모 저장
                try {
                    clientInterface.DiaryRegistSender(mDiary.getName(),mDiary.getText(),mDiary.getYearToStting()
                            ,mDiary.getMonthToStting(),mDiary.getDayToStting());
                    clientInterface.RegistQuestionSender(mDiary.getName(),mDiary.getQuestion(),mDiary.getYearToStting()
                            ,mDiary.getMonthToStting(),mDiary.getDayToStting());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /** TODO 저장버튼 비활성화, 수정버튼 활성화
                //sp_question.setEnabled(false);
                btn_done.setEnabled(false);
                btn_done.setTextColor(getResources().getColor(R.color.white));
                btn_edit.setEnabled(true);
                btn_edit.setTextColor(getResources().getColor(R.color.darkGray));**/
                break;
            case R.id.btn_edit:
                try {
                    clientInterface.DiaryEditSender(mDiary.getName(),mDiary.getText(),mDiary.getYearToStting()
                            ,mDiary.getMonthToStting(),mDiary.getDayToStting());
                    /** TODO 서버 질문 수정 보내기 **/
                    //clientInterface.RegistQuestionSender(mDiary.getName(),mDiary.getQuestion(),mDiary.getYearToStting()
                    //        ,mDiary.getMonthToStting(),mDiary.getDayToStting());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /** TODO 저장버튼 활성화, 수정버튼 비활성화
                //sp_question.setEnabled(true);
                btn_edit.setEnabled(false);
                btn_edit.setTextColor(getResources().getColor(R.color.white));
                btn_done.setEnabled(true);
                btn_done.setTextColor(getResources().getColor(R.color.darkGray));**/
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //swipe();
        gestureScanner = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                        return false;

                    // right to left swipe(next)
                    if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //다음내용 갱신하기
                        calendar.add(Calendar.DATE,1);
                        //setCalendar(calendar);

                    }
                    // left to right swipe(prev)
                    else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //이전내용 갱신하기
                        calendar.add(Calendar.DATE,-1);
                        //setCalendar(calendar);
                    }
                } catch (Exception e) {

                }
                return true;
            }

            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            public void onShowPress(MotionEvent motionEvent) {

            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            public void onLongPress(MotionEvent motionEvent) {

            }
        });
        gestureScanner.onTouchEvent(motionEvent);
        setCalendar(calendar);
        //et_diary.setClickable(true);
        return true;
    }
}
