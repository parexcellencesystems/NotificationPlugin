package com.parexcellencesystems.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by drew woods on 8/14/2015.
 */
public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {


            // After the device is rebooted we must restart the receive notifications job
            //TODO the api url, auth header, and interval will be stored off somewhere
            ReceiveNotificationsJob.start(context, "http://drew/parex.mobile/api", "", 10000);
            //ReceiveNotificationsJob.execute();
/*

            *//* Setting the alarm here *//*
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            int interval = 8000;
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

            Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();*/
        }
    }
}