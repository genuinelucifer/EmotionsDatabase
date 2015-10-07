package com.emotions.database.emotionsdatabase;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.GregorianCalendar;

/**
 * Accepts the Broadcast from alarm and shows notification if required.
 *
 * Created by Abhinav Tripathi on 30-Sep-15.
 */
public class AlarmClass extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);
        /*int numAlarms = Integer.parseInt(Utilities.getPref(NumAlarms.class, Utilities.NUM_ALARMS_PREF_KEY));
        Calendar now = GregorianCalendar.getInstance();
        int hour = now.get(Calendar.HOUR);
        int min  = now.get(Calendar.MINUTE);
        int sec  = now.get(Calendar.SECOND);
        Log.d("ALARMS","NUPP RA! NumAlarms <= 0 !\n-----*********************************************\n");
        if(numAlarms>0) {*/
            Log.d("ALARMS","YUP RA! NumAlarms > 0 !\n-----*********************************************\n");
            //if(!Utilities.doAlarmNow(hour,min,sec, numAlarms)) return;

        //Toast.makeText( , "Yup notif.. ", Toast.LENGTH_LONG).show();

            /*NotificationCompat.Builder mBuilder =
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
            mNotificationManager.notify(1, mBuilder.build());*/
        //Intent i = new Intent(AlarmClass.this, MainActivity.class);

        //}

        long alt = Utilities.getNextMinForAlarm(context);
        if(alt<=0)
        {
            Log.d("AlarmClass", "Didn't set alarm as alt is zero! and NumAlarms = " + Utilities.getNumAlarms(context));
            return;
        }
        Long time = new GregorianCalendar().getTimeInMillis()+alt*60*1000;
        Intent intentAlarm = new Intent(context, AlarmClass.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(context, "Next alarm after : " + alt + " minutes.", Toast.LENGTH_SHORT).show();
    }
}

