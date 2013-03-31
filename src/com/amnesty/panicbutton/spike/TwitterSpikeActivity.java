package com.amnesty.panicbutton.spike;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class TwitterSpikeActivity extends Activity {

    private static final String TWITTER_SHORT_CODE = "TWITTER_SHORT_CODE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_spike);

        EditText shortCodeText = getShortCodeText();
        shortCodeText.setText(getTwitterShortCode());
    }

    private String getTwitterShortCode() {
        SharedPreferences sharedPreferences = getSharedPreferences(HardwareTriggerActivity.PREFERENCES_NAME, 0);
        return sharedPreferences.getString(TWITTER_SHORT_CODE, "");
    }

    private void saveTwitterShortCode(String code) {
        SharedPreferences sharedPreferences = getSharedPreferences(HardwareTriggerActivity.PREFERENCES_NAME, 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TWITTER_SHORT_CODE, code);
        editor.commit();
    }

    public void onTweetClick(View view) {
        EditText tweetText = (EditText) findViewById(R.id.tweetBox);
        String tweet = tweetText.getText().toString();

        EditText shortCodeText = getShortCodeText();
        String shortCode = shortCodeText.getText().toString();
        saveTwitterShortCode(shortCode);

        SmsManager defaultSMSManager = SmsManager.getDefault();
        defaultSMSManager.sendTextMessage(shortCode, null, tweet, null, null);

        displayToast("Tweet sent successfully");
    }

    private EditText getShortCodeText() {
        return (EditText) findViewById(R.id.twitterShortCode);
    }

    private void displayToast(String toastMessage) {
        Toast toast = Toast.makeText(getApplicationContext(), toastMessage, LENGTH_SHORT);
        toast.show();
    }
}