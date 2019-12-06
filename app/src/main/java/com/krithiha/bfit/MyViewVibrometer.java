package com.krithiha.bfit;
//schonweiss


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.View;


public class MyViewVibrometer extends View {

	public float x = 100;
	public   float y = 5;
	public float z = 5;
	float lineX = 0;
	float lineY = 0;

	Paint paintBlue, paintBlack, paintGray, paintDKGray, paintLTGray;

	boolean flagFinish = true;

	int ARRATCOUNT ;

	float pointX[];
	float pointY[] ;
	int count = 0;

	Bitmap bitmapPin;

	public MyViewVibrometer(Context context) {
		super(context);

		Display disp = MainActivity.context.getWindowManager().getDefaultDisplay();

		int h = disp.getHeight();
		int w = disp.getWidth();
		ARRATCOUNT=w;
		pointX = new float[ARRATCOUNT];
		pointY = new float[ARRATCOUNT];
		paintBlue = new Paint();
		paintBlue.setColor(Color.parseColor("#0b2092"));

		paintBlue.setAntiAlias(true);

		paintBlack = new Paint();
		paintBlack.setColor(Color.WHITE);
		// paintBlack.setAntiAlias(true);

		fillArray(pointX);
		fillArray(pointY);
		bitmapPin = BitmapFactory
				.decodeResource(getResources(), R.drawable.sam);

		// bitmapPin=Bitmap.createScaledBitmap(bitmapPin,
		// 5,
		// 5, true);
		
		
	}

	private void fillArray(float arr[]) {
		Display disp = MainActivity.context.getWindowManager().getDefaultDisplay();

		int h = disp.getHeight();
		int w = disp.getWidth();
		for (int i = 0; i < ARRATCOUNT; i++) {
			arr[i] =h / 2;
		}
	}

	public void updateAccelationSquareRoot(float accelationSquareRoot) {

		
		Log.v("LOG", "Log "+"updateAccelationSquareRoot");
		for (int i = 0; i < ARRATCOUNT - 1; i++) {
			pointY[i] = pointY[i + 1];

		}
		Display disp = MainActivity.context.getWindowManager().getDefaultDisplay();

		int h = disp.getHeight();
		int w = disp.getWidth();
	//	Toast.makeText(MainActivity.context, ""+h, 2000).show();
		
		pointY[ARRATCOUNT - 1] = (float) ((h / 5.5)
				+ ((accelationSquareRoot) * (int) (h * .06)));

		invalidate();

	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawColor(Color.TRANSPARENT);

		int tempx = 0;
		Display disp = MainActivity.context.getWindowManager().getDefaultDisplay();

		int h = disp.getHeight();
		int w = disp.getWidth();

		for (int i = 0; i < ARRATCOUNT - 1; i++) {
			canvas.drawLine(tempx, pointY[i], tempx + 1, pointY[i + 1],
					paintBlue);

			tempx++;
		}

		canvas.drawLine(0, 0, 0,h, paintBlack);
		canvas.drawLine(1, 0, 1, h, paintBlack);
		canvas.drawLine(2, 0, 2, h, paintBlack);

		// canvas.drawLine(0, VibrometerUi.mobDisplayHeight / 2,
		// VibrometerUi.mobDisplayWidth,
		// VibrometerUi.mobDisplayHeight / 2, paintBlack);
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * -.10)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * -.10)),
				paintBlack);
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * -.06)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * -.06)),
				paintBlack);
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * -.02)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * -.02)),
				paintBlack);
		
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * .02)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * .02)),
				paintBlack);
		
		
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * .06)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * .06)),
				paintBlack);
		
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * .10)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * .10)),
				paintBlack);
		
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * .14)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * .14)),
				paintBlack);
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * .18)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * .18)),
				paintBlack);
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * .22)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * .22)),
				paintBlack);
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * .26)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * .26)),
				paintBlack);
		canvas.drawLine(
				0,
				(int) (h / 11 + ((h) * .30)),
				RunOrWalk.w,
				(int) (h / 11 + ((h) * .30)),
				paintBlack);
		
		
		
		
		
//		canvas.drawLine(
//				0,
//				(int) (Accelerometer.h / 11 + ((Accelerometer.h) * .6)),
//				Accelerometer.w,
//				(int) (Accelerometer.h / 11 + ((Accelerometer.h) * .6)),
//				paintBlack);

		






	}

}