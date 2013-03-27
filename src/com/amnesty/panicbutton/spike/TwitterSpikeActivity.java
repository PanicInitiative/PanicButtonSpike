package com.amnesty.panicbutton.spike;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class TwitterSpikeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_spike);
        // suppress USSD
        /* Context applicationContext = getApplicationContext();
        Intent intent = new Intent(applicationContext, USSDSuppressService.class);
        applicationContext.startService(intent); */
    }

    public void onTweetClick(View view) {
        EditText editText = (EditText) findViewById(R.id.tweetBox);
        String tweet = editText.getText().toString();
        // tweet = "@alert_anyone " + tweet;

        SmsManager defaultSMSManager = SmsManager.getDefault();
        defaultSMSManager.sendTextMessage("53000", null, tweet, null, null);

        displayToast("Tweet sent successfully");

        // Multipart
        // How to confirm a message has been sent successfully
        // Tweet with @ and #
    }

    private void displayToast(String toastMessage) {
        Toast toast = Toast.makeText(getApplicationContext(), toastMessage, LENGTH_SHORT);
        toast.show();
    }
}