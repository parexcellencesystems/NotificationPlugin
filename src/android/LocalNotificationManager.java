package com.parexcellencesystems.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;

public class LocalNotificationManager {	
    
	private static LocalNotificationManager _instance = null;

    protected LocalNotificationManager() {
    }
    
    public static LocalNotificationManager getInstance() {
        if(_instance == null) {
    	    _instance = new LocalNotificationManager();
        }
        return _instance;
    }

    private PendingIntent getPendingIntent(Context context)
    {
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        return pendingIntent;
    }

    public void run(Context context) {
    	GetNotificationJob job = new GetNotificationJob(context);
    	job.execute();
    }

    protected void stop(Context context) {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    	PendingIntent pendingIntent = getPendingIntent(context);
    	alarmManager.cancel(pendingIntent);
    	
    	String packageName = this.getClass().getPackage().getName();
    	SharedPreferences prefs = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
    	prefs.edit().putBoolean(Constants.SETTING_ENABLED, false).apply();
    }
    
    protected void start(Context context, String getUrl, String authorizationHeader, long intervalMillis, String title, String icon) {
    	
        // Stop the current job (if there is one)
        stop(context);

    	String packageName = this.getClass().getPackage().getName();
    	
    	SharedPreferences prefs = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
    	
    	prefs.edit()
			.putBoolean(Constants.SETTING_ENABLED, true)
			.putString(Constants.SETTING_GET_URL, getUrl)
			.putString(Constants.SETTING_AUTH_HEADER, authorizationHeader)
			.putLong(Constants.SETTING_INTERVAL, intervalMillis)
			.putString(Constants.SETTING_TITLE, title)
			.putString(Constants.SETTING_ICON, icon)
	    	.apply();
    	
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = getPendingIntent(context);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), intervalMillis, pendingIntent);
    }
}