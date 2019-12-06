package com.krithiha.bfit;

import com.krithiha.bfit.R;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class Calibration extends Activity implements SensorEventListener,
		OnTouchListener {
	SensorManager sensorManager;
	Sensor mAccelerometer;
	public static int mobDisplayWidth;
	public static int mobDisplayHeight;
	LinearLayout protractor;
	CalibrationView myVeiw;
	RelativeLayout rr;
	RelativeLayout camaraBtn;
	RelativeLayout white;
	public static Camera mCamera;
	FrameLayout preview;
	Boolean camaraFlag = false;

	int shift = 3;

	public int valueXYZ = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calibration);

		System.out.print("555 Oncreate");

		protractor = (LinearLayout) findViewById(R.id.protractor);

		camaraBtn = (RelativeLayout) findViewById(R.id.camaraBtn);
		camaraBtn.setOnTouchListener(this);
		white = (RelativeLayout) findViewById(R.id.white);

		Display display = getWindowManager().getDefaultDisplay();
		mobDisplayWidth = display.getWidth();
		mobDisplayHeight = display.getHeight();

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		protractor.getLayoutParams().width = mobDisplayWidth;
		protractor.getLayoutParams().height = mobDisplayWidth / 2;

		myVeiw = new CalibrationView(this);
		myVeiw.invalidate();

		rr = (RelativeLayout) findViewById(R.id.rr);

		LayoutParams rLParams = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		rLParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);

		rr.addView(myVeiw, rLParams);

		white.setVisibility(View.VISIBLE);

	}

	View v;

	public void X(View v) {
		if (this.v != null)
			this.v.setEnabled(true);
		this.v = v;
		shift = 0;
		v.setEnabled(false);
	}

	public void Y(View v) {
		if (this.v != null)
			this.v.setEnabled(true);
		this.v = v;
		shift = 1;
		v.setEnabled(false);
	}

	public void Z(View v) {
		if (this.v != null)
			this.v.setEnabled(true);
		this.v = v;
		shift = 2;

		v.setEnabled(false);
	}

	public void MAX(View v) {
		if (11 - (Math.abs(valueXYZ)) > 2) {

			Toast.makeText(this,
					"Calibration Error " + (11 - (Math.abs(valueXYZ))), 2000)
					.show();

		} else {
			Toast.makeText(this, "Calibration Value saved", 2000).show();
		}
	}

	public void MIN(View v) {
		if (11 - (Math.abs(valueXYZ)) > 2) {
			Toast.makeText(this,
					"Calibration Error " + (11 - (Math.abs(valueXYZ))), 2000)
					.show();
		} else {

			if (shift == 0)
			{
				Toast.makeText(this, "Calibration Value saved", 2000).show();
			} else if (shift == 1) {
				Toast.makeText(this, "Calibration Value saved", 2000).show();
			}else if (shift == 2) {
				Toast.makeText(this, "Calibration Value saved", 2000).show();
			}
			
		}
	}

	protected void onDestroy() {
		super.onDestroy();
		sensorManager.unregisterListener(this);
	}

	protected void onPause() {
		super.onPause();

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getAccelerometer(event);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_UI);
	}

	private void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		// float x = values[0];
		float y = 0;
		if (shift < 3)
			y = values[shift];
		// float z = values[2];
		myVeiw.y = (float) y;

		valueXYZ = (int) y;

		// Log.v("Log", "555 :: " + y);

		myVeiw.invalidate();
	}

}
