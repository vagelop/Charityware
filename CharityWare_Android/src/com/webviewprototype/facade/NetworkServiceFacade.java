package com.webviewprototype.facade;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.net.NetworkInfo;


public interface NetworkServiceFacade {

	public  boolean hasActiveInternetConnection(NetworkInfo activeNetworkInfo,Activity list);	
	public void poll(Activity act);
	
}
