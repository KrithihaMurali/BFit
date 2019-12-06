package com.krithiha.bfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by Krithiha on 3/28/2018.
 */

public class Splash extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SaveSharedPreference.getRemind(getApplicationContext()).equals("yes"))
                {
                    Intent i=new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i2=new Intent(Splash.this,Login.class);
                    startActivity(i2);
                    finish();
                }
            }
        },1000);
    }
}
