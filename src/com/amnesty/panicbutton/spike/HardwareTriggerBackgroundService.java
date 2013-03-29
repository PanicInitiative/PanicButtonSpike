package com.amnesty.panicbutton.spike;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class HardwareTriggerBackgroundService extends Service {
    public static final String TAG = HardwareTriggerBackgroundService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "CREATING HardwareTriggerBackgroundService...");

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");

        BroadcastReceiver mReceiver = new HardwareTriggerReceiver(getApplicationContext());
        registerReceiver(mReceiver, filter);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
