package com.amnesty.panicbutton.spike;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MultiButtonActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_button);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.multibutton);
        for (int count = 0; count < linearLayout.getChildCount(); count++) {
            View child = linearLayout.getChildAt(count);

            if (child instanceof Button) {
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button button = (Button) view;
                        Toast.makeText(MultiButtonActivity.this,
                                "Clicked " + button.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.v("MultiButtonActivity","Clicked " + button.getText().toString());
                    }
                });
            }

        }
    }
}
