package com.krithiha.bfit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesManager {
	SharedPreferences prefs;
	Context context;

	public PreferencesManager(Context context) {

		this.context = context;

	}
	
	public void setAccelerometerXMaxError(String x)
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("setAccelerometerXError", x); // value to
																	// store
		editor.commit();
	}
	public String getAccelerometerXMaxError()
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("setAccelerometerXError", "0");
	}
	
	
	
	public void setAccelerometerXMinError(String x)
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("setAccelerometerXError", x); // value to
																	// store
		editor.commit();
	}
	public String getAccelerometerXMinError()
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("setAccelerometerXError", "0");
	}
	
	
	
	
	
	public void setAccelerometerYMaxError(String x)
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("setAccelerometerYError", x); // value to
																	// store
		editor.commit();
	}
	public String getAccelerometerYMaxError()
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("setAccelerometerYError", "0");
	}
	
	
	
	public void setAccelerometerYMinError(String x)
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("setAccelerometerYError", x); // value to
																	// store
		editor.commit();
	}
	public String getAccelerometerYMinError()
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("setAccelerometerYError", "0");
	}
	
	
	
	
	
	public void setAccelerometerZMaxError(String x)
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("setAccelerometerZError", x); // value to
																	// store
		editor.commit();
	}
	public String getAccelerometerZMaxError()
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("setAccelerometerZError", "0");
	}
	
	
	public void setAccelerometerZMinError(String x)
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("setAccelerometerZError", x); // value to
																	// store
		editor.commit();
	}
	public String getAccelerometerZMinError()
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("setAccelerometerZError", "0");
	}

	
	
	
	
	
	public void setAccelerometerListenerService(String message) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("AccelerometerListenerService", message); // value to
																	// store
		editor.commit();
	}

	public String getAccelerometerListenerService() {

		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("AccelerometerListenerService", "off");

	}

	public void setActivity(String activity) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("Activity", activity); // value to
												// store
		editor.commit();
	}

	public String getActivity() {

		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("Activity", "");

	}

	public void setActivityStartTime(String activity) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("ActivityTime", activity); // value to
		// store
		editor.commit();
	}

	public String getActivityStartTime() {

		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("ActivityTime", "");

	}
	public String getName() {

		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString("name", "");

	}
	
	

}
