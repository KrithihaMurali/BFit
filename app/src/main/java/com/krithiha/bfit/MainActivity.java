package com.krithiha.bfit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.krithiha.bfit.SQLiteServices.DatabaseHelper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    LinearLayout linear_run,linear_sit,linear_push,linear_others;
    public static Activity context;
    static Activity activity;
    TextView txt_g_run,txt_g_sit,txt_g_push;
    Dialog dia, dia_run,dia_sit,dia_push;



    private LinearLayout linear_map,linear_graph,linear_dairy,linear_competition;
    private ImageButton img_map,img_graph,img_dairy,img_competition;
    private TextView tv_map,tv_graph,tv_dairy,tv_competition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear_run=(LinearLayout) findViewById(R.id.linear_run);
        linear_sit=(LinearLayout) findViewById(R.id.linear_sit);
        linear_push=(LinearLayout) findViewById(R.id.linear_push);

        txt_g_run=(TextView)findViewById(R.id.txt_g_run);
        txt_g_sit=(TextView)findViewById(R.id.txt_g_sit);
        txt_g_push=(TextView)findViewById(R.id.txt_g_push);

        activity=MainActivity.this;
        context=MainActivity.this;

        linear_map=(LinearLayout)findViewById(R.id.linear_map);
        linear_graph=(LinearLayout)findViewById(R.id.linear_graph);
        linear_dairy=(LinearLayout)findViewById(R.id.linear_dairy);
        linear_competition=(LinearLayout)findViewById(R.id.linear_competition);

        img_map=(ImageButton)findViewById(R.id.img_map);
        img_graph=(ImageButton)findViewById(R.id.img_graph);
        img_dairy=(ImageButton)findViewById(R.id.img_diary);
        img_competition=(ImageButton)findViewById(R.id.img_competition);

        tv_map=(TextView)findViewById(R.id.tv_map);
        tv_graph=(TextView)findViewById(R.id.tv_graph);
        tv_dairy=(TextView)findViewById(R.id.tv_diary);
        tv_competition=(TextView)findViewById(R.id.tv_competition);



        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        try
        {
            databaseHelper.createDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        linear_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i_run=new Intent(getApplicationContext(),RunOrWalk.class);
                startActivity(i_run);
            }
        });

        linear_sit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i_sit=new Intent(getApplicationContext(),SitUp.class);
                startActivity(i_sit);
            }
        });

        linear_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i_push=new Intent(getApplicationContext(),PushUp.class);
                startActivity(i_push);
            }
        });


        txt_g_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dia_run=new Dialog(MainActivity.this);
                dia_run.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dia_run.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dia_run.setContentView(R.layout.guide_alert_run);
                ImageView image_v_run;
                image_v_run=(ImageView)dia_run.findViewById(R.id.image_v_run);
                TextView txt_guide_close_run=(TextView)dia_run.findViewById(R.id.txt_guide_close_run);
                txt_guide_close_run.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      dia_run.dismiss();
                    }

                });
                image_v_run.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i_img_run=new Intent(getApplicationContext(),RunWalkYoutubeLink.class);
                        startActivity(i_img_run);
                    }
                });
                dia_run.show();
                dia_run.setCanceledOnTouchOutside(false);


            }
        });

        txt_g_sit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dia_sit=new Dialog(MainActivity.this);
                dia_sit.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dia_sit.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dia_sit.setContentView(R.layout.guide_alert_situp);
                TextView txt_guide_close_sit=(TextView)dia_sit.findViewById(R.id.txt_guide_close_sit);
                txt_guide_close_sit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dia_sit.dismiss();
                    }

                });

                ImageView image_v_sit;
                image_v_sit=(ImageView)dia_sit.findViewById(R.id.image_v_sit);
                image_v_sit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i_img_run=new Intent(getApplicationContext(),SitUpYoutubeLink.class);
                        startActivity(i_img_run);
                    }
                });
                dia_sit.show();
                dia_sit.setCanceledOnTouchOutside(false);
            }
        });

        txt_g_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dia_push=new Dialog(MainActivity.this);
                dia_push.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dia_push.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dia_push.setContentView(R.layout.guide_alert_pushup);
                TextView txt_guide_close_push=(TextView)dia_push.findViewById(R.id.txt_guide_close_push);
                txt_guide_close_push.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dia_push.dismiss();
                    }

                });

                ImageView image_v_push;
                image_v_push=(ImageView)dia_push.findViewById(R.id.image_v_push);
                image_v_push.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i_img_run=new Intent(getApplicationContext(),PushUpYoutubeLink.class);
                        startActivity(i_img_run);
                    }
                });
                dia_push.show();
                dia_push.setCanceledOnTouchOutside(false);

            }
        });


        linear_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i_mapp=new Intent(getApplicationContext(),MyActivity.class);

                linear_map.setBackgroundColor(Color.parseColor("#0b2092"));
                img_map.setBackgroundResource(R.drawable.map_white);
                tv_map.setTextColor(Color.WHITE);

                linear_graph.setBackgroundColor(Color.WHITE);
                img_graph.setBackgroundResource(R.drawable.graph_blue);
                tv_graph.setTextColor(Color.parseColor("#0b2092"));

                linear_dairy.setBackgroundColor(Color.WHITE);
                img_dairy.setBackgroundResource(R.drawable.book_blue);
                tv_dairy.setTextColor(Color.parseColor("#0b2092"));

                linear_competition.setBackgroundColor(Color.WHITE);
                img_competition.setBackgroundResource(R.drawable.share);
                tv_competition.setTextColor(Color.parseColor("#0b2092"));

                startActivity(i_mapp);


            }
        });
        img_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_mapp=new Intent(getApplicationContext(),MyActivity.class);

                linear_map.setBackgroundColor(Color.parseColor("#0b2092"));
                img_map.setBackgroundResource(R.drawable.map_white);
                tv_map.setTextColor(Color.WHITE);

                linear_graph.setBackgroundColor(Color.WHITE);
                img_graph.setBackgroundResource(R.drawable.graph_blue);
                tv_graph.setTextColor(Color.parseColor("#0b2092"));

                linear_dairy.setBackgroundColor(Color.WHITE);
                img_dairy.setBackgroundResource(R.drawable.book_blue);
                tv_dairy.setTextColor(Color.parseColor("#0b2092"));

                linear_competition.setBackgroundColor(Color.WHITE);
                img_competition.setBackgroundResource(R.drawable.share);
                tv_competition.setTextColor(Color.parseColor("#0b2092"));

                startActivity(i_mapp);

            }
        });


        linear_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i_graph=new Intent(getApplicationContext(),ActivityGraph.class);

                linear_graph.setBackgroundColor(Color.parseColor("#0b2092"));
                img_graph.setBackgroundResource(R.drawable.graph_white);
                tv_graph.setTextColor(Color.WHITE);

                linear_map.setBackgroundColor(Color.WHITE);
                img_map.setBackgroundResource(R.drawable.map_blue);
                tv_map.setTextColor(Color.parseColor("#0b2092"));

                linear_dairy.setBackgroundColor(Color.WHITE);
                img_dairy.setBackgroundResource(R.drawable.book_blue);
                tv_dairy.setTextColor(Color.parseColor("#0b2092"));

                linear_competition.setBackgroundColor(Color.WHITE);
                img_competition.setBackgroundResource(R.drawable.share);
                tv_competition.setTextColor(Color.parseColor("#0b2092"));

                startActivity(i_graph);
            }
        });

        img_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_graph=new Intent(getApplicationContext(),ActivityGraph.class);

                linear_graph.setBackgroundColor(Color.parseColor("#0b2092"));
                img_graph.setBackgroundResource(R.drawable.graph_white);
                tv_graph.setTextColor(Color.WHITE);

                linear_map.setBackgroundColor(Color.WHITE);
                img_map.setBackgroundResource(R.drawable.map_blue);
                tv_map.setTextColor(Color.parseColor("#0b2092"));

                linear_dairy.setBackgroundColor(Color.WHITE);
                img_dairy.setBackgroundResource(R.drawable.book_blue);
                tv_dairy.setTextColor(Color.parseColor("#0b2092"));

                linear_competition.setBackgroundColor(Color.WHITE);
                img_competition.setBackgroundResource(R.drawable.share);
                tv_competition.setTextColor(Color.parseColor("#0b2092"));

                startActivity(i_graph);
            }
        });

        linear_dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i_dairy=new Intent(getApplicationContext(),Dairy.class);

                linear_dairy.setBackgroundColor(Color.parseColor("#0b2092"));
                img_dairy.setBackgroundResource(R.drawable.book_white);
                tv_dairy.setTextColor(Color.WHITE);

                linear_map.setBackgroundColor(Color.WHITE);
                img_map.setBackgroundResource(R.drawable.map_blue);
                tv_map.setTextColor(Color.parseColor("#0b2092"));

                linear_graph.setBackgroundColor(Color.WHITE);
                img_graph.setBackgroundResource(R.drawable.graph_blue);
                tv_graph.setTextColor(Color.parseColor("#0b2092"));

                linear_competition.setBackgroundColor(Color.WHITE);
                img_competition.setBackgroundResource(R.drawable.share);
                tv_competition.setTextColor(Color.parseColor("#0b2092"));

                startActivity(i_dairy);
            }
        });

        img_dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_dairy=new Intent(getApplicationContext(),Dairy.class);

                linear_dairy.setBackgroundColor(Color.parseColor("#0b2092"));
                img_dairy.setBackgroundResource(R.drawable.book_white);
                tv_dairy.setTextColor(Color.WHITE);

                linear_map.setBackgroundColor(Color.WHITE);
                img_map.setBackgroundResource(R.drawable.map_blue);
                tv_map.setTextColor(Color.parseColor("#0b2092"));

                linear_graph.setBackgroundColor(Color.WHITE);
                img_graph.setBackgroundResource(R.drawable.graph_blue);
                tv_graph.setTextColor(Color.parseColor("#0b2092"));

                linear_competition.setBackgroundColor(Color.WHITE);
                img_competition.setBackgroundResource(R.drawable.share);
                tv_competition.setTextColor(Color.parseColor("#0b2092"));

                startActivity(i_dairy);
            }
        });
        linear_competition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linear_competition.setBackgroundColor(Color.parseColor("#0b2092"));
                img_competition.setBackgroundResource(R.drawable.share_white);
                tv_competition.setTextColor(Color.WHITE);

                linear_map.setBackgroundColor(Color.WHITE);
                img_map.setBackgroundResource(R.drawable.map_blue);
                tv_map.setTextColor(Color.parseColor("#0b2092"));

                linear_graph.setBackgroundColor(Color.WHITE);
                img_graph.setBackgroundResource(R.drawable.graph_blue);
                tv_graph.setTextColor(Color.parseColor("#0b2092"));

                linear_dairy.setBackgroundColor(Color.WHITE);
                img_dairy.setBackgroundResource(R.drawable.book_blue);
                tv_dairy.setTextColor(Color.parseColor("#0b2092"));


                ShareCompat.IntentBuilder.from(activity)
                        .setType("text/plain")
                        .setChooserTitle("Chooser title")
                        .setText("http://play.google.com/store/apps/details?id=" + activity.getPackageName())
                        .startChooser();

//                ApplicationInfo app = getApplicationContext().getApplicationInfo();
//                String filePath = app.sourceDir;
//
//                Intent intent = new Intent(Intent.ACTION_SEND);
//
//                // MIME of .apk is "application/vnd.android.package-archive".
//                // but Bluetooth does not accept this. Let's use "*/*" instead.
//                intent.setType("*/*");
//
//                // Append file and send Intent
//                File originalApk = new File(filePath);
//
//                try {
//                    //Make new directory in new location
//                    File tempFile = new File(getExternalCacheDir() + "/ExtractedApk");
//                    //If directory doesn't exists create new
//                    if (!tempFile.isDirectory())
//                        if (!tempFile.mkdirs())
//                            return;
//                    //Get application's name and convert to lowercase
//                    tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ","").toLowerCase() + ".apk");
//                    //If file doesn't exists create new
//                    if (!tempFile.exists()) {
//                        if (!tempFile.createNewFile()) {
//                            return;
//                        }
//                    }
//                    //Copy file to new location
//                    InputStream in = new FileInputStream(originalApk);
//                    OutputStream out = new FileOutputStream(tempFile);
//
//                    byte[] buf = new byte[1024];
//                    int len;
//                    while ((len = in.read(buf)) > 0) {
//                        out.write(buf, 0, len);
//                    }
//                    in.close();
//                    out.close();
//                    System.out.println("File copied.");
//                    //Open share dialog
//                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
//                    startActivity(Intent.createChooser(intent, "Share app via"));
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


            }
        });

        img_competition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_competition.setBackgroundColor(Color.parseColor("#0b2092"));
                img_competition.setBackgroundResource(R.drawable.share_white);
                tv_competition.setTextColor(Color.WHITE);

                linear_map.setBackgroundColor(Color.WHITE);
                img_map.setBackgroundResource(R.drawable.map_blue);
                tv_map.setTextColor(Color.parseColor("#0b2092"));

                linear_graph.setBackgroundColor(Color.WHITE);
                img_graph.setBackgroundResource(R.drawable.graph_blue);
                tv_graph.setTextColor(Color.parseColor("#0b2092"));

                linear_dairy.setBackgroundColor(Color.WHITE);
                img_dairy.setBackgroundResource(R.drawable.book_blue);
                tv_dairy.setTextColor(Color.parseColor("#0b2092"));

                ShareCompat.IntentBuilder.from(activity)
                        .setType("text/plain")
                        .setChooserTitle("Chooser title")
                        .setText("http://play.google.com/store/apps/details?id=" + activity.getPackageName())
                        .startChooser();

//                ApplicationInfo app = getApplicationContext().getApplicationInfo();
//                String filePath = app.sourceDir;

//                Intent intent = new Intent(Intent.ACTION_SEND);
//
//                // MIME of .apk is "application/vnd.android.package-archive".
//                // but Bluetooth does not accept this. Let's use "*/*" instead.
//                intent.setType("*/*");
//
//                // Append file and send Intent
//                File originalApk = new File(filePath);
//
//                try {
//                    //Make new directory in new location
//                    File tempFile = new File(getExternalCacheDir() + "/ExtractedApk");
//                    //If directory doesn't exists create new
//                    if (!tempFile.isDirectory())
//                        if (!tempFile.mkdirs())
//                            return;
//                    //Get application's name and convert to lowercase
//                    tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ","").toLowerCase() + ".apk");
//                    //If file doesn't exists create new
//                    if (!tempFile.exists()) {
//                        if (!tempFile.createNewFile()) {
//                            return;
//                        }
//                    }
//                    //Copy file to new location
//                    InputStream in = new FileInputStream(originalApk);
//                    OutputStream out = new FileOutputStream(tempFile);
//
//                    byte[] buf = new byte[1024];
//                    int len;
//                    while ((len = in.read(buf)) > 0) {
//                        out.write(buf, 0, len);
//                    }
//                    in.close();
//                    out.close();
//                    System.out.println("File copied.");
//                    //Open share dialog
//                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
//                    startActivity(Intent.createChooser(intent, "Share app via"));
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
         }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menus,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {

            case R.id.settings:
                Intent i=new Intent(MainActivity.this,Settings_ListView.class);
                startActivity(i);
                break;
            case R.id.rateus:
                Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName()));
                startActivity(rateIntent);
                break;
            case R.id.log:

                dia=new Dialog(MainActivity.this);
                dia.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dia.setContentView(R.layout.alert_dialog);
                TextView txt_alert_yes=(TextView)dia.findViewById(R.id.dialog_yes);
                TextView txt_alert_no=(TextView)dia.findViewById(R.id.dialog_no);
                txt_alert_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i_yes=new Intent(getApplicationContext(),Login.class);
                        SaveSharedPreference.setRemind(getApplicationContext(),"No");
                        startActivity(i_yes);
                        finish();
                    }

                });
                txt_alert_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i_no=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i_no);
                    }
                });
                dia.show();
                dia.setCanceledOnTouchOutside(false);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
