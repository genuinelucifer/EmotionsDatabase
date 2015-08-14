package com.emotions.database.emotionsdatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class HappyMeter extends Fragment {
    SeekBar sbHappiness, sbSleepiness;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View android = inflater.inflate(R.layout.fragmenthappy, container, false);
        sbHappiness = (SeekBar) android.findViewById(R.id.sbHappiness);
        sbSleepiness = (SeekBar) android.findViewById(R.id.sbSleepiness);

        sbHappiness.setProgress(0);
        Utilities.setHappiness(0);
        sbSleepiness.setProgress(0);
        Utilities.setSleepiness(0);

        sbHappiness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Utilities.setHappiness(sbHappiness.getProgress());
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
                Utilities.setSleepiness(sbSleepiness.getProgress());
            }
        });

        return android;
    }
}
