package com.emotions.database.emotionsdatabase;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Some Utility functions and also a common point to coordinate data transfer between activities
 *
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


    /*
        	No media activity
        	Reading print media (E-books, or hard copy )
        	Watching Television, video, and//or DVD’s (on a TV)
        	Watching video on a computer (you tube etc)
        	Listening to music
        	Listening to non musical audio (news, radio, podcasts etc)
        	Playing video, computer or mobile games
        	Talking on phone (mobile, video conferencing etc)
        	Instant messaging (watsapp, google talk, skype etc)
        	Text message using mobile
        	Reading and writing e-mail
        	Web surfing, pdfs etc
        	Using computer application (word processing, programming etc)
        	Other media activity
     */
    public static final int NUM_MEDIA_LIST = 14;
    public static int cbIDs[] = {R.id.cbNoMedia, R.id.cbPrintMedia, R.id.cbTV, R.id.cbVidCom,
                        R.id.cbMusic, R.id.cbAudio, R.id.cbGames, R.id.cbPhone, R.id.cbInstMsg,
                        R.id.cbTextMsg, R.id.cbEmail, R.id.cbWeb, R.id.cbComApps, R.id.cbNoMedia};
    public static String checkBoxesStrings[] = {"Media_None","Media_Print","Media_TV","Media_VideoPC",
                        "Media_Music","Media_Audio","Media_Games","Media_Phone","Media_InstantMessaging",
                        "Media_MobileTexts","Media_EMail","Media_Web","Media_ComApps","Media_Other"};
    public static boolean[] checkBoxesCheckedState = new boolean[NUM_MEDIA_LIST];

    public static boolean isNetworkAvailable(Context ctx)
    {
        ConnectivityManager ctvMngr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo aNetInfo = ctvMngr.getActiveNetworkInfo();
        return aNetInfo != null && aNetInfo.isAvailable();
    }

    public static final String NUM_ALARMS_PREF_KEY = "NUM_ALARMS_PREFERENCE";
    public static int getPrefInt(Context ctx, String prefKey)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx.getApplicationContext());
        return sp.getInt(prefKey, 0);
    }
    public static void savePrefInt(Context ctx, String prefKey, int prefValue)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx.getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(prefKey, prefValue);
        editor.apply();
    }

    public static void storeNumAlarms(int numAlarm, Context ctx)
    {
        savePrefInt(ctx, NUM_ALARMS_PREF_KEY, numAlarm);
    }
    public static int getNumAlarms(Context ctx)
    {
        return getPrefInt(ctx, NUM_ALARMS_PREF_KEY);
    }
    public static long getNextMinForAlarm(Context ctx)
    {
        int numAlarms = getNumAlarms(ctx);
        // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
        // we fetch  the current time in milliseconds and added 1 day time
        // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day
        Calendar cal = new GregorianCalendar();
        int h = cal.get(Calendar.HOUR_OF_DAY), m = cal.get(Calendar.MINUTE);
        int tot = 12 * 60;
        int diff = 0, alt = 0;
        if(numAlarms>1)
            diff = tot/(numAlarms-1);
        int curh = 9, curm = 0;
        for(int i=0;i<numAlarms;++i)
        {
            if(h<curh || (h==curh && m<curm))
            {
                alt = (curh-h)*60 + curm-m;
                break;
            }
            curm += diff;
            curh += curm/60;
            curm %= 60;
        }
        if(alt==0 && numAlarms>=1)
            alt = (23-h)*60 + (60-m) + 9*60;
        return alt;
    }

}
