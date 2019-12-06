package com.krithiha.bfit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Krithiha on 3/9/2018.
 */

public class Dairy extends AppCompatActivity {

    EditText edt_dairy_name,edt_dairy_age,edt_dairy_hgt,edt_dairy_wt,edt_dairy_gender;
    Button btn_dairy_save;
    android.support.v7.app.ActionBar action;
    Spinner spinner1;
    SharedPreferences mySharedPreferences;
    final String MYPREFS = "MyPreferences_001";
    SharedPreferences.Editor myEditor;
    TextView txt_spn_dairy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dairy);

        action=this.getSupportActionBar();
//        action.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().show();

        edt_dairy_name=(EditText)findViewById(R.id.edt_dairy_name);
        edt_dairy_age=(EditText)findViewById(R.id.edt_dairy_age);
        edt_dairy_hgt=(EditText)findViewById(R.id.edt_dairy_hgt);
        edt_dairy_wt=(EditText)findViewById(R.id.edt_dairy_wt);

        btn_dairy_save=(Button)findViewById(R.id.btn_dairy_save);

        txt_spn_dairy=(TextView)findViewById(R.id.txt_spn_dairy);


       // edt_dairy_name.setText(SaveSharedPreference.getFirstName(getApplicationContext()));

        String gender[] = { "Gender","Male", "Female","Others" };
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.textview_spinner_design, R.id.txt_spn_dairy, gender);
        /*spinnerArrayAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        spinner1.setAdapter(spinnerArrayAdapter);


        mySharedPreferences = getSharedPreferences(MYPREFS, 0);
        myEditor = mySharedPreferences.edit();

        if (mySharedPreferences != null) {

          /*  edt_dairy_name.setText(mySharedPreferences.getString("name", "Sukanya"));
            edt_dairy_age.setText(mySharedPreferences.getString("age", "23"));
            edt_dairy_hgt.setText(mySharedPreferences.getString("height", "175"));
            edt_dairy_wt.setText(mySharedPreferences.getString("weight", "74"));*/

            if ("Male".equals(mySharedPreferences.getString("gender", "74"))) {
                spinner1.setSelection(0);

            } else if ("Female".equals(mySharedPreferences.getString("gender","74"))){
                spinner1.setSelection(1);
            }
            else
            {
                spinner1.setSelection(2);
            }



        } else {
            Toast.makeText(getApplicationContext(), "No Data found", Toast.LENGTH_SHORT).show();
        }

        edt_dairy_name.setHint("Enter your name");
        edt_dairy_age.setHint("Enter your age");
        edt_dairy_hgt.setHint("Weight in kilograms");
        edt_dairy_wt.setHint("Height in centimeters");

        btn_dairy_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edt_dairy_name.getText().toString().equals("")) {
                    if (!edt_dairy_age.getText().toString().equals("")) {
                        if (!edt_dairy_wt.getText().toString().equals("")) {
                            if (!edt_dairy_hgt.getText().toString().equals("")) {
                                if (v.getId() == R.id.btn_dairy_save) {
                                    myEditor.putString("name", edt_dairy_name.getText().toString() + "");
                                    myEditor.putString("age", edt_dairy_age.getText().toString() + "");
                                    myEditor.putString("weight", edt_dairy_wt.getText().toString() + "");
                                    myEditor.putString("height", edt_dairy_hgt.getText().toString() + "");
                                    myEditor.putString("gender", spinner1.getSelectedItem().toString());

                                    myEditor.commit();
                                    finish();

                                }

                            }
                            else {
                                edt_dairy_wt.setError("Enter your height");
                            }
                        }
                        else {
                            edt_dairy_hgt.setError("Enter yuor weight");
                        }
                    }
                    else {
                        edt_dairy_age.setError("Enter your age");
                    }
                    }
                else {
                    edt_dairy_name.setError("Enter your Name");
                }
            }
        });

    }
    }

