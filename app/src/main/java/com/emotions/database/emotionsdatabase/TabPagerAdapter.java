package com.emotions.database.emotionsdatabase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragment for Happy Tab
                return new HappyMeter();
            case 1:
                //Fragment for Engagement Tab
                return new EngageMedia();
            case 2:
                //Fragment for Comment Tab
                return new Comment();
        }
        return null;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3; //No of Tabs
    }

}
