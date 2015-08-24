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
            // We must manually restart the notification service after the device is rebooted (if it was running before the reboot)
        	String packageName = this.getClass().getPackage().getName();
        	SharedPreferences prefs = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        	
        	Boolean enabled = prefs.getBoolean(Constants.SETTING_ENABLED, false);

        	// The service was running before the reboot
        	if(enabled) {
        		// Get the rest of the stored settings
            	String getUrl = prefs.getString(Constants.SETTING_GET_URL, "");
            	String authorizationHeader = prefs.getString(Constants.SETTING_AUTH_HEADER, "");
            	Long intervalMillis = prefs.getLong(Constants.SETTING_INTERVAL, 0);
            	String title = prefs.getString(Constants.SETTING_TITLE, "");
            	String icon = prefs.getString(Constants.SETTING_ICON, "");
            	
            	// Restart the service
            	LocalNotificationManager.getInstance().start(context, getUrl, authorizationHeader, intervalMillis, title, icon);
        	}        	
        }
    }
}