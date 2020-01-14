package com.example.diary.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.diary.MainActivity;
import com.example.diary.R;

public class DiaryWritingService extends Service {
    private int REQUEST_CODE = 1;
    public DiaryWritingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String hour = intent.getStringExtra("hour");
        String minute = intent.getStringExtra("minute");
        String question = intent.getStringExtra("question");

        Intent mMainIntent = new Intent(this, MainActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(this,REQUEST_CODE,mMainIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder mBuilder = new Notification.Builder(this).setSmallIcon(R.drawable.convert)
                .setContentTitle(hour+" : "+minute+" 오늘의 일기를 작성해주세요").setContentIntent(mPendingIntent)
                .setContentText(question).setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(Integer.valueOf(hour+minute),notification);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
