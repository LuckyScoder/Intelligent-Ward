package com.example.lenovo.controller.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;

public class AutoUpdateSecond extends Service {
    private Intent local_intent = new Intent("com.example.lenovo.controller.BROADCAST2");
    public AutoUpdateSecond() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(local_intent);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int time_temp = 30*1000; //30s更新1次
        long All_time = SystemClock.elapsedRealtime() + time_temp;
        Intent i = new Intent(this,AutoUpdateSecond.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,All_time,pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
