package com.krithiha.bfit;

import android.content.Context;
import android.graphics.Typeface;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.Stack;

public class ActivityGraph extends AppCompatActivity implements AccelerometerListener {

	public static String serverIpAddress = "192.168.1.9";
	//GIFView gifView;
	private static Context CONTEXT;
	int tiG = 0;
	double metsG = 0;
	double kcalG = 0;
	int weightG = 50;
	int time_winG = 3;
	public static int lengthFFTG = 256;

	TextView result;
	TextView accxG, accyG, acczG;

	public static int hG, wG;
	MyViewVibrometer myVeiwG;
	private long lastUpdateG;
	RelativeLayout rrG, graphrrG;
	boolean flagDataSend = true;
	String ip[];

	LinearLayout part2G;

	ServerConnection serverObj;

	double[] realArrayXG = new double[lengthFFTG];
	double[] realArrayYG= new double[lengthFFTG];
	double[] realArrayZG = new double[lengthFFTG];
	double[] imagArrayG = new double[lengthFFTG];

	float x_coordinate_G = 0;
	float y_coordinate_G = 0;
	float z_coordinate_G = 0;

	Stack<String> cvsValueG = new Stack<String>();
	Thread tG;

	protected int readG;
	ProgressBar progBarG;
	Spinner spiG;

	private Handler myHandlerG = new Handler();

	private Runnable rG = new Runnable() {

		@Override
		public void run() {

			storeAndCallFFt(x_coordinate_G, y_coordinate_G, z_coordinate_G, tiG);
			tiG++;
			myHandlerG.postDelayed(rG, 20);

		}
	};

    android.support.v7.app.ActionBar action;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


        action=this.getSupportActionBar();
//        action.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().show();


		Log.v("LOG", "Log " + "onCreate :ActivityGraph");
		Display disp = getWindowManager().getDefaultDisplay();

		hG = disp.getHeight();
		wG = disp.getWidth();

		setContentView(R.layout.graph);
		CONTEXT = this;

		myVeiwG = new MyViewVibrometer(this);
		myVeiwG.invalidate();
		rrG = (RelativeLayout) findViewById(R.id.rrG);

		LayoutParams rLParamsG = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		rLParamsG.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);

		rrG.addView(myVeiwG, rLParamsG);

		myHandlerG.removeCallbacks(rG);
		myHandlerG.postDelayed(rG, 20);


		Typeface tfReguler = Typeface.createFromAsset(getAssets(),
				"fonts/LED22.ttf");

		Typeface digital = Typeface.createFromAsset(getAssets(),
				"fonts/Digital.ttf");

		accxG = (TextView) findViewById(R.id.accxG);
		accyG = (TextView) findViewById(R.id.accyG);
		acczG = (TextView) findViewById(R.id.acczG);
//		status = (TextView) findViewById(R.id.status);

//		energy = (TextView) findViewById(R.id.energy_value);

		accxG.setTypeface(digital);
		accyG.setTypeface(digital);
		acczG.setTypeface(digital);

		graphrrG = (RelativeLayout) findViewById(R.id.graphrrG);

		graphrrG.getLayoutParams().height = (int) (hG * .15);
		graphrrG.getLayoutParams().width = (int) (wG - 5);

		part2G = (LinearLayout) findViewById(R.id.part2G);

		RelativeLayout accxyzG = (RelativeLayout) findViewById(R.id.accxyzG);
		accxyzG.getLayoutParams().height = (int) (hG * .40);

		AsynTaskForCreateCSV csvCrtObj = new AsynTaskForCreateCSV();
		csvCrtObj.execute("");
	}

	

	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	/*public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return super.onMenuItemSelected(featureId, item);
	}
*/






	@Override
	protected void onStop() {

		super.onStop();
		myHandlerG.removeCallbacks(rG);
	}

	protected void onResume() {
		super.onResume();
		Log.v("LOG", "Log "+"onResume");
		if (AccelerometerManager.isSupported()) {
			AccelerometerManager.startListening(this);
			
		}

	}

	protected void onDestroy() {
		super.onDestroy();
		Log.v("LOG", "Log "+"onDestroy");
		flagDataSend = false;
		if (AccelerometerManager.isListening()) {
			AccelerometerManager.stopListening();
		}

	}

	public static Context getContext() {
		return CONTEXT;
	}

	/**
	 * onShake callback
	 */
	public void onShake(float force) {
	}

	/**
	 * onAccelerationChanged callback
	 */
	public synchronized void onAccelerationChanged(float x, float y, float z) {

		x_coordinate_G = x;
		y_coordinate_G = y;
		z_coordinate_G = z;

		try {
			accxG.setText("X: " + (x + "").substring(0, 4) + " ");
			accyG.setText("Y: " + (y + "").substring(0, 4) + " ");
			acczG.setText("Z: " + (z + "").substring(0, 4) + " ");
		} catch (Exception e) {
			// TODO: handle exception
		}

		float accelationSquareRoot = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

		if (((int) accelationSquareRoot) % 2 == 0) {

			myVeiwG.updateAccelationSquareRoot(-(accelationSquareRoot - 1));
		} else {

			myVeiwG.updateAccelationSquareRoot((accelationSquareRoot - 1));
		}

		long actualTime = System.currentTimeMillis();
		if (accelationSquareRoot >= 2) //
		{
			if (actualTime - lastUpdateG < 200) {
				return;
			}

			lastUpdateG = actualTime;

		}

	}

	/**
	 * <p>
	 * This is the method for .
	 * </p>
	 * 
	 * @param xG
	 * @param yG
	 * @param zG
	 * @param iG
	 */

	private void storeAndCallFFt(float xG, float yG, float zG, int iG) {
		if (tiG++ >= lengthFFTG) {
			ResultObj maxPosXG = new ResultObj();
			ResultObj maxPosYG = new ResultObj();
			ResultObj maxPosZG = new ResultObj();
			maxPosXG = FastFourierTransform.fastFT(realArrayXG, imagArrayG, true);
			maxPosYG = FastFourierTransform.fastFT(realArrayYG, imagArrayG, true);
			maxPosZG = FastFourierTransform.fastFT(realArrayZG, imagArrayG, true);
			tiG = 0;
			setResultToView(maxPosXG, maxPosYG, maxPosZG);
		} else {
			realArrayXG[iG] = xG;
			realArrayYG[iG] = yG;
			realArrayZG[iG] = zG;
			imagArrayG[iG] = 0;
		}

	}

	private void setResultToView(ResultObj maxPosXG, ResultObj maxPosYG,
			ResultObj maxPosZG) {
		int maxPosOfXG, maxPosOfYG, maxPosOfZG, temp1G, temp2G, maxPosG;
		double maxAMPxG, maxAMPyG, maxAMPzG, maxAMPG;
		Boolean xflagG = false, yflagG = false, zflagG = false;

		maxPosOfXG = maxPosXG.getResultPos();
		maxPosOfYG = maxPosYG.getResultPos();
		maxPosOfZG = maxPosZG.getResultPos();
		maxAMPxG = maxPosXG.getResultAmp();
		maxAMPyG = maxPosYG.getResultAmp();
		maxAMPzG = maxPosZG.getResultAmp();

		temp1G = Double.compare(maxAMPxG, maxAMPyG);

		if (temp1G > 0) {
			temp2G = Double.compare(maxAMPxG, maxAMPzG);

			if (temp2G > 0) {
				if (Double.compare(maxAMPxG, 0.1) > 0) {
					if (maxPosOfXG > 4) {
//						gifView.setGIFResource(R.drawable.run);
//						status.setText("Running");
						metsG = 0.093 * (maxPosOfXG * 0.5 * 60) - 4.7;
					} else {
//						gifView.setGIFResource(R.drawable.walk);
//						status.setText("Walking");
						metsG = (0.0272 * (maxPosOfXG * 0.5 * 60)) + 1.2;
					}
				} else {
//					gifView.setGIFResource(R.drawable.idel3);
//					status.setText("Idle");
					metsG = 2;
				}	

				kcalG += 1.05 * metsG * weightG * time_winG / (60 * 60);

//				energy.setText("" + (int) kcal);

				return;
			} else {
				temp2G = Double.compare(maxAMPyG, maxAMPzG);
				if (temp2G > 0) {
					if (Double.compare(maxAMPyG, 0.1) > 0) {
						if (maxPosOfYG > 4) {
//							gifView.setGIFResource(R.drawable.run);
//							status.setText("Running");
							metsG = 0.093 * (maxPosOfYG * 0.5 * 60) - 4.7;
						} else {
//							gifView.setGIFResource(R.drawable.walk);
//							status.setText("Walking");
							metsG = (0.0272 * (maxPosOfYG * 0.5 * 60)) + 1.2;
						}
					} else {
//						gifView.setGIFResource(R.drawable.idel3);
//						status.setText("Idle");
						metsG = 2;
					}

					kcalG += 1.05 * metsG * weightG * time_winG / (60 * 60);

//					energy.setText("" + (int) kcal);

					return;
				}
			}
		}

		if (Double.compare(maxAMPzG, 0.1) > 0) {
			if (maxPosOfZG > 4) {
//				gifView.setGIFResource(R.drawable.run);
//				status.setText("Running");
				metsG = 0.093 * (maxPosOfZG * 0.5 * 60) - 4.7;
			} else {
//				gifView.setGIFResource(R.drawable.walk);
//				status.setText("Walking");
				metsG = (0.0272 * (maxPosOfZG * 0.5 * 60)) + 1.2;
			}
		} else {
//			gifView.setGIFResource(R.drawable.idel3);
//			status.setText("Idle");
			metsG = 2;
		}

		kcalG += 1.05 * metsG * weightG * time_winG / (60 * 60);

//		energy.setText("" + (int) kcal);

		return;

	}

    public class AsynTaskForSendData extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			while (flagDataSend) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress("");
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			Date beginupd = new Date();
			long millisecond = beginupd.getTime();
			
			String strVal= x_coordinate_G + "," + y_coordinate_G + ","+ z_coordinate_G+","+millisecond;
			//cvsValue.push(strVal);
			serverObj.sendDate(strVal);
			super.onProgressUpdate(values);
		}
	}
	public class AsynTaskForCreateCSV extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			while (flagDataSend) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress("");
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			Date beginupd = new Date();
			long millisecond = beginupd.getTime();
			
			String strVal= x_coordinate_G + "," + y_coordinate_G + ","+ z_coordinate_G+","+millisecond;
			cvsValueG.push(strVal);
			//Log.v("LOG", "Pushing data in to stack");
			//serverObj.sendDate(strVal);
			super.onProgressUpdate(values);
		}
	}
}
