package com.radio.radioagakiza;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class RadioService extends Service {
    private IBinder mbinder=new Binder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }
    public class Mybinder extends Binder{
        RadioService getservice(){
            return  RadioService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }
}
