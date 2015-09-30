package com.emotions.database.emotionsdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VerticalSeekBar;

/**
 * Stores Happiness On a scale of 10
 *
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class HappyMeter extends AppCompatActivity {

    VerticalSeekBar sbHappiness, sbSleepiness;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmenthappy);

        sbHappiness = (VerticalSeekBar) findViewById(R.id.sbHappiness);
        sbSleepiness = (VerticalSeekBar) findViewById(R.id.sbSleepiness);

        Utilities.setHappiness(5);
        Utilities.setSleepiness(5);

        sbHappiness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Utilities.setHappiness(sbHappiness.getProgress() + 1);
                //Toast.makeText(HappyMeter.this, "The meter Read: "+ sbHappiness.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        sbSleepiness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Utilities.setSleepiness(sbSleepiness.getProgress() + 1);
                //Toast.makeText(HappyMeter.this, "The meter Read: "+ sbSleepiness.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        next = (Button) findViewById(R.id.btnNextHappy);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HappyMeter.this, MediaList.class);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
