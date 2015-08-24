package com.parexcellencesystems.notification;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by drew woods on 8/12/2015.
 */
public class NotificationPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
        	if(Constants.ACTION_START.equals(action)) {
                JSONObject jsonArgs = args.getJSONObject(0);

                LocalNotificationManager.getInstance().start(
                	this.cordova.getActivity().getApplicationContext(),
                	jsonArgs.getString(Constants.SETTING_GET_URL),
                	jsonArgs.getString(Constants.SETTING_AUTH_HEADER),
                	jsonArgs.getLong(Constants.SETTING_INTERVAL),
                	jsonArgs.getString(Constants.SETTING_TITLE),
                	jsonArgs.getString(Constants.SETTING_ICON)
                );

                callbackContext.success();
                return true;
            } else if(Constants.ACTION_STOP.equals(action)) {
                LocalNotificationManager.getInstance().stop(this.cordova.getActivity().getApplicationContext());

                callbackContext.success();
                return true;
            } else {
                callbackContext.error("Invalid action");
                return false;
            }
        } catch(Exception e) {
            callbackContext.error(e.getMessage());
            return false;
        }
    }
}