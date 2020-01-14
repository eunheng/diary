package com.example.diary;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diary.decorator.EventDecorator;
import com.example.diary.decorator.SaturdayDecorator;
import com.example.diary.decorator.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnDateSelectedListener, OnMonthChangedListener {

    //widget
    private ImageButton btn_convert,btn_setting;
    private DatePicker dp_fixedPicker;
    private Button btn_datemove;

    private MaterialCalendarView calendarView;
    private DiaryAdapter diaryAdapter;


    //val
    final CalendarDay todayDate = CalendarDay.today();
    private int todayYear = todayDate.getYear(), todayMonth = todayDate.getMonth(), todayDay = todayDate.getDay();    //오늘 날짜
    private CalendarDay mCalendarDay;
    private int dpYear=todayYear,dpMonth=todayMonth,dpDay=todayDay;
    private String myID, coupleID;
    private int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        login();
        dailyToMonthly(); //처음 뷰 띄울 때 오늘을 기준으로 일기있는 날 체크, 인텐트 받아오면 일기 보던 날의 달로 체크
        checkDatePicker();
    }

    private void init() {
        btn_convert = (ImageButton) findViewById(R.id.btn_convertView);
        btn_setting = (ImageButton) findViewById(R.id.btn_setting);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        dp_fixedPicker = (DatePicker) findViewById(R.id.dp_fixedPicker);
        btn_datemove = (Button) findViewById(R.id.btn_datemove);
        btn_convert.setOnClickListener(this);       //뷰 전환 버튼 클릭 이벤트
        btn_setting.setOnClickListener(this);
        btn_datemove.setOnClickListener(this);
        calendarView.setOnDateChangedListener(this);    //날짜 클릭 이벤트
        calendarView.setOnMonthChangedListener(this);   //달 변화 이벤트
        calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
        );

    }

    private void login(){
        Intent intent = getIntent();
        if(intent != null){
            myID = intent.getStringExtra("myID");
            coupleID = intent.getStringExtra("coupleID");
            if (coupleID.isEmpty()){
                //커플 아이디 없을 경우
                coupleID = "Null";
            }
        }
    }

    private void dailyToMonthly(){//앱이 실행 됐을 때, 데일리 뷰로 갔다 왔을 때
        key = 0;
        Intent intent = getIntent();
        if(intent != null){
            dpYear = intent.getIntExtra("year", todayYear);
            dpMonth = intent.getIntExtra("month", todayMonth);
            dpDay = intent.getIntExtra("day", todayDay);
        }
        mCalendarDay = CalendarDay.from(dpYear,dpMonth,dpDay);
        calendarView.setSelectedDate(mCalendarDay);
        calendarView.setCurrentDate(mCalendarDay);
        checkedDay(key,dpYear,dpMonth,dpDay);
    }

    //상단 스피너 날짜 이동값 저장
    private void checkDatePicker(){
        dp_fixedPicker.init(dpYear, dpMonth, dpDay , new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int yy, int mm, int dd) {
                dpYear = yy;
                dpMonth = mm;
                dpDay = dd;
            }
        });
    }

    private void moveDiary(int year, int month, int day){
        key=2;
        diaryAdapter = new DiaryAdapter(key,myID,coupleID,year,month,day);
        startActivity(DailyViewActivity.intentToDaily(this,myID,coupleID,year,month,day,diaryAdapter.myQuestions,diaryAdapter.myDiarys,diaryAdapter.coupleQuestions,diaryAdapter.coupleDdiarys));
        finish();
    }

    //일기 저장된 날 체크
    private void checkedDay(int key, int year, int month, int day){
        diaryAdapter = new DiaryAdapter(key,myID,coupleID,year,month,day);
        String[] result = {"2020,01,18","2020,01,22","2020,01,04","2020,01,29"};
        //new ApiSimulator(diaryAdapter.checkedDiarys).executeOnExecutor(Executors.newSingleThreadExecutor());
        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

    }

    //달력에 dot 표시
    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0 ; i < Time_Result.length ; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                if(!Time_Result[i].equals("NULL")){
                    String[] time = Time_Result[i].split(",");
                    int year = Integer.parseInt(time[0]);
                    int month = Integer.parseInt(time[1]);
                    int dayy = Integer.parseInt(time[2]);
                    dates.add(day);
                    calendar.set(year,month-1,dayy);
                }
            }

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            calendarView.addDecorator(new EventDecorator(getResources().getColor(R.color.colorAccent), calendarDays,MainActivity.this));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_convertView:  //무조건 오늘날짜 일기로
                moveDiary(todayYear,todayMonth,todayDay);
                break;
            case R.id.btn_datemove: //피커에서 받아온 값으로 캘린더 이동, 일기있는 날 체크
                key=1;
                mCalendarDay = CalendarDay.from(dpYear,dpMonth,dpDay);
                calendarView.setCurrentDate(mCalendarDay);
                calendarView.setSelectedDate(mCalendarDay);
                checkedDay(key,dpYear,dpMonth,dpDay);
                break;
            case R.id.btn_setting: //피커에서 받아온 값으로 일기 업데이트
                Intent intent = new Intent(this, SettingActivity.class);
                //TODO 유저네임? intent.putExtra("year", year);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        //날짜 클릭 이벤트(캘린더에서 선택된 날짜 데일리뷰로 이동)
        moveDiary(date.getYear(),date.getMonth(),date.getDay());
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //달 변화 이벤트(일기있는 날 체크)
        key=1;
        checkedDay(key,date.getYear(),date.getMonth(),date.getDay());
    }
}
