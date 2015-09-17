package com.emotions.database.emotionsdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VerticalSeekBar;

/**
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
}
