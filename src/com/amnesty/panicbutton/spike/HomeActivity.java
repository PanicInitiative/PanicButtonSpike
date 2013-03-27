package com.amnesty.panicbutton.spike;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class HomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void showTwitterSpike(View view) {
        displayToast("TwitterSpike Will be implemented!");
    }

    public void showLocationSpike(View view) {
        displayToast("LocationSpike Will be implemented!");
    }

    public void showUSSDSpike(View view) {
        displayToast("USSDSpike Will be implemented!");
    }

    public void showHardwareTriggerSpike(View view) {
        displayToast("HardwareTriggerSpike Will be implemented!");
    }

    private void displayToast(String toastMessage) {
        Toast toast = Toast.makeText(getApplicationContext(), toastMessage, LENGTH_SHORT);
        toast.show();
    }
}
