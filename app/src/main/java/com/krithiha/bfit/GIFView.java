package com.krithiha.bfit;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;

public class GIFView extends View {
	private Movie mMovie;
	long movieStart;
	private int gifId;
	public GIFView(Context context) {
		super(context);
	}

	public GIFView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GIFView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public void initializeView() {
		if (gifId != 0) {
	    //R.drawable.loader - our animated GIF
	    InputStream is = getContext().getResources().openRawResource(gifId);
	    mMovie = Movie.decodeStream(is);
	    movieStart = 0;
	    this.invalidate();
//	    AsymTaskForClock o = new AsymTaskForClock();
//        o.execute("");
	    
	   // Log.v("Log", "LOG555"+" AsymTaskForClock");
	   }
	}
	protected void onDraw(Canvas canvas) {
	    canvas.drawColor(Color.TRANSPARENT);
	   
	    super.onDraw(canvas);
	    long now = android.os.SystemClock.uptimeMillis();
	    if (movieStart == 0) {
	        movieStart = now;
	    }
	    if (mMovie != null) {
	        int relTime = (int) ((now - movieStart) % mMovie.duration());
	        mMovie.setTime(relTime);
	        mMovie.draw(canvas, getWidth() - mMovie.width(), getHeight() - mMovie.height());
	        this.invalidate();
	        
	    }
	}
	

	public void setGIFResource(int resId) {
	    this.gifId = resId;
	    initializeView();
	}

	public int getGIFResource() {
	    return this.gifId;
	}
	private class AsymTaskForClock extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			while(true){
							publishProgress(" " );
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			}


			//return null;	
		}

		protected void onProgressUpdate(String... value) {
			 invalidate();
		}

	}

//	private void initializeView() {
//	    if (gifId != 0) {
//	        InputStream is = getContext().getResources().openRawResource(gifId);
//	        movie = Movie.decodeStream(is);
//	        movieStart = 0;
//	        this.invalidate();
//	    }
//	}
}
