package com.emotions.database.emotionsdatabase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Accepts the Broadcast from alarm and shows notification if required.
 *
 * Created by Abhinav Tripathi on 30-Sep-15.
 */
public class AlarmClass extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar now = GregorianCalendar.getInstance();
        int dayOfWeek = now.get(Calendar.DATE);
        if(dayOfWeek != 1 && dayOfWeek != 7) {
            NotificationCompat.Builder mBuilder = 
                    new NotificationCompat.Builder(context)
                    .setContentTitle(context.getResources().getString(R.string.strAlarmTitle))
                    .setContentText(context.getResources().getString(R.string.strAlarmText));
            Intent resultIntent = new Intent(context, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
    }
}

