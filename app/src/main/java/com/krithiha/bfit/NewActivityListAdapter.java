package com.krithiha.bfit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.krithiha.bfit.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewActivityListAdapter extends BaseAdapter {

	ArrayList<NewWorkoutDetailsModel> workoutDetailsModels;
	Context context;

	LayoutInflater inflater;

	public NewActivityListAdapter(
			ArrayList<NewWorkoutDetailsModel> workoutDetailsModels, Context context) {
		// TODO Auto-generated constructor stub

		this.workoutDetailsModels = workoutDetailsModels;
		this.context = context;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return workoutDetailsModels.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return workoutDetailsModels.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view = null;
		if (arg1 == null) {
			view = inflater.inflate(R.layout.workout_details_design, null);
		} else {
			view = arg1;
		}

		TextView activity = (TextView) view.findViewById(R.id.textViewActivity);
		TextView name = (TextView) view.findViewById(R.id.textViewName);
		TextView Calorie = (TextView) view.findViewById(R.id.textViewCalorie);
		TextView textViewDate = (TextView) view.findViewById(R.id.textViewDate);
		TextView textViewStartTime = (TextView) view
				.findViewById(R.id.textViewStartTime);
		TextView textViewendTime = (TextView) view
				.findViewById(R.id.textViewendTime);

		activity.setText(workoutDetailsModels.get(arg0).getActivity());
		name.setText(workoutDetailsModels.get(arg0).getName());
		Calorie.setText(workoutDetailsModels.get(arg0).getCalorie());
		textViewDate.setText(dateFormatDD_MMM_YYYY(workoutDetailsModels.get(
				arg0).getStart_time()));
		textViewStartTime.setText(dateFormatHH_MM_ss(workoutDetailsModels.get(
				arg0).getStart_time()));
		textViewendTime.setText (dateFormatHH_MM_ss(workoutDetailsModels.get(
				arg0).getEnd_time()));

		return view;
	}

	public String dateFormatDD_MMM_YYYY(String dateInString) {
		try {
			Date date = new Date(dateInString);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

			return formatter.format(date);
		} catch (Exception e) {
			return "";
		}

	}

	public String dateFormatHH_MM_ss(String dateInString) {
		try {
			Date date = new Date(dateInString);
			SimpleDateFormat formatter = new SimpleDateFormat("HH:MM:ss");

			return formatter.format(date);
		} catch (Exception e) {
			return "";
		}
	}

}
