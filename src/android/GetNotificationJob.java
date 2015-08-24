package com.parexcellencesystems.notification;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;



	
	
//private static class Task extends AsyncTask <Void, Void, String> {
public class GetNotificationJob extends AsyncTask <Void, Void, String> {
	
	
	
	
	
	//private CordovaInterface _cordova = null;
	private Context _context = null;
	
	//public Task(CordovaInterface cordova)
	//public Task(Context context)
	public GetNotificationJob(Context context)
	{
		//_cordova = cordova;
		_context = context;
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
        String packageName = this.getClass().getPackage().getName();
    	SharedPreferences prefs = _context.getSharedPreferences(packageName, Context.MODE_PRIVATE);

    	String apiUrl = prefs.getString(Constants.SETTING_API_URL, "");
    	String authorizationHeader = prefs.getString(Constants.SETTING_AUTH_HEADER, "");
    	

    	
    	
    	
    	

        
    	
    	
    	
        HttpGet httpGet = new HttpGet(apiUrl + (apiUrl.substring(apiUrl.length() - 1) != "/" ? "/" : "") + "1/locations");
        httpGet.addHeader("Authorization", authorizationHeader);


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
        

        //String packageName = _context.getPackageName(); //.getApplicationContext().getPackageName();
        String packageName = this.getClass().getPackage().getName();
        
        
        
    	//SharedPreferences prefs = _cordova.getActivity().getSharedPreferences("com.parexcellencesystems.notification", Context.MODE_PRIVATE);
    	SharedPreferences prefs = _context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
    	String title = prefs.getString(Constants.SETTING_TITLE, "");
    	String icon = prefs.getString(Constants.SETTING_ICON, "");
        
    	
    	
       //_context.getApplicationContext().getApplicationInfo().
        
        
    	
    	//int iconId = _cordova.getActivity().getResources().getIdentifier(icon, "drawable", _cordova.getActivity().getPackageName());
    	//int iconId = _context.getApplicationContext().getResources().getIdentifier(icon, "drawable", _context.getApplicationContext().getPackageName());
    	int iconId = _context.getResources().getIdentifier(icon, "drawable", _context.getPackageName());
        
        //int iconId = _context.getResources().getIdentifier(icon, "drawable", _cordova.getActivity().getPackageName());
        
        NotificationCompat.Builder notificationBuilder =
                //new NotificationCompat.Builder(_cordova.getActivity())
        		new NotificationCompat.Builder(_context)
        		//new NotificationCompat.Builder(CordovaApp.this.getActivity())
                        .setSmallIcon(iconId)// ((R.drawable.icon)
                        .setContentTitle(title)
                        .setContentText("Get yer ya-yas out!");
// Creates an explicit intent for an Activity in your app
        //Intent resultIntent = new Intent(_context, CordovaApp.class);
        //Intent resultIntent = new Intent(_context, CordovaPlugin.class);
        
        
        
        //Class c = _context.getApplicationContext().getClass();
        
        
        PackageManager packageManager = _context.getPackageManager();
        
        //Intent resultIntent = new Intent(_cordova.getActivity(), _cordova.getClass());
        //Intent resultIntent = new Intent(_context, _context.getApplicationContext().getClass());
        
        
        
        //Intent resultIntent = new Intent(_context, com.parexcellencesystems.test.CordovaApp.class);
        //.getApplicationContext().getPackageName()
        
        
        
        
        Intent resultIntent = packageManager.getLaunchIntentForPackage(_context.getPackageName());
        
        
        
        
        //Intent resultIntent = new Intent(_cordova.getActivity(), CordovaApp.class);
        //Intent resultIntent = new Intent(CordovaApp.this.getActivity(), CordovaApp.class);

        
        
        //Intent resultIntent = new Intent(this, ResultActivity.class);
        //...
        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
            _context,
            0,
            resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        );
        /*PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );*/            
        
        

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        
        
        
        
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(_cordova.getActivity());
        
        
        
        
        
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(_context);
// Adds the back stack for the Intent (but not the Intent itself)
        //stackBuilder.addParentStack(_context.getApplicationContext().getClass());
        
        
        
// Adds the Intent that starts the Activity to the top of the stack
        //stackBuilder.addNextIntent(resultIntent);
 

            /*PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );*/
        

     // When the notification is clicked
        notificationBuilder.setContentIntent(resultPendingIntent); 


        //NotificationManager mNotificationManager = (NotificationManager)_cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager notificationManager = (NotificationManager)_context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        
        
        
        Notification notification = notificationBuilder.build();
//        n.flags |= Notification.FLAG_AUTO_CANCEL;
        
        notificationManager.notify(0, notification);












        //return s;


        //return null;





    }
}