package com.parexcellencesystems.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by drew woods on 8/13/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    	LocalNotificationManager.getInstance().run(context);
    }
}