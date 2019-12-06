package com.krithiha.bfit;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class DisplayManager {
	
	int mobDisplayHeight;
	int mobDisplayWidth;
	
	public DisplayManager(Context con) {
		
		WindowManager wm = (WindowManager) con.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		setMobDisplayWidth(width);
		setMobDisplayHeight(height);
	}

	public int getMobDisplayHeight() {
		return mobDisplayHeight;
	}
	public int getMobDisplayWidth() {
		return mobDisplayWidth;
	}
	 
	public void setMobDisplayHeight(int mobDisplayHeight) {
		this.mobDisplayHeight = mobDisplayHeight;
	}
	public void setMobDisplayWidth(int mobDisplayWidth) {
		this.mobDisplayWidth = mobDisplayWidth;
	}
	
}
