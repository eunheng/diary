package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
    private ImageButton btn_convert;
    private MaterialCalendarView calendarView;


    //val
    final CalendarDay instance = CalendarDay.today();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        checkedDay();


    }

    private void init() {
        btn_convert = (ImageButton) findViewById(R.id.btn_convertView);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        btn_convert.setOnClickListener(this);       //뷰 전환 버튼 클릭 이벤트
        calendarView.setOnDateChangedListener(this);    //날짜 클릭 이벤트
        calendarView.setOnMonthChangedListener(this);   //달 변화 이벤트
        calendarView.setSelectedDate(instance);  //오늘 날짜를 기본으로 보여줌
        calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
        );

    }

    private void checkedDay(){
        //TODO 일기가 저장된 날짜 받아오기
        String[] result = {"2020,01,18","2020,01,22","2020,01,04","2020,01,29"};
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
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);
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
            case R.id.btn_convertView:
                Intent intent = new Intent(this, DailyViewActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

    }
}
