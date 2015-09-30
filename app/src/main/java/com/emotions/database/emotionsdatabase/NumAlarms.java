package com.emotions.database.emotionsdatabase;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class NumAlarms extends AppCompatActivity {

    NumberPicker npNumAlarms;
    Button btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_alarms);

        npNumAlarms = (NumberPicker) findViewById(R.id.npNumAlarms);
        btnSet      = (Button)       findViewById(R.id.btnSetAlarm);

        npNumAlarms.setMinValue(0);
        npNumAlarms.setMaxValue(9);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent alarmIntent = new Intent(NumAlarms.this, AlarmClass.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(NumAlarms.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent);
            }
        });
    }
}
