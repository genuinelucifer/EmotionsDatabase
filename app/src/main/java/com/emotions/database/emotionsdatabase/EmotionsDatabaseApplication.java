package com.emotions.database.emotionsdatabase;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class EmotionsDatabaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "qxcFSVca93yaTsgOLAG3FmIv1RnUGhsygpHEeJ52", "AkHHI3wUoIDQADDaCUJgl3xo4aU3Po6v0aTO8msO");
    }
}
