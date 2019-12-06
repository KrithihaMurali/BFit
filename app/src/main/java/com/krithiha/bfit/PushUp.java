package com.krithiha.bfit;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Stack;


import android.content.Context;
import android.graphics.Typeface;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;


public class PushUp extends AppCompatActivity implements
        AccelerometerListener {

    public static String serverIpAddress = "192.168.1.9";
    //GIFView gifView;
    private static Context CONTEXT;
    int ti = 0;
    double mets = 0;
    double kcal = 0;
    int weight = 50;
    int time_win = 3;
    public static int lengthFFT = 256;

    TextView result;
    TextView accx, accy, accz;

    public static int h, w;
    MyViewVibrometer myVeiw;
    private long lastUpdate;
    RelativeLayout rr, graphrr;
    boolean flagDataSend = true;
    String ip[];

    LinearLayout part2, part1;

    TextView status;

    TextView energy;
    // Boolean partFlag=true;

    ServerConnection serverObj;

    double[] realArrayX = new double[lengthFFT];
    double[] realArrayY = new double[lengthFFT];
    double[] realArrayZ = new double[lengthFFT];
    double[] imagArray = new double[lengthFFT];

    float x_coordinate = 0;
    float y_coordinate = 0;
    float z_coordinate = 0;

    Stack<String> cvsValue = new Stack<String>();
    Thread t;

    protected int read;
    ProgressBar progBar;
    Spinner spi;

    private Handler myHandler = new Handler();

    private Runnable r = new Runnable() {

        @Override
        public void run() {

            storeAndCallFFt(x_coordinate, y_coordinate, z_coordinate, ti);
            ti++;
            myHandler.postDelayed(r, 20);

        }
    };

    // AudioManagerClass obj;

    /**
     * Called when the activity is first created.
     */

    android.support.v7.app.ActionBar action;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        action=this.getSupportActionBar();
       // action.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().show();


        CONTEXT = this;
        // obj = new AudioManagerClass(CONTEXT);
        // obj.playAudio();

        Display disp = getWindowManager().getDefaultDisplay();

        h = disp.getHeight();
        w = disp.getWidth();

        setContentView(R.layout.pushup);
        ((Chronometer) findViewById(R.id.chronometer1)).start();

        myVeiw = new MyViewVibrometer(this);
        myVeiw.invalidate();
        rr = (RelativeLayout) findViewById(R.id.rr);

        LayoutParams rLParams = new LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        rLParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);

        rr.addView(myVeiw, rLParams);

        myHandler.removeCallbacks(r);
        myHandler.postDelayed(r, 20);

        //gifView = (GIFView) findViewById(R.id.gifview);
        // gifView.getLayoutParams().height = (int) (h * .20);
        // gifView.getLayoutParams().width = (int) (w);

        //	gifView.setGIFResource(R.drawable.idel3);

        // gifView.getLayoutParams().height=(int) (h*.40);
        // gifView.getLayoutParams().width=(int) (w);

        Typeface tfReguler = Typeface.createFromAsset(getAssets(),
                "fonts/LED22.ttf");

        Typeface digital = Typeface.createFromAsset(getAssets(),
                "fonts/Digital.ttf");

        accx = (TextView) findViewById(R.id.accx);
        accy = (TextView) findViewById(R.id.accy);
        accz = (TextView) findViewById(R.id.accz);
        status = (TextView) findViewById(R.id.status);

        energy = (TextView) findViewById(R.id.energy_value);

        accx.setTypeface(digital);
        accy.setTypeface(digital);
        accz.setTypeface(digital);
        energy.setTypeface(digital);
        status.setTypeface(tfReguler);

        RelativeLayout rrgifview = (RelativeLayout) findViewById(R.id.rrgifview);

        rrgifview.getLayoutParams().height = (int) (h * .30);
        // rrgifview.getLayoutParams().width = (int) (w);

        LinearLayout llmidle1 = (LinearLayout) findViewById(R.id.llmidle1);
        llmidle1.getLayoutParams().height = (int) (h * .25);
        llmidle1.getLayoutParams().width = (int) (w);

        graphrr = (RelativeLayout) findViewById(R.id.graphrr);

        graphrr.getLayoutParams().height = (int) (h * .15);
        graphrr.getLayoutParams().width = (int) (w - 5);

        part2 = (LinearLayout) findViewById(R.id.part2);
        part2.setVisibility(View.GONE);

        part1 = (LinearLayout) findViewById(R.id.part1);
        // part1.setVisibility(View.GONE);

        RelativeLayout accxyz = (RelativeLayout) findViewById(R.id.accxyz);
        accxyz.getLayoutParams().height = (int) (h * .40);

        AsynTaskForCreateCSV csvCrtObj = new AsynTaskForCreateCSV();
        csvCrtObj.execute("");

        PreferencesManager preferencesManager = new PreferencesManager(this);
        preferencesManager.setActivity("Push Up");
        preferencesManager.setActivityStartTime((new Date()) + "");

        Button btn_acc = (Button)findViewById(R.id.btn_acc);
        Button btn_ass = (Button)findViewById(R.id.btn_ass);
        btn_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showGraph();
            }
        });

        btn_ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAction();
            }
        });


    }

    protected void onDestroy() {
        super.onDestroy();


        flagDataSend = false;
        if (AccelerometerManager.isListening()) {
            AccelerometerManager.stopListening();
        }

        DBActivity dbActivity = new DBActivity(PushUp.this);

        dbActivity.saveActivity(energy.getText().toString());
        ((Chronometer) findViewById(R.id.chronometer1)).stop();

    }

    public void showGraph() {
        final Animation anm1 = new TranslateAnimation(w, 0, 0, 0);
        anm1.setDuration(500);
        part2.startAnimation(anm1);

        anm1.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                part1.setVisibility(View.GONE);
                part2.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Animation", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub

            }
        });
        final Animation anm2 = new TranslateAnimation(0, -w, 0, 0);
        anm2.setDuration(500);
        part1.startAnimation(anm2);

        anm2.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                // part1.setVisibility(View.GONE);
                // part2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub

            }
        });

    }

    public void showAction() {
        final Animation anm1 = new TranslateAnimation(-w, 0, 0, 0);
        anm1.setDuration(500);
        part1.startAnimation(anm1);

        anm1.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                part1.setVisibility(View.VISIBLE);
                part2.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub

            }
        });

        final Animation anm2 = new TranslateAnimation(0, w, 0, 0);
        anm2.setDuration(500);
        part2.startAnimation(anm2);

        anm2.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                part1.setVisibility(View.VISIBLE);
                part2.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub

            }
        });

    }

    /*public void createCsvFile() {

        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter("/sdcard/myfile.csv"), ',');
            // writer = new CSVWriter(new FileWriter(ge+"/myfile.csv"), ',');
            int cp = cvsValue.size();

            for (int i = 0; i < cp; i++) {
                String[] entries = cvsValue.pop().split(","); // array of your
                // values
                writer.writeNext(entries);
            }

            // String[] entries = str.split("#"); // array of your values
            // writer.writeNext(entries);
            writer.close();
        } catch (IOException e) {
            // error
        }
    }*/

    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

   /* public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }*/

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // public boolean onOptionsItemSelected(MenuItem item) {
    // switch (item.getItemId()) {
    // case R.id.csv:
    //
    // // serverObj.sendDate(x_coordinate + "," + y_coordinate + ","
    // // + z_coordinate);
    //
    // //"first#second#third"
    //
    //
    // // Log.v("LOG", "Time in milli = "+millisecond);
    // //
    // createCsvFile();
    //
    // return true;
    // case R.id.server:
    // if(isNetworkAvailable()){
    //
    // final Dialog d = new Dialog(this);
    // d.setContentView(R.layout.dialogforip);
    // d.setTitle("Enter Server IP");
    // d.setCancelable(true);
    // RelativeLayout r = (RelativeLayout) d
    // .findViewById(R.id.rripaddress);
    // r.getLayoutParams().height = (int) (h * .6);
    // r.getLayoutParams().width = (int) (w * .80);
    // final EditText txtip = (EditText) d.findViewById(R.id.txtip);
    //
    // final String str = getipAddress();
    //
    // System.out.println(" Ip address " + str);
    // StringTokenizer strTok = null;
    //
    // if (str != null) {
    // strTok = new StringTokenizer(str, ".");
    // } else {
    // strTok = new StringTokenizer("0.0.0.0", ".");
    // }
    //
    // // StringTokenizer strTok = new StringTokenizer(str, ".");
    //
    // final String subnetadd = strTok.nextToken() + "."
    // + strTok.nextToken() + "." + strTok.nextToken();
    //
    // txtip.setText(subnetadd + ".");
    // txtip.setSelection(txtip.getText().length());
    // System.out.println("Inet Address : " + subnetadd);
    //
    // progBar = (ProgressBar) d.findViewById(R.id.progressBar);
    //
    // progBar.setVisibility(View.INVISIBLE);
    //
    // spi = (Spinner) d.findViewById(R.id.spinner1);
    //
    // spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    //
    // @Override
    // public void onItemSelected(AdapterView<?> arg0, View arg1,
    // int arg2, long arg3) {
    // txtip.setText(spi.getItemAtPosition(arg2).toString());
    // }
    //
    // @Override
    // public void onNothingSelected(AdapterView<?> arg0) {
    // // TODO Auto-generated method stub
    //
    // }
    // });
    //
    // Button bconnect = (Button) d.findViewById(R.id.bconnect);
    // bconnect.setOnClickListener(new View.OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    //
    // if (str != null) {
    // serverIpAddress = txtip.getText().toString();
    // serverObj = new ServerConnection(serverIpAddress,
    // getApplicationContext());
    // d.dismiss();
    // AsynTaskForSendData obj = new AsynTaskForSendData();
    // obj.execute("");
    // } else {
    // txtip.setError("Invalid ip address");
    // }
    // }
    // });
    // Button bauto = (Button) d.findViewById(R.id.bauto);
    // bauto.setOnClickListener(new View.OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    // if (str != null) {
    // progBar.setVisibility(View.VISIBLE);
    // Toast.makeText(Accelerometer.this,
    // "\nThis takes more than 7 minutes\n",
    // Toast.LENGTH_LONG).show();
    //
    // // ip = checkHosts(subnetadd);
    //
    // AsymTaskSearchIP o = new AsymTaskSearchIP();
    // o.execute(subnetadd);
    // } else {
    // txtip.setError("Invalid ip address");
    // }
    // }
    // });
    //
    // d.show();
    //
    //
    // }
    // else{
    // Toast.makeText(Accelerometer.this,
    // "\n No WIFI Connection \n",
    // Toast.LENGTH_LONG).show();
    // }
    //
    // return true;
    // case R.id.exit:
    // finish();
    // default:
    // return super.onOptionsItemSelected(item);
    // }
    //
    // }

    public class AsymTaskSearchIP extends AsyncTask<String, String, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            ip = checkHosts(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            // ip=result;
            progBar.setVisibility(View.INVISIBLE);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                    PushUp.this,
                    android.R.layout.simple_spinner_item, ip);
            spinnerArrayAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The
            // drop
            // down
            // progressBar.setVisibility(View.INVISIBLE); // vieww
            spi.setAdapter(spinnerArrayAdapter);
        }

    }

    public static String getipAddress() {
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en
                    .hasMoreElements(); ) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr
                        .hasMoreElements(); ) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr
                            .nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipaddress = inetAddress.getHostAddress()
                                .toString();
                        return ipaddress;
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return null;
    }

    public String[] checkHosts(String subnet) {

        Stack<String> s = new Stack<String>();
        System.out.println("In checkHost Method");
        int timeout = 1500;
        for (int i = 1; i < 10; i++) {

            String host = subnet + "." + i;
            System.out.println(subnet + "." + i);
            try {
                if (InetAddress.getByName(host).isReachable(timeout)) {
                    System.out.println(host + " is reachable");
                    s.push(host);
                }
            } catch (Exception e) {
                System.out.println("Error");
            }

        }

        System.out.println("Stack Size " + s.size());
        String[] ipArry = new String[s.size()];

        int size = s.size();
        for (int i = 0; i < size; i++) {
            ipArry[i] = s.pop();
        }
        for (int i = 0; i < ipArry.length; i++) {
            System.out.println("Ip in String " + ipArry[i]);
        }
        return ipArry;

    }

    @Override
    protected void onStop() {

        super.onStop();
        myHandler.removeCallbacks(r);
    }

    protected void onResume() {
        super.onResume();
        if (AccelerometerManager.isSupported()) {
            AccelerometerManager.startListening(this);
        }

    }

    public static Context getContext() {
        return CONTEXT;
    }

    /**
     * onShake callback
     */
    public void onShake(float force) {
        // Toast.makeText(this, "Phone shaked : " + force, 1000).show();
    }

    /**
     * onAccelerationChanged callback
     */
    public synchronized void onAccelerationChanged(float x, float y, float z) {

        x_coordinate = x;
        y_coordinate = y;
        z_coordinate = z;
      /*  accx.setText("X: " + (x + "").substring(0, 4) + " ");
        accy.setText("Y: " + (y + "").substring(0, 4) + " ");
        accz.setText("Z: " + (z + "").substring(0, 4) + " ");*/
        try {
            accx.setText("X: " + (x + "").substring(0, 4) + " ");
            accy.setText("Y: " + (y + "").substring(0, 4) + " ");
            accz.setText("Z: " + (z + "").substring(0, 4) + " ");
        } catch (Exception e) {
            // TODO: handle exception
        }

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        if (((int) accelationSquareRoot) % 2 == 0) {

            myVeiw.updateAccelationSquareRoot(-(accelationSquareRoot - 1));
        } else {

            myVeiw.updateAccelationSquareRoot((accelationSquareRoot - 1));
        }

        long actualTime = System.currentTimeMillis();
        if (accelationSquareRoot >= 2) //
        {
            if (actualTime - lastUpdate < 200) {
                return;
            }

            lastUpdate = actualTime;

        }

    }

    /**
     * <p>
     * This is the method for .
     * </p>
     *
     * @param x
     * @param y
     * @param z
     * @param i
     */

    private void storeAndCallFFt(float x, float y, float z, int i) {
        if (ti++ >= lengthFFT) {
            ResultObj maxPosX = new ResultObj();
            ResultObj maxPosY = new ResultObj();
            ResultObj maxPosZ = new ResultObj();
            maxPosX = FastFourierTransform.fastFT(realArrayX, imagArray, true);
            maxPosY = FastFourierTransform.fastFT(realArrayY, imagArray, true);
            maxPosZ = FastFourierTransform.fastFT(realArrayZ, imagArray, true);
            ti = 0;
            setResultToView(maxPosX, maxPosY, maxPosZ);
        } else {
            realArrayX[i] = x;
            realArrayY[i] = y;
            realArrayZ[i] = z;
            imagArray[i] = 0;
        }

    }

    // AudioManagerClass obj = new AudioManagerClass(context);
    // obj.playAudio();
    private void setResultToView(ResultObj maxPosX, ResultObj maxPosY,
                                 ResultObj maxPosZ) {
        int maxPosOfX, maxPosOfY, maxPosOfZ, temp1, temp2, maxPos;
        double maxAMPx, maxAMPy, maxAMPz, maxAMP;
        Boolean xflag = false, yflag = false, zflag = false;

        maxPosOfX = maxPosX.getResultPos();
        maxPosOfY = maxPosY.getResultPos();
        maxPosOfZ = maxPosZ.getResultPos();
        maxAMPx = maxPosX.getResultAmp();
        maxAMPy = maxPosY.getResultAmp();
        maxAMPz = maxPosZ.getResultAmp();

        temp1 = Double.compare(maxAMPx, maxAMPy);

        if (temp1 > 0) {
            temp2 = Double.compare(maxAMPx, maxAMPz);

            if (temp2 > 0) {
                if (Double.compare(maxAMPx, 0.1) > 0) {
                    if (maxPosOfX > 4) {
                        ///	gifView.setGIFResource(R.drawable.run);
                        status.setText("Push Ups");
                        // obj.playAudio();
                        // obj.setLooping(true);
                        mets = 0.093 * (maxPosOfX * 0.5 * 60) - 4.7;
                    } else {
                        ///gifView.setGIFResource(R.drawable.walk);
                        status.setText("Push Ups");
                        // obj.stopAudio();

                        mets = (0.0272 * (maxPosOfX * 0.5 * 60)) + 1.2;
                    }
                } else {
                    //	gifView.setGIFResource(R.drawable.idel3);
                    status.setText("Idle");
                    mets = 2;
                }

                kcal += 1.05 * mets * weight * time_win / (60 * 60);

                energy.setText("" + (int) kcal);

                return;
            } else {
                temp2 = Double.compare(maxAMPy, maxAMPz);
                if (temp2 > 0) {
                    if (Double.compare(maxAMPy, 0.1) > 0) {
                        if (maxPosOfY > 4) {
                            //gifView.setGIFResource(R.drawable.run);
                            status.setText("Push Ups");
                            // obj.playAudio();
                            // obj.setLooping(true);
                            mets = 0.093 * (maxPosOfY * 0.5 * 60) - 4.7;
                        } else {
                            //gifView.setGIFResource(R.drawable.walk);
                            status.setText("Push Ups");
                            // obj.stopAudio();
                            mets = (0.0272 * (maxPosOfY * 0.5 * 60)) + 1.2;
                        }
                    } else {
                        //gifView.setGIFResource(R.drawable.idel3);
                        status.setText("Idle");
                        mets = 2;
                    }

                    kcal += 1.05 * mets * weight * time_win / (60 * 60);

                    energy.setText("" + (int) kcal);

                    return;
                }
            }
        }

        if (Double.compare(maxAMPz, 0.1) > 0) {
            if (maxPosOfZ > 4) {
                //gifView.setGIFResource(R.drawable.run);
                status.setText("Push Ups");

                // obj.playAudio();
                // obj.setLooping(true);
                mets = 0.093 * (maxPosOfZ * 0.5 * 60) - 4.7;
            } else {
                //gifView.setGIFResource(R.drawable.walk);
                status.setText("Push Ups");
                // obj.stopAudio();
                mets = (0.0272 * (maxPosOfZ * 0.5 * 60)) + 1.2;
            }
        } else {
            //gifView.setGIFResource(R.drawable.idel3);
            status.setText("Idle");
            mets = 2;
        }

        kcal += 1.05 * mets * weight * time_win / (60 * 60);

        energy.setText("" + (int) kcal);

        return;

    }

    public class AsynTaskForSendData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            while (flagDataSend) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                publishProgress("");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Date beginupd = new Date();
            long millisecond = beginupd.getTime();

            String strVal = x_coordinate + "," + y_coordinate + ","
                    + z_coordinate + "," + millisecond;
            // cvsValue.push(strVal);
            serverObj.sendDate(strVal);
            super.onProgressUpdate(values);
        }
    }

    public class AsynTaskForCreateCSV extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            while (flagDataSend) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                publishProgress("");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Date beginupd = new Date();
            long millisecond = beginupd.getTime();

            String strVal = x_coordinate + "," + y_coordinate + ","
                    + z_coordinate + "," + millisecond;
            cvsValue.push(strVal);
            // Log.v("LOG", "Pushing data in to stack");
            // serverObj.sendDate(strVal);
            super.onProgressUpdate(values);
        }
    }
}
