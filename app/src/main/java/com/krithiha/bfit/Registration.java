package com.krithiha.bfit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andexert.library.RippleView;

/**
 * Created by Krithiha on 2/6/2018.
 */

public class Registration extends Activity {
    EditText edt_regi_first,edt_regi_sur,edt_regi_address,edt_regi_phone,edt_regi_user,edt_regi_pass,edt_regi_con;
    Button btn_regi_regi;
    LinearLayout linearLayout2;
    RippleView rippleview_regi;
    SharedPreferences sharedPreferences;
    ImageButton img_your;

    ImageView img_sample;
    private static final int CAMERA_REQUEST = 123;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "Sample";
    private Uri mImageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        edt_regi_first=(EditText)findViewById(R.id.edt_regi_first);
        edt_regi_sur=(EditText)findViewById(R.id.edt_regi_sur);
        edt_regi_address=(EditText)findViewById(R.id.edt_regi_address);
        edt_regi_phone=(EditText)findViewById(R.id.edt_regi_phone);
        edt_regi_user=(EditText)findViewById(R.id.edt_regi_user);
        edt_regi_pass=(EditText)findViewById(R.id.edt_regi_pass);
        edt_regi_con=(EditText)findViewById(R.id.edt_regi_con);
        btn_regi_regi=(Button)findViewById(R.id.btn_regi_regi);
        linearLayout2=(LinearLayout)findViewById(R.id.linearlayout2);
        rippleview_regi = (RippleView) findViewById(R.id.rippleview_regi);
        img_your=(ImageButton)findViewById(R.id.img_your);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        img_your.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent;
                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                } else {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                }
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
            }
        });

        rippleview_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt_regi_first.getText().toString().equals(""))
                {
                    if(!edt_regi_sur.getText().toString().equals(""))
                    {
                        if (!edt_regi_address.getText().toString().equals(""))
                        {
                            if(!edt_regi_phone.getText().toString().equals(""))
                            {
                                if (!edt_regi_user.getText().toString().equals(""))
                                {
                                    if (!edt_regi_pass.getText().toString().equals(""))
                                    {
                                        if (!edt_regi_con.getText().toString().equals(""))
                                        {
                                            if (edt_regi_pass.getText().toString().equals(edt_regi_con.getText().toString()))
                                            {
                                                Intent i = new Intent(Registration.this,Login.class);
                                                SaveSharedPreference.setFirstName(getApplicationContext(), edt_regi_first.getText().toString());
                                                SaveSharedPreference.setSurName(getApplicationContext(), edt_regi_sur.getText().toString());
                                                SaveSharedPreference.setAddress(getApplicationContext(), edt_regi_address.getText().toString());
                                                SaveSharedPreference.setPhoneNumber(getApplicationContext(), edt_regi_phone.getText().toString());
                                                SaveSharedPreference.setUsername(getApplicationContext(), edt_regi_user.getText().toString());
                                                SaveSharedPreference.setPassword(getApplicationContext(), edt_regi_pass.getText().toString());
                                                SaveSharedPreference.setConfirmPassword(getApplicationContext(), edt_regi_con.getText().toString());
                                               /* Snackbar snackbar = Snackbar.make(linearLayout2, "Registered Successfully", Snackbar.LENGTH_LONG);
                                                snackbar.show();*/
                                                Toast.makeText(Registration.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(i);
                                            }
                                            else
                                            {
                                                /*Snackbar snackbar = Snackbar.make(linearLayout2, "Passwords doesn't match", Snackbar.LENGTH_LONG);
                                                snackbar.show();*/
                                                finish();
                                                Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else
                                        {
                                            edt_regi_con.setError("Enter confirm password");
                                        }
                                    }
                                    else
                                    {
                                        edt_regi_pass.setError("Enter password");
                                    }
                                }
                                else
                                {
                                    edt_regi_user.setError("Enter username");
                                }
                            }
                            else
                            {
                                edt_regi_phone.setError("Enter phone number");
                            }
                        }
                        else
                        {
                            edt_regi_address.setError("Enter address");
                        }
                    }
                    else
                    {
                        edt_regi_sur.setError("Enter surname");
                    }
                }
                else
                {
                    edt_regi_first.setError("Enter first name");
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a image.
                // The Intent's data Uri identifies which item was selected.
                if (data != null) {

                    // This is the key line item, URI specifies the name of the data
                    mImageUri = data.getData();

                    // Removes Uri Permission so that when you restart the device, it will be allowed to reload.
                    this.grantUriPermission(this.getPackageName(), mImageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                    this.getContentResolver().takePersistableUriPermission(mImageUri, takeFlags);

                    // Saves image URI as string to Default Shared Preferences
                    SharedPreferences preferences =
                            PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("image", String.valueOf(mImageUri));
                    editor.commit();

                    img_your.setImageURI(mImageUri);
                    img_your.invalidate();
                }
            }
        }
    }
}
