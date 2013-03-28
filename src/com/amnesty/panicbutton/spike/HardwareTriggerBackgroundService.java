package com.amnesty.panicbutton.spike;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

public class HardwareTriggerBackgroundService extends Service {
    public static final String TAG = HardwareTriggerBackgroundService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "CREATING HardwareTriggerBackgroundService...");

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_MEDIA_BUTTON);
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");

        BroadcastReceiver mReceiver = new HardwareTriggerReceiver();
        registerReceiver(mReceiver, filter);

        AudioManager manager = (AudioManager) getSystemService(AUDIO_SERVICE);
        manager.registerMediaButtonEventReceiver(new ComponentName(this, HardwareTriggerReceiver.class));
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
