package com.mrhi.test;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {

    MediaPlayer mp;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mp==null){
            mp= MediaPlayer.create(this, R.raw.kalimba);
            mp.setLooping(false);
            mp.setVolume(1,1);
            mp.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if(mp!=null){
            mp.stop();
            mp.release();
            mp= null;
        }
        super.onDestroy();

    }

    //bindService()를 할 때 실행
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }

    // 외교관 클래스
    class ServiceBinder extends Binder {
        MyService aaaa(){
            return MyService.this;
        }
    }
}
