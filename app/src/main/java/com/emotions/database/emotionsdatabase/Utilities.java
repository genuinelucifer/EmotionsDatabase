package com.emotions.database.emotionsdatabase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class Utilities {
    private static String comment = "";
    private static int happiness = 0;
    private static int sleepiness = 0;
    private static int numEngagements = 0;

    public static void setComment(String str) { comment = str; }
    public static String getComment()
    {
        return comment;
    }

    public static void setHappiness(int h)
    {
        happiness = h;
    }
    public static int getHappiness()
    {
        return happiness;
    }

    public static void setSleepiness(int s)
    {
        sleepiness = s;
    }
    public static int getSleepiness()
    {
        return sleepiness;
    }

    public static void setNumEngagements(int n)
    {
        numEngagements = n;
    }
    public static int getNumEngagements()
    {
        return numEngagements;
    }

    public static boolean isNetworkAvailable(Context ctx)
    {
        ConnectivityManager ctvMngr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo aNetInfo = ctvMngr.getActiveNetworkInfo();
        return aNetInfo != null && aNetInfo.isAvailable();
    }
}
