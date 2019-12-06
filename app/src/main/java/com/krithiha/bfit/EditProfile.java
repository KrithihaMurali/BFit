package com.krithiha.bfit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.andexert.library.RippleView;

/**
 * Created by Krithiha on 2/8/2018.
 */

public class EditProfile extends AppCompatActivity {

    EditText edt_edit_first,edt_edit_sur,edt_edit_address,edt_edit_phone;
    Button btn_edit_edit;
    RippleView rippleview_edit;
    LinearLayout linearlayout_editprofile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        edt_edit_first=(EditText)findViewById(R.id.edt_edit_first);
        edt_edit_sur=(EditText)findViewById(R.id.edt_edit_sur);
        edt_edit_address=(EditText)findViewById(R.id.edt_edit_address);
        edt_edit_phone=(EditText)findViewById(R.id.edt_edit_phone);
        btn_edit_edit=(Button)findViewById(R.id.btn_edit_edit);
        rippleview_edit=(RippleView)findViewById(R.id.rippleview_edit);
        linearlayout_editprofile=(LinearLayout)findViewById(R.id.linearlayout_editprofile);

        edt_edit_first.setText(SaveSharedPreference.getFirstName(getApplicationContext()));
        edt_edit_sur.setText(SaveSharedPreference.getSurName(getApplicationContext()));
        edt_edit_address.setText(SaveSharedPreference.getAddress(getApplicationContext()));
        edt_edit_phone.setText(SaveSharedPreference.getPhoneNumber(getApplicationContext()));


        rippleview_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveSharedPreference.setFirstName(getApplicationContext(),edt_edit_first.getText().toString());
                SaveSharedPreference.setSurName(getApplicationContext(),edt_edit_sur.getText().toString());
                SaveSharedPreference.setAddress(getApplicationContext(),edt_edit_address.getText().toString());
                SaveSharedPreference.setPhoneNumber(getApplicationContext(),edt_edit_phone.getText().toString());

                /*Snackbar snackbar1 = Snackbar.make(linearlayout_editprofile, "Edited Successfully", Snackbar.LENGTH_LONG);
                snackbar1.show();*/
            }
        });
        /*Snackbar snackbar2 = Snackbar.make(linearlayout_editprofile, "Edition is not successful", Snackbar.LENGTH_LONG);
        snackbar2.show();*/
    }
}
