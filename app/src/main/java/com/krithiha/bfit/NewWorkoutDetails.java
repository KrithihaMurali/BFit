package com.krithiha.bfit;

import java.util.ArrayList;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.krithiha.bfit.SQLiteServices.SQLiteAdapter;

public class NewWorkoutDetails extends Activity {
	
	GridView gridView1;
	ArrayList<NewWorkoutDetailsModel> workoutDetailsModels;
	int totalCalorie=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newworkoutdetails);
		workoutDetailsModels = new ArrayList<NewWorkoutDetailsModel>();
		gridView1 = (GridView) findViewById(R.id.gridView1);
		
		
		
		
		
		SQLiteAdapter sqladptr2 = new SQLiteAdapter(this);
		sqladptr2.openToRead();
		String[] columns = { "name", "activity","start_time","end_time","calorie" };

		Cursor cursor = sqladptr2.queueAll("activity", columns, "", null);
		cursor.getCount();
		Log.v("Curser count", "" + cursor.getCount());

		cursor.moveToPosition(0);

		while (cursor.isAfterLast() == false) {
			Log.e("DB", "555 name :" + cursor.getString(0));
			Log.e("DB", "555 Activiy :" + cursor.getString(1));
			Log.e("DB", "555 start_time :" + cursor.getString(2));
			Log.e("DB", "555 end_time :" + cursor.getString(3));
			Log.e("DB", "555 calorie :" + cursor.getString(4));
			Log.e("DB", "555 ------ :" + "--------------");

			NewWorkoutDetailsModel detailsModel = new NewWorkoutDetailsModel();
			
			detailsModel.setName(cursor.getString(0));
			detailsModel.setActivity(cursor.getString(1));
			detailsModel.setStart_time(cursor.getString(2));
			detailsModel.setEnd_time(cursor.getString(3));
			detailsModel.setCalorie(cursor.getString(4));
			//detailsModel.setActivity(cursor.getString(5));
			totalCalorie=totalCalorie+Integer.parseInt(detailsModel.getCalorie());
			workoutDetailsModels.add(detailsModel);

			cursor.moveToNext();

		}
		NewWorkoutDetailsModel detailsModel = new NewWorkoutDetailsModel();
		
		detailsModel.setName("");
		detailsModel.setActivity("Total Calorie :");
		detailsModel.setStart_time("");
		detailsModel.setEnd_time("");
		detailsModel.setCalorie(totalCalorie+"");
		
		
		workoutDetailsModels.add(detailsModel);
		
		
		sqladptr2.close();
		gridView1.setAdapter(new NewActivityListAdapter(workoutDetailsModels,NewWorkoutDetails.this));
	}

}
