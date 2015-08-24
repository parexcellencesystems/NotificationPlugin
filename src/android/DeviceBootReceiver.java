package com.parexcellencesystems.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by drew woods on 8/14/2015.
 */
public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {


            // After the device is rebooted we must restart the receive notifications job
            //TODO the api url, auth header, and interval will be stored off somewhere
            
            //ReceiveNotificationsJob.execute();
/*

            *//* Setting the alarm here *//*
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            int interval = 8000;
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

            Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();*/
        	
        	
        	
        	String packageName = this.getClass().getPackage().getName();
        	SharedPreferences prefs = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        	        	
        	// Check if we should restart the service
        	Boolean enabled = prefs.getBoolean(Constants.SETTING_ENABLED, false);
        	
        	if(enabled) {
        		// Get the rest of the stored settings
            	String apiUrl = prefs.getString(Constants.SETTING_API_URL, "");
            	String authorizationHeader = prefs.getString(Constants.SETTING_AUTH_HEADER, "");
            	Long intervalMillis = prefs.getLong(Constants.SETTING_INTERVAL, 0);
            	String title = prefs.getString(Constants.SETTING_TITLE, "");
            	String icon = prefs.getString(Constants.SETTING_ICON, "");
            	
            	// Restart the service
            	LocalNotificationManager.getInstance().start(context, apiUrl, authorizationHeader, intervalMillis, title, icon);
        	}        	
        }
    }
}