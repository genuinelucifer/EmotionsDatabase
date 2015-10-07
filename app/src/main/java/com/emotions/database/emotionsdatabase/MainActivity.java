package com.emotions.database.emotionsdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextBtn = (Button) findViewById(R.id.btnNextMain);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HappyMeter.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_common, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_SetAlarm) {
            Intent i = new Intent(MainActivity.this, NumAlarms.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

/*
You want to call android.telephony.TelephonyManager.getDeviceId().

This will return whatever string uniquely identifies the device (IMEI on GSM, MEID for CDMA).

You'll need the following permission in your AndroidManifest.xml:

<uses-permission android:name="android.permission.READ_PHONE_STATE" />

in order to do this.
 */
