package com.webviewprototype.facade.impl;

import java.io.IOException; 
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;

import com.webviewprototype.CharityActivity;
import com.webviewprototype.facade.NetworkServiceFacade;

public class NetworkFacadeImpl implements NetworkServiceFacade {

	private static final String LOG_TAG = null;
	private NetworkInfo info;
	private CharityActivity activity;

	public NetworkFacadeImpl() {
	}
	
	@SuppressLint("NewApi")
	public  boolean hasActiveInternetConnection(NetworkInfo activeNetworkInfo,Activity act) {
		info=activeNetworkInfo;
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		if (activeNetworkInfo != null) {
	        try {
	            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
	            urlc.setRequestProperty("User-Agent", "Test");
	            urlc.setRequestProperty("Connection", "close");
	            urlc.setConnectTimeout(1500); 
	            urlc.connect();
	            System.out.println(5);
	            return (urlc.getResponseCode() == 200);
	        } catch (IOException e) {
	        	  System.out.println(6);
	            Log.e(LOG_TAG, "Error checking internet connection", e);
	        }
	    } else {
	    	  System.out.println(7);
	        Log.d(LOG_TAG, "No network available!");
	    }
	    return false;
	}

	private void attach(Activity act) {
			activity=(CharityActivity) act;
	}
	
	private void detach() {
		activity=null;
	}
	
	public void poll(Activity act) {
		if (activity!=null) {
			if ( act !=activity) {
				detach();
				attach(act);
			}
		}else{
			attach(act);
		}
		while (hasActiveInternetConnection(info,activity)) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		activity.synchronize();
	}
	
	
	
		
}
