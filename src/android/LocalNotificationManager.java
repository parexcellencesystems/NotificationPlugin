package com.parexcellencesystems.notification;










import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.cordova.CordovaInterface;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

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
    
    private CordovaInterface _cordova = null;
    private PendingIntent _pendingIntent = null;
    //private Context _context = null;
    private String _apiUrl = null;
    private String _authorizationHeader = null;
    
    private String _title = null;
    private String _icon = null;
    
    //private long _intervalMillis = 0;
    
    
    public void run() {
    //public static void run() {
        //protected static void execute() {
    	
    	//Class<?> o = CordovaApp.class;
    	//CordovaApp.
    	
    	//Task.execute();
    	
    	
        //Task task = new Task(this.cordova);
    	Task task = new Task(_cordova);
        task.execute();
    }

    //protected static void stop() {
    protected void stop() {
        if(_pendingIntent != null) {
            AlarmManager alarmManager = (AlarmManager)_cordova.getActivity().getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(_pendingIntent);
        }
    }

    //protected static void start(Context context, String apiUrl, String authorizationHeader, long intervalMillis)
    protected void start(CordovaInterface cordova, String apiUrl, String authorizationHeader, long intervalMillis, String title, String icon) {
    	_cordova = cordova;
        //_context = context;
        _apiUrl = apiUrl;
        _authorizationHeader = authorizationHeader;
        //_intervalMillis



        _title = title;
        _icon = icon;

        //if(_pendingIntent != null) {
        // Stop the current job (if there is one)
        stop();
        //}



// Retrieve a PendingIntent that will perform a broadcast
        Intent intent = new Intent(_cordova.getActivity(), AlarmReceiver.class);
        _pendingIntent = PendingIntent.getBroadcast(_cordova.getActivity(), 0, intent, 0);
        //Intent alarmIntent = new Intent(MyActivity.this, AlarmReceiver.class);
        //pendingIntent = PendingIntent.getBroadcast(MyActivity.this, 0, alarmIntent, 0);

//this.registerReceiver(AlarmReceiver, new IntentFilter(""));


        //AlarmManager.INTERVAL_FIFTEEN_MINUTES
        AlarmManager alarmManager = (AlarmManager)_cordova.getActivity().getSystemService(Context.ALARM_SERVICE);

        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), (5 * 1000), pendingIntent);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), intervalMillis, _pendingIntent);
        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

        //Toast.makeText(_context, "Alarm set", Toast.LENGTH_SHORT).show();

    }



    
    
    
    //private static class Task extends AsyncTask <Void, Void, String> {
    private class Task extends AsyncTask <Void, Void, String> {
    	
    	
    	
    	
    	
    	private CordovaInterface _cordova = null;
    	
    	public Task(CordovaInterface cordova)
    	{
    		_cordova = cordova;
    	}

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
                //Exception r = e;




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
            

            
            
            //Uri uri = Uri.parse(options.optString("iconUri"));
            //bmp = assets.getIconFromUri(uri);
            
            //AssetUtil 
            
            //int resId = assets.getResIdForDrawable(_icon);
            
            
            
           
            
            int iconId = _cordova.getActivity().getResources().getIdentifier(_icon, "drawable", _cordova.getActivity().getPackageName());
            
            
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(_cordova.getActivity())
            		//new NotificationCompat.Builder(CordovaApp.this.getActivity())
                            .setSmallIcon(iconId)// ((R.drawable.icon)
                            .setContentTitle(_title)
                            .setContentText("Get yer ya-yas out!");
// Creates an explicit intent for an Activity in your app
            //Intent resultIntent = new Intent(_context, CordovaApp.class);
            //Intent resultIntent = new Intent(_context, CordovaPlugin.class);
            
            
            Intent resultIntent = new Intent(_cordova.getActivity(), _cordova.getClass());
            //Intent resultIntent = new Intent(_cordova.getActivity(), CordovaApp.class);
            //Intent resultIntent = new Intent(CordovaApp.this.getActivity(), CordovaApp.class);
            

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(_cordova.getActivity());
            
            
// Adds the back stack for the Intent (but not the Intent itself)
            //stackBuilder.addParentStack(CordovaApp.class);
            stackBuilder.addParentStack(_cordova.getClass());
            //stackBuilder.addParentStack(CordovaApp.class);
// Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);




            NotificationManager mNotificationManager = (NotificationManager)_cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
            mNotificationManager.notify(0, mBuilder.build());












            //return s;


            //return null;





        }


    }
    
	
}
