package com.emotions.database.emotionsdatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class EngageMedia extends Fragment {
    RadioGroup rgNumMedia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View android = inflater.inflate(R.layout.engagemedia, container, false);
        rgNumMedia = (RadioGroup) android.findViewById(R.id.rgNumMedia);
        Utilities.setNumEngagements(0);
        rgNumMedia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rbZero:
                        Utilities.setNumEngagements(0);
                        break;
                    case R.id.rbOne:
                        Utilities.setNumEngagements(1);
                        break;
                    case R.id.rbTwo:
                        Utilities.setNumEngagements(2);
                        break;
                    case R.id.rbThree:
                        Utilities.setNumEngagements(3);
                        break;
                    case R.id.rbMore:
                        Utilities.setNumEngagements(4);
                        break;
                    default:
                        break;
                }
            }
        });

        return android;
    }
}
