package com.amnesty.panicbutton.spike;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class HardwareTriggerActivity extends Activity {
    public static final String PREFERENCES_NAME = "PANIC_BUTTON_SPIKE_SETTINGS";
    public static final String MOBILE_NUMBER = "MOBILE_NUMBER";
    private SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardware_trigger);

        settings = getSharedPreferences(PREFERENCES_NAME, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        EditText editText = (EditText) findViewById(R.id.mobileNumber);
        editText.setText(settings.getString(MOBILE_NUMBER, getCurrentPhoneNumber()));
    }

    private String getCurrentPhoneNumber() {
        TelephonyManager phoneManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        return phoneManager.getLine1Number();
    }

    public void onSaveClick(View view) {
        EditText editText = (EditText) findViewById(R.id.mobileNumber);
        String mobileNumber = editText.getText().toString();

        SharedPreferences.Editor editor = settings.edit();
        editor.putString(MOBILE_NUMBER, mobileNumber);
        editor.apply();

        displayToast(mobileNumber + " Save successfully");
    }

    private void displayToast(String toastMessage) {
        Toast toast = Toast.makeText(getApplicationContext(), toastMessage, LENGTH_SHORT);
        toast.show();
    }
}