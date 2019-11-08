package com.mrhi.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MyService myService;//참조변수[4byte: null]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickStart(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        bindService(intent, conn, 0);
    }

    public void clickStop(View view) {

        unbindService(conn);

        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void clickPause(View view) {
        if (myService != null) myService.mp.pause();
    }


    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.ServiceBinder binder = (MyService.ServiceBinder) iBinder;

            myService = binder.aaaa();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
