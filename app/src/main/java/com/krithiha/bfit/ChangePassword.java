package com.krithiha.bfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Krithiha on 4/7/2018.
 */

public class ChangePassword extends Activity {

    EditText edt_change_old,edt_change_new,edt_change_con;
    Button btn_change;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);

        edt_change_old=(EditText)findViewById(R.id.edt_change_old);
        edt_change_new=(EditText)findViewById(R.id.edt_change_new);
        edt_change_con=(EditText)findViewById(R.id.edt_change_con);
        btn_change=(Button) findViewById(R.id.btn_change);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edt_change_old.getText().toString().equals(""))
                {
                    if (!edt_change_new.getText().toString().equals(""))
                    {
                        if (!edt_change_con.getText().toString().equals(""))
                        {
                            if (edt_change_new.getText().toString().equals(edt_change_con.getText().toString()))
                            {
                                SaveSharedPreference.setPassword(getApplicationContext(),edt_change_new.getText().toString());
                                Toast.makeText(ChangePassword.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                Intent i_lo=new Intent(getApplicationContext(),Login.class);
                                startActivity(i_lo);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(ChangePassword.this, "password doesn't match", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            edt_change_con.setError("Enter confirm password");
                        }
                    }
                    else
                    {
                        edt_change_new.setError("Enter new password");
                    }
                }
                else
                {
                    edt_change_old.setError("Enter old password");
                }

            }
        });
    }
}
