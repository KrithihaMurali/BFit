package com.krithiha.bfit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;

/**
 * Created by Krithiha on 2/6/2018.
 */

public class Login extends Activity {
    EditText edt_login_user,edt_login_pass;
    Button btn_login_login;
    TextView txt_login_create,txt_login_forgot;
    String user,pass;
    LinearLayout linearLayout;
    RippleView rippleview;
    ImageView img_login_your;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        edt_login_user=(EditText)findViewById(R.id.edt_login_user);
        edt_login_pass=(EditText)findViewById(R.id.edt_login_pass);
        btn_login_login=(Button)findViewById(R.id.btn_login_login);
        txt_login_create=(TextView)findViewById(R.id.txt_login_create);
        txt_login_forgot=(TextView)findViewById(R.id.txt_login_forgot);
        linearLayout=(LinearLayout)findViewById(R.id.linearlayout);
        //rippleview = (RippleView) findViewById(R.id.rippleview);
        img_login_your=(ImageView)findViewById(R.id.img_login_your);
        user=SaveSharedPreference.getUsername(getApplicationContext());
        pass=SaveSharedPreference.getPassword(getApplicationContext());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String mImageUri = preferences.getString("image", null);

        if (mImageUri!=null) {
            Log.e("Image","view"+mImageUri);
        img_login_your.setImageURI(Uri.parse(mImageUri));
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT).show();
        }

              btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!edt_login_user.getText().toString().equals(""))
                {
                    if(!edt_login_pass.getText().toString().equals(""))
                    {
                        if (edt_login_user.getText().toString().equals(user) && edt_login_pass.getText().toString().equals(pass))
                        {
                            Intent i_login=new Intent(getApplicationContext(),MainActivity.class);
                            SaveSharedPreference.setRemind(getApplicationContext(), "yes");
                            /*Snackbar snackbar = Snackbar.make(linearLayout, "Login Successfully", Snackbar.LENGTH_LONG);
                            snackbar.show();*/
                            Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(i_login);
                        }
                        else
                        {
                           /* Snackbar snackbar = Snackbar.make(linearLayout, "Login Failed", Snackbar.LENGTH_LONG);
                            snackbar.show();*/
                            Toast.makeText(Login.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        edt_login_pass.setError("Enter Password");
                    }
                }
                else
                {
                    edt_login_user.setError("Enter username");
                }
            }
        });

        txt_login_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Login.this,Registration.class);
                finish();
                startActivity(i);
            }
        });
        txt_login_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i_forgot=new Intent(Login.this,ChangePassword.class);
                finish();
                startActivity(i_forgot);
            }
        });
    }
}