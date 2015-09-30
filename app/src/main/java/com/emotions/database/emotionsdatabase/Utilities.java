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
}
