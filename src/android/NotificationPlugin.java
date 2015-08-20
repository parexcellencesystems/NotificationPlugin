package com.parexcellencesystems.notification;

import android.app.Activity;
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

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by drew woods on 8/12/2015.
 */


//public static class ReceiveNotificationsJob extends AsyncTask <Void, Void, String> {
//public class ReceiveNotificationsJob {
public class NotificationPlugin extends CordovaPlugin {



    public static final String ACTION_START = "start";
    public static final String ACTION_STOP = "stop";





    private static PendingIntent _pendingIntent = null;



    private static Context _context = null;
    private static String _apiUrl = null;
    private static String _authorizationHeader = null;
    private static long _interval = 0;


   // @Override
    public static void run() {
        //protected static void execute() {
        Task task = new Task();
        task.execute();
    }




//CordovaPlugin
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {


        Toast.makeText(_context, action, Toast.LENGTH_SHORT).show();




        try {
        	
        	if(action.equals(ACTION_START)) {
                //this.cordova.getActivity().startActivity(new Intent(Intent.ACTION_START.ACTION_EDIT ACTION_START));

                JSONObject jsonArgs = args.getJSONObject(0);

                //TODO store the api url, auth header, and interval off somewhere
                _context = this.cordova.getActivity();
                _apiUrl = jsonArgs.getString("apiUrl");
                _authorizationHeader = jsonArgs.getString("authorizationHeader");
                _interval = jsonArgs.getLong("intervalMillis");

                start(_context, _apiUrl, _authorizationHeader, _interval);

                callbackContext.success();
                return true;
            } else if(action.equals(ACTION_STOP)) {
                //this.cordova.getActivity()..stop.startActivity(new Intent(ACTION_STOP));
                stop();
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

    protected static void stop() {
        if(_pendingIntent != null) {
            AlarmManager alarmManager = (AlarmManager)_context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(_pendingIntent);
        }
    }

    protected static void start(Context context, String apiUrl, String authorizationHeader, long intervalMillis)
    {

        _context = context;
        _apiUrl = apiUrl;
        _authorizationHeader = authorizationHeader;





        //if(_pendingIntent != null) {
        // Stop the current job (if there is one)
        stop();
        //}



// Retrieve a PendingIntent that will perform a broadcast
        Intent intent = new Intent(_context, AlarmReceiver.class);
        _pendingIntent = PendingIntent.getBroadcast(_context, 0, intent, 0);
        //Intent alarmIntent = new Intent(MyActivity.this, AlarmReceiver.class);
        //pendingIntent = PendingIntent.getBroadcast(MyActivity.this, 0, alarmIntent, 0);

//this.registerReceiver(AlarmReceiver, new IntentFilter(""));


        //AlarmManager.INTERVAL_FIFTEEN_MINUTES
        AlarmManager alarmManager = (AlarmManager)_context.getSystemService(Context.ALARM_SERVICE);

        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), (5 * 1000), pendingIntent);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), intervalMillis, _pendingIntent);
        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

        //Toast.makeText(_context, "Alarm set", Toast.LENGTH_SHORT).show();

    }


    private static class Task extends AsyncTask <Void, Void, String> {

        // This is what gets called when the alarm goes off (this is NOT called on the main thread)
        @Override
        //protected String doInBackground(Object... params)
        protected String doInBackground(Void... params) {
            //private void displayNotifications(Context context) {


            //_context = (Context)params[0];
            //String url = (String)params[1];
            //String authorizationHeader = (String)params[2];


            //HttpGet httpGet = new HttpGet("http://drew/parex.mobile/api/1/locations");


            HttpClient httpClient = new DefaultHttpClient();


/*

			url: getParMobileServiceUrl() + '/1/bins/' + encodeURI(bin.BINABUTTON),
			type: 'PUT',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(bin),
			dataType: 'json',
			cache: false,
			timeout: TIMEOUT_DEFAULT,
			beforeSend: function(xhr)
			{
				xhr.setRequestHeader('Authorization', getBasicAuthRequestHeader());
			},


 */


/*        HttpGet httpGet = new HttpGet("http://drew/parex.mobile/api/1/locations");
        */



            //TODO check if "/" is needed or not
            HttpGet httpGet = new HttpGet(_apiUrl + "/1/locations");
            httpGet.addHeader("Authorization", _authorizationHeader);


            //HttpGet httpGet = new HttpGet("http://drew/ParEx.Mobile/api/1/locations");


            HttpResponse httpResponse = null;
            String notificationsJson = null;
            try
            {
                httpResponse = httpClient.execute(httpGet);

                if(httpResponse.getStatusLine().getStatusCode() == 200) {
                    InputStream inputStream = httpResponse.getEntity().getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    try {
                        while((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                            //sb.append(line);
                        }
                        inputStream.close();
                    }
                    catch(Exception e) {
                        //TODO handle exceptions
                    }

                    notificationsJson = sb.toString();

                    //return sb.toString();

                }


            }
            catch(Exception e)
            {
                Exception r = e;




            }


            return notificationsJson;




        }

        //}

        @Override
        protected void onPostExecute(String notificationsJson) {
            super.onPostExecute(notificationsJson);








            //CordovaApp app = (CordovaApp)params[0];
            //Context context = mActivity.getApplicationContext();

            //Context context = _activity.getApplicationContext();


            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(_context)
                            .setSmallIcon(R.drawable.icon)
                            .setContentTitle("PAR Mobile")
                            .setContentText("Get yer ya-yas out!");
// Creates an explicit intent for an Activity in your app
            Intent resultIntent = new Intent(_context, CordovaApp.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(_context);
// Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(CordovaApp.class);
// Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);




            NotificationManager mNotificationManager = (NotificationManager)_context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
            mNotificationManager.notify(0, mBuilder.build());












            //return s;


            //return null;





        }


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
