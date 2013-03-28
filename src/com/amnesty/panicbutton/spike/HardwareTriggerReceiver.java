package com.amnesty.panicbutton.spike;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import static android.content.Intent.ACTION_MEDIA_BUTTON;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

public class HardwareTriggerReceiver extends BroadcastReceiver {
    public static final String TAG = HardwareTriggerReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.v(TAG, "Power button OFF");
            Toast.makeText(context, "Power button clicked OFF", LENGTH_LONG).show();
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.v(TAG, "Power button ON");
            Toast.makeText(context, "Power button clicked ON", LENGTH_SHORT).show();
        } else if (intent.getAction().equals(ACTION_MEDIA_BUTTON)) {
            Log.v(TAG, "Volume button pressed");
            KeyEvent ke = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
            if (ke.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
                Log.v(TAG, "Volume DOWN pressed");
            }
            Toast.makeText(context, "Volume button pressed", LENGTH_SHORT).show();
        }
    }
}