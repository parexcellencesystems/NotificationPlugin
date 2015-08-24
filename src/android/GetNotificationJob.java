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
	
public class GetNotificationJob extends AsyncTask <Void, Void, String> {

	private Context _context = null;

	public GetNotificationJob(Context context)
	{
		_context = context;
	}

    // This is what gets called when the alarm goes off (this is NOT called on the main thread)
    @Override
    protected String doInBackground(Void... params) {
        HttpClient httpClient = new DefaultHttpClient();

        String packageName = this.getClass().getPackage().getName();

        SharedPreferences prefs = _context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
    	String getUrl = prefs.getString(Constants.SETTING_GET_URL, "");
    	String authorizationHeader = prefs.getString(Constants.SETTING_AUTH_HEADER, "");
    	
        HttpGet httpGet = new HttpGet(getUrl);
        httpGet.addHeader("Authorization", authorizationHeader);

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
                    }
                    inputStream.close();
                }
                catch(Exception e) {
                    //TODO handle exceptions
                }

                notificationsJson = sb.toString();
            }
        }
        catch(Exception e)
        {
        	//TODO
        }
        return notificationsJson;
    }

    @Override
    protected void onPostExecute(String notificationsJson) {        
    	super.onPostExecute(notificationsJson);

    	String packageName = this.getClass().getPackage().getName();

    	SharedPreferences prefs = _context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
    	String title = prefs.getString(Constants.SETTING_TITLE, "");
    	String icon = prefs.getString(Constants.SETTING_ICON, "");
        
    	int iconId = _context.getResources().getIdentifier(icon, "drawable", _context.getPackageName());
        
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(_context)
	        .setAutoCancel(true)                
	        .setSmallIcon(iconId)
	        .setContentTitle(title)
	        .setContentText("Get yer ya-yas out!"); //TODO
        
        PackageManager packageManager = _context.getPackageManager();
        
        Intent resultIntent = packageManager.getLaunchIntentForPackage(_context.getPackageName());
        
        //TODO change if want to go to a specific page in PAR Mobile when notification is clicked
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
            _context,
            0,
            resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        );
        notificationBuilder.setContentIntent(resultPendingIntent); 

        NotificationManager notificationManager = (NotificationManager)_context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = notificationBuilder.build();
        notificationManager.notify(0, notification);
    }
}