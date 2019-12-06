package com.krithiha.bfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Krithiha on 2/9/2018.
 */

public class Settings_ListView extends AppCompatActivity {
    LinearLayout linear_settings_profile,linear_settings_edit,linear_settings_work,linear_settings_cali;
    android.support.v7.app.ActionBar action;
    ImageView img_set_pro;
    TextView txt_pro_name;
    String name;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_listview1);

        action=this.getSupportActionBar();
//        action.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().show();

        linear_settings_profile=(LinearLayout)findViewById(R.id.linear_settings_profile);
        linear_settings_edit=(LinearLayout)findViewById(R.id.linear_settings_edit);
        linear_settings_work=(LinearLayout)findViewById(R.id.linear_settings_work);
        linear_settings_cali=(LinearLayout)findViewById(R.id.linear_settings_cali);
        img_set_pro=(ImageView)findViewById(R.id.img_set_pro);
        txt_pro_name=(TextView)findViewById(R.id.txt_pro_name);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String mImageUri = preferences.getString("image", null);
        name=SaveSharedPreference.getFirstName(getApplicationContext());
        txt_pro_name.setText(name);


        if (mImageUri!=null) {
            img_set_pro.setImageURI(Uri.parse(mImageUri));
        }
        linear_settings_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iprofile=new Intent(Settings_ListView.this,Profile.class);
                startActivity(iprofile);
            }
        });

        linear_settings_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iedit=new Intent(Settings_ListView.this,EditProfile.class);
                startActivity(iedit);
            }
        });
        linear_settings_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_workout=new Intent(Settings_ListView.this,WorkoutDetails.class);
                startActivity(i_workout);
            }
        });
        linear_settings_cali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_workout=new Intent(Settings_ListView.this,Calibration.class);
                startActivity(i_workout);
            }
        });


    }
}
