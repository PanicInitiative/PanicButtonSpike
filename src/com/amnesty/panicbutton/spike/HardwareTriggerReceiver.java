package com.amnesty.panicbutton.spike;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HardwareTriggerReceiver extends BroadcastReceiver {
    public static final String TAG = HardwareTriggerReceiver.class.getSimpleName();
    private TriggerEvents triggers = new TriggerEvents();
    private Vibrator vibrator;
    private Context context;

    public HardwareTriggerReceiver(Context context) {
        this.context = context;
        this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_OFF) || action.equals(Intent.ACTION_SCREEN_ON)) {
            triggers.add();
        } else {
            int volume = (Integer) intent.getExtras().get("android.media.EXTRA_VOLUME_STREAM_VALUE");
            Log.v(TAG, "Volume button pressed : " + volume);
        }
    }

    private class TriggerEvents {
        private List<Long> timestamps = new ArrayList<Long>();

        public boolean inLimit() {
            return timestamps.size() < 5;
        }

        public Long first() {
            return (timestamps.size() > 0) ? timestamps.get(0) : null;
        }

        public void clear() {
            Log.v(TAG, "CLEAR...");
            timestamps.clear();
        }

        public void add() {
            long current = System.currentTimeMillis();
            long first = (first() == null) ? current : first();
            Log.v(TAG, "ADD : " + current);

            if((current - first) < 7000) {
                if(inLimit()) {
                    timestamps.add(current);
                } else {
                    alert();
                    clear();
                }
            } else {
                clear();
                timestamps.add(current);
            }
        }

        private void alert() {
            SharedPreferences sharedPreferences = context.getSharedPreferences(HardwareTriggerActivity.PREFERENCES_NAME, 0);
            String destinationAddress = sharedPreferences.getString(HardwareTriggerActivity.MOBILE_NUMBER, getCurrentPhoneNumber());

            Log.v(TAG, "TRIGGER ALERT : " + destinationAddress);
            vibrator.vibrate(500);

            SmsManager defaultSMSManager = SmsManager.getDefault();
            String message = "Help, I am in trouble. Location : " ;
            defaultSMSManager.sendTextMessage(destinationAddress, null, message, null, null);
        }

        private String getCurrentPhoneNumber() {
            TelephonyManager phoneManager = (TelephonyManager)context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            return phoneManager.getLine1Number();
        }

    }

}