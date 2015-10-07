package com.emotions.database.emotionsdatabase;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.GregorianCalendar;

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
        npNumAlarms.setMaxValue(50);

        npNumAlarms.setValue(Utilities.getNumAlarms(NumAlarms.this));

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utilities.savePref(NumAlarms.class, Utilities.NUM_ALARMS_PREF_KEY, Integer.toString(npNumAlarms.getValue()));
                int numAlarms = npNumAlarms.getValue();
                if(numAlarms==0)
                    return;
                Utilities.storeNumAlarms(numAlarms, NumAlarms.this);
                long alt = Utilities.getNextMinForAlarm(NumAlarms.this);
                //Toast.makeText(NumAlarms.this, "Stopped at " + curh + ":" + curm, Toast.LENGTH_SHORT).show();
                Long time = new GregorianCalendar().getTimeInMillis()+alt*60*1000;

                // create an Intent and set the class which will execute when Alarm triggers, here we have
                // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when

                Intent intentAlarm = new Intent(NumAlarms.this, AlarmClass.class);

                //Get the Alarm Service
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                //set the alarm for particular time
                alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(NumAlarms.this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

                //Toast.makeText(NumAlarms.this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();
                Toast.makeText(NumAlarms.this, "Alarms Set... for " + alt + " mins...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
