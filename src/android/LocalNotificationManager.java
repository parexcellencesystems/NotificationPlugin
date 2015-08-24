package com.parexcellencesystems.notification;










/*import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
*/
//import org.apache.cordova.CordovaActivity;
//import org.apache.cordova.CordovaInterface;

/*import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
*/




//import android.content.pm.PackageManager;

import android.app.AlarmManager;
//import android.app.Notification;
//import android.app.NotificationManager;
import android.app.PendingIntent;
//import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.os.AsyncTask;
import android.os.SystemClock;
//import android.support.v4.app.NotificationCompat;

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

    
    //private CordovaInterface _cordova = null;
    //private Context _context = null;
    
    //private PendingIntent _pendingIntent = null;
    
    
    
   /* private String _apiUrl = null;
    private String _authorizationHeader = null;
    private String _title = null;
    private String _icon = null;*/
    
    
    
    //private long _intervalMillis = 0;
    
    //private PendingIntent getPendingIntent(CordovaInterface cordova)
    private PendingIntent getPendingIntent(Context context)
    {
        //Intent intent = new Intent(cordova.getActivity(), AlarmReceiver.class);
        Intent intent = new Intent(context, AlarmReceiver.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(cordova.getActivity(), 0, intent, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        return pendingIntent;
    }


    
    
    
    
    
    public void run(Context context) {
    	
    	
    //public void run(Context context) {
    //public static void run() {
        //protected static void execute() {
    	
    	//Class<?> o = CordovaApp.class;
    	//CordovaApp.
    	
    	//Task.execute();
    	
    	//SharedPreferences prefs = context.getSharedPreferences("com.parexcellencesystems.notification", Context.MODE_PRIVATE);
    	//String au = prefs.getString("apiUrl", "");
    	
    	
    	
    	//_context = context;
        //Task task = new Task(this.cordova);
    	GetNotificationJob job = new GetNotificationJob(context);
    	//Task task = new Task(context);
    	job.execute();
    }

    //protected static void stop() {
    //protected void stop(CordovaInterface cordova) {
    protected void stop(Context context) {
    	//cordova.
    	//this.getInstance().
    	//pendingIntent = new PendingIntent();
    	
        //Intent intent = new Intent(cordova.getActivity(), AlarmReceiver.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(cordova.getActivity(), 0, intent, 0);
    	
        
        //AlarmManager alarmManager = (AlarmManager)cordova.getActivity().getSystemService(Context.ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    	//PendingIntent pendingIntent = getPendingIntent(cordova);
    	PendingIntent pendingIntent = getPendingIntent(context);

    	alarmManager.cancel(pendingIntent);


    	
    	String packageName = this.getClass().getPackage().getName();
    	SharedPreferences prefs = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
    	prefs.edit().putBoolean(Constants.SETTING_ENABLED, false).apply();
        
        
    	//intent { cmp=com.parexcellencesystems.test/com.parexcellencesystems.notification.AlarmReceiver }
    	
    	
        //if(_pendingIntent != null) {
        	
        	//this.getInstance().
            //AlarmManager alarmManager = (AlarmManager)_context.getActivity().getSystemService(Context.ALARM_SERVICE);
            //alarmManager.cancel(_pendingIntent);
        //}
    }
    
    //protected static void start(Context context, String apiUrl, String authorizationHeader, long intervalMillis)
    //protected void start(CordovaInterface cordova, String apiUrl, String authorizationHeader, long intervalMillis, String title, String icon) {
    protected void start(Context context, String apiUrl, String authorizationHeader, long intervalMillis, String title, String icon) {
    	
        // Stop the current job (if there is one)
        //stop(cordova);
        stop(context);

        
        
        
    	//String xxx = _cordova.getActivity().getPackageName();
    	//String yyy = this.toString();
        
        
        
        
        //context.get
    	
    	String packageName = this.getClass().getPackage().getName();
    	SharedPreferences prefs = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
    	
/*    	prefs.edit().putBoolean(Constants.SETTING_ENABLED, true).apply();
    	prefs.edit().putString(Constants.SETTING_API_URL, apiUrl).apply();
    	prefs.edit().putString(Constants.SETTING_AUTH_HEADER, authorizationHeader).apply();
    	prefs.edit().putLong(Constants.SETTING_INTERVAL, intervalMillis).apply();
    	prefs.edit().putString(Constants.SETTING_TITLE, title).apply();
    	prefs.edit().putString(Constants.SETTING_ICON, icon).apply();
*/    	
    	
    	prefs.edit()
			.putBoolean(Constants.SETTING_ENABLED, true)
			.putString(Constants.SETTING_API_URL, apiUrl)
			.putString(Constants.SETTING_AUTH_HEADER, authorizationHeader)
			.putLong(Constants.SETTING_INTERVAL, intervalMillis)
			.putString(Constants.SETTING_TITLE, title)
			.putString(Constants.SETTING_ICON, icon)
	    	.apply();
    	
    	
    	
    	
    	//_cordova = cordova;
        /*_apiUrl = apiUrl;
        _authorizationHeader = authorizationHeader;
        _title = title;
        _icon = icon;*/

        
        
    	//PendingIntent pendingIntent = getPendingIntent(cordova);
        
        //if(_pendingIntent != null) {
        //}



// Retrieve a PendingIntent that will perform a broadcast
        //Intent intent = new Intent(cordova.getActivity(), AlarmReceiver.class);
        //_pendingIntent = PendingIntent.getBroadcast(cordova.getActivity(), 0, intent, 0);
        //Intent alarmIntent = new Intent(MyActivity.this, AlarmReceiver.class);
        //pendingIntent = PendingIntent.getBroadcast(MyActivity.this, 0, alarmIntent, 0);

//this.registerReceiver(AlarmReceiver, new IntentFilter(""));

        
        //AlarmManager alarmManager = (AlarmManager)cordova.getActivity().getSystemService(Context.ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        //PendingIntent pendingIntent = getPendingIntent(cordova);
        PendingIntent pendingIntent = getPendingIntent(context);

        //AlarmManager.INTERVAL_FIFTEEN_MINUTES
        

        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), (5 * 1000), pendingIntent);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), intervalMillis, pendingIntent);
        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

        //Toast.makeText(_context, "Alarm set", Toast.LENGTH_SHORT).show();

    }



    
    
    
    
	
}
