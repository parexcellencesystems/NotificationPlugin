package com.parexcellencesystems.notification;


/*import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
*/

import org.apache.cordova.CallbackContext;
//import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
//import org.apache.http.HttpResponse;

//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
*/





/**
 * Created by drew woods on 8/12/2015.
 */


//public static class ReceiveNotificationsJob extends AsyncTask <Void, Void, String> {
//public class ReceiveNotificationsJob {
public class NotificationPlugin extends CordovaPlugin {

    /*private static NotificationPlugin _instance = null;
    protected NotificationPlugin() {
    }
    public static NotificationPlugin getInstance() {
        if(_instance == null) {
    	    _instance = new NotificationPlugin();
        }
        return _instance;
    }*/
	   
	   
    
    
    
	   
	   

    public static final String ACTION_START = "start";
    public static final String ACTION_STOP = "stop";





/*    private static PendingIntent _pendingIntent = null;
    private static Context _context = null;
    private static String _apiUrl = null;
    private static String _authorizationHeader = null;
    private static long _interval = 0;
*/


/*    public void run() {
    //public static void run() {
        //protected static void execute() {
    	
    	//Class<?> o = CordovaApp.class;
    	//CordovaApp.
    	
    	//Task.execute();
    	
    	
        Task task = new Task(this.cordova);
        task.execute();
    }
*/



//CordovaPlugin
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {


        //Toast.makeText(_context, action, Toast.LENGTH_SHORT).show();


    	//Object o = this.cordova;
    	
    	

        try {
        	
        	if(action.equals(ACTION_START)) {
                //this.cordova.getActivity().startActivity(new Intent(Intent.ACTION_START.ACTION_EDIT ACTION_START));

                JSONObject jsonArgs = args.getJSONObject(0);

                //TODO store the api url, auth header, and interval off somewhere
/*                _context = ;
                _apiUrl = ;
                _authorizationHeader = ;
                _interval = ;
*/
                LocalNotificationManager.getInstance().start(
                	this.cordova, 
                	jsonArgs.getString("apiUrl"), 
                	jsonArgs.getString("authorizationHeader"), 
                	jsonArgs.getLong("intervalMillis"),
                	jsonArgs.getString("title"),
                	jsonArgs.getString("icon")
                );

                callbackContext.success();
                return true;
            } else if(action.equals(ACTION_STOP)) {
                //this.cordova.getActivity()..stop.startActivity(new Intent(ACTION_STOP));
                LocalNotificationManager.getInstance().stop();
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


        //protected static void execute() {
        //Task task = new Task();
        //task.execute();
    }

    
    
    
    
    
    
    
    
    
    
    




    //private Context _context = null;


/*

    //private PendingIntent _pendingIntent;
    //private AlarmManager _alarmManager;
    Activity _activity = null;


    public ReceiveNotificationsJob(Activity activity) {
        _activity = activity;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        *//*
        * args: api url, auth header, execution interval (or 0?)
        *
        * *//*


        Context context = _activity.getApplicationContext();


        Calendar calendar = Calendar.getInstance();

// Retrieve a PendingIntent that will perform a broadcast
        //Intent alarmIntent = ;
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, alarmIntent);
        //alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis() + 10000, alarmIntent);

        //alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

        return true;
    }*/
}
