package com.dabaichat.testinternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class testinternet {

	
	public boolean isNetworkConnected(Context context) { 
		if (context != null) { 
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
		.getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
		if (mNetworkInfo != null) { 
		return mNetworkInfo.isAvailable(); 
		} 
		} 
		return false; 
		}
	
	
	
	public boolean isWifiConnected(Context context) { 
		if (context != null) { 
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
		.getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo mWiFiNetworkInfo = mConnectivityManager 
		.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
		if (mWiFiNetworkInfo != null) { 
		return mWiFiNetworkInfo.isAvailable(); 
		} 
		} 
		return false; 
		}
	
	
	

	
	
}
