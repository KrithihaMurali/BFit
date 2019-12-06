package com.krithiha.bfit;

import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;

import com.krithiha.bfit.SQLiteServices.SQLiteAdapter;

public class DBActivity {
	
	Context context;
	
	public DBActivity(Context context) {							// Getting current context(Constructor)
		this.context=context;
	}
	
	public void saveActivity(String energy)							// To store the energy				
	{
		SharedPreferences mySharedPreferences;

		PreferencesManager preferencesManager = new PreferencesManager(context);
		final String MYPREFS = "MyPreferences_001";
		mySharedPreferences = context.getSharedPreferences(MYPREFS, 0);
	//	mySharedPreferences.getString("name", "Sukanya");
		SQLiteAdapter sqladptr = new SQLiteAdapter(context);
		sqladptr.openToRead();

		String[][] data = {
				{"name",SaveSharedPreference.getFirstName(context)},//To store all the data in array data such as name, activity, start, end and calories
			//	{ "name", mySharedPreferences.getString("name", "Sukanya") },
				{ "activity", preferencesManager.getActivity()},
				{"start_time",preferencesManager.getActivityStartTime()},
				{"end_time",(new Date()+"")},
					{"calorie",energy} };
		sqladptr.insert(data, "activity");						// Inserting the data to array using SQLiteAdapter Class

		sqladptr.close();								// Closing the Database
		
		// for print the value in table activity
		
		SQLiteAdapter sqladptr2 = new SQLiteAdapter(context);				// To retrive data from SQLiteAdapter
		sqladptr2.openToRead();
		String[] columns = { "name", "activity","start_time","end_time","calorie" };

		Cursor cursor = sqladptr2.queueAll("activity", columns, "", null);
		cursor.getCount();
		Log.v("Curser count", "" + cursor.getCount());

		cursor.moveToPosition(0);

		while (cursor.isAfterLast() == false) {						// Printing the values in console 
			Log.e("DB", "555 name :" + cursor.getString(0));
			Log.e("DB", "555 Activiy :" + cursor.getString(1));
			Log.e("DB", "555 start_time :" + cursor.getString(2));
			Log.e("DB", "555 end_time :" + cursor.getString(3));
			Log.e("DB", "555 calorie :" + cursor.getString(4));
			Log.e("DB", "555 ------ :" + "--------------");

			

			cursor.moveToNext();

		}
		sqladptr2.close();
	}

}
