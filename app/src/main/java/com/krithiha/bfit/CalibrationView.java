package com.krithiha.bfit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CalibrationView extends View {

	public float x = 100;
	public float y = 5;
	Bitmap bitmap;
	Paint paintBlue, paintBlack, paintGray, paintDKGray, paintLTGray;

	boolean flagFinish = true;

	Context context;
	DisplayManager displayManager;

	Paint red;
	Paint green;

	public CalibrationView(Context context) {
		super(context);
		this.context = context;
		paintBlue = new Paint();
		paintBlue.setColor(Color.BLUE);

		paintBlue.setAntiAlias(true);

		paintBlack = new Paint();
		paintBlack.setColor(Color.BLACK);
		paintBlack.setAntiAlias(true);

		red = new Paint();
		red.setColor(Color.RED);
		red.setAntiAlias(true);

		green = new Paint();
		green.setColor(Color.GREEN);
		green.setAntiAlias(true);

	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.TRANSPARENT);
		float angle = (((y + 10)) * 9) + 180;

		displayManager = new DisplayManager(context);

		float widthMiddle = displayManager.getMobDisplayWidth() / 2;

		float radius = widthMiddle;

		createPoints(canvas, angle, widthMiddle, radius);

		if (11 - (Math.abs(y)) > 2) {
			canvas.drawCircle(widthMiddle, 0, 20, red);
		} else {
			canvas.drawCircle(widthMiddle, 0, 20, green);
		}

	}

	public void createPoints(Canvas canvas, float angle, float widthMiddle,
			float radius) {

		float toX = (float) (widthMiddle + (radius * Math
				.cos((angle * (3.14 / 180)))));

		float toY = (float) (0 - (radius * Math.sin((angle * (3.14 / 180)))));

		float toX5M = (float) (widthMiddle + (5 * Math
				.cos(((angle - 90) * (3.14 / 180)))));
		float toY5M = (float) (0 - (5 * Math.sin(((angle - 90) * (3.14 / 180)))));

		float toX5P = (float) (widthMiddle + (5 * Math
				.cos(((angle + 90) * (3.14 / 180)))));
		float toY5P = (float) (0 - (5 * Math.sin(((angle + 90) * (3.14 / 180)))));

		float toX2M = (float) (widthMiddle + (2 * Math
				.cos(((angle - 90) * (3.14 / 180)))));
		float toY2M = (float) (0 - (2 * Math.sin(((angle - 90) * (3.14 / 180)))));

		float toX2P = (float) (widthMiddle + (2 * Math
				.cos(((angle + 90) * (3.14 / 180)))));
		float toY2P = (float) (0 - (2 * Math.sin(((angle + 90) * (3.14 / 180)))));

		float toX1M = (float) (widthMiddle + (1 * Math
				.cos(((angle - 90) * (3.14 / 180)))));
		float toY1M = (float) (0 - (1 * Math.sin(((angle - 90) * (3.14 / 180)))));

		float toX1P = (float) (widthMiddle + (1 * Math
				.cos(((angle + 90) * (3.14 / 180)))));
		float toY1P = (float) (0 - (1 * Math.sin(((angle + 90) * (3.14 / 180)))));

		float toX3M = (float) (widthMiddle + (3 * Math
				.cos(((angle - 90) * (3.14 / 180)))));
		float toY3M = (float) (0 - (3 * Math.sin(((angle - 90) * (3.14 / 180)))));

		float toX3P = (float) (widthMiddle + (3 * Math
				.cos(((angle + 90) * (3.14 / 180)))));
		float toY3P = (float) (0 - (3 * Math.sin(((angle + 90) * (3.14 / 180)))));

		float toX4M = (float) (widthMiddle + (4 * Math
				.cos(((angle - 90) * (3.14 / 180)))));
		float toY4M = (float) (0 - (4 * Math.sin(((angle - 90) * (3.14 / 180)))));

		float toX4P = (float) (widthMiddle + (4 * Math
				.cos(((angle + 90) * (3.14 / 180)))));
		float toY4P = (float) (0 - (4 * Math.sin(((angle + 90) * (3.14 / 180)))));

		paintBlack.setColor(Color.DKGRAY);
		dradOnCanvas(canvas, widthMiddle, 0, toX, toY, paintBlack);

		paintBlack.setColor(Color.GRAY);
		dradOnCanvas(canvas, toX5M, toY5M, toX, toY, paintBlack);
		dradOnCanvas(canvas, toX4M, toY4M, toX, toY, paintBlack);
		dradOnCanvas(canvas, toX3M, toY3M, toX, toY, paintBlack);
		dradOnCanvas(canvas, toX2M, toY2M, toX, toY, paintBlack);
		dradOnCanvas(canvas, toX1M, toY1M, toX, toY, paintBlack);

		paintBlack.setColor(Color.LTGRAY);
		dradOnCanvas(canvas, toX5P, toY5P, toX, toY, paintBlack);
		dradOnCanvas(canvas, toX2P, toY2P, toX, toY, paintBlack);
		dradOnCanvas(canvas, toX1P, toY1P, toX, toY, paintBlack);
		dradOnCanvas(canvas, toX3P, toY3P, toX, toY, paintBlack);
		dradOnCanvas(canvas, toX4P, toY4P, toX, toY, paintBlack);

		paintBlack.setColor(Color.BLACK);
		canvas.drawCircle(displayManager.getMobDisplayWidth() / 2, 0, 10,
				paintBlack);

	}

	public void dradOnCanvas(Canvas canvas, float fromX, float fromY,
			float toX, float toY, Paint paint) {
		canvas.drawLine(fromX, fromY, toX, toY, paint);
	}

}