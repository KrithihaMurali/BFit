package com.krithiha.bfit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


/**
 * Created by Krithiha on 2/8/2018.
 */

public class Profile extends AppCompatActivity {

    EditText edt_profile_first,edt_profile_sur,edt_profile_address,edt_profile_phone;
    String fname,sname,address,phone;
    android.support.v7.app.ActionBar action;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        action=this.getSupportActionBar();
//        action.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().show();

        edt_profile_first=(EditText)findViewById(R.id.edt_profile_first);
        edt_profile_sur=(EditText)findViewById(R.id.edt_profile_sur);
        edt_profile_address=(EditText)findViewById(R.id.edt_profile_address);
        edt_profile_phone=(EditText)findViewById(R.id.edt_profile_phone);

        fname=SaveSharedPreference.getFirstName(getApplicationContext());
        sname=SaveSharedPreference.getSurName(getApplicationContext());
        address=SaveSharedPreference.getAddress(getApplicationContext());
        phone=SaveSharedPreference.getPhoneNumber(getApplicationContext());

        edt_profile_first.setText(fname);
        edt_profile_sur.setText(sname);
        edt_profile_address.setText(address);
        edt_profile_phone.setText(phone);
    }


}
