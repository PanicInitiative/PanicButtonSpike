package com.amnesty.panicbutton.spike;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class HomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Context context = getApplicationContext();
        Intent service = new Intent(context, HardwareTriggerBackgroundService.class);
        context.startService(service);
    }

    public void showTwitterSpike(View view) {
        startActivity(new Intent(this, TwitterSpikeActivity.class));
    }

    public void showLocationSpike(View view) {
        displayToast("LocationSpike Will be implemented!");
    }

    public void showUSSDSpike(View view) {
        displayToast("USSDSpike Will be implemented!");
    }

    private void displayToast(String toastMessage) {
        Toast toast = Toast.makeText(getApplicationContext(), toastMessage, LENGTH_SHORT);
        toast.show();
    }
}
