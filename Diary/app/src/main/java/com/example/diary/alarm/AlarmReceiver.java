package com.example.diary.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mServiceIntent = new Intent(context,DiaryWritingService.class);
        mServiceIntent.putExtra("hour",intent.getStringExtra("hour"));
        mServiceIntent.putExtra("minute",intent.getStringExtra("minute"));
        mServiceIntent.putExtra("question",intent.getStringExtra("question"));

        context.startService(mServiceIntent);
    }
}
