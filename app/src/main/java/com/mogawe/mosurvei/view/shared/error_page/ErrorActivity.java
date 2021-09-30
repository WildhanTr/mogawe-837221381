package com.mogawe.mosurvei.view.shared.error_page;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

import butterknife.BindView;

public class ErrorActivity extends BaseActivity {

    @BindView(R.id.btnBack)
    Button btnBack;

    @BindView(R.id.btnCall)
    Button btnCall;

    @BindView(R.id.btnSeeError)
    Button btnSeeError;

    @BindView(R.id.btnWhatsApp)
    Button btnWhatsApp;

    @BindView(R.id.rltErrorPreview)
    RelativeLayout rltErrorPreview;

    @BindView(R.id.closeErrorReview)
    ImageView closeErrorReview;

    @BindView(R.id.txvErrorMessage)
    TextView txvErrorMessage;

    String errorMessage, deviceInformation, firmwareInformation, operatorName, activityName;

    String brand, device, model, id, product, androidVersion, release, incremental, percentAvailableRam, availableRam, signalStrength, battery, availableStorage;

    float memoryUsage;

    double availableMegs, percentAvail;

    Integer versionNumber;

    @Override
    protected int layout() {
        return R.layout.a_error;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras().containsKey("error"))
            errorMessage = (String) getIntent().getSerializableExtra("error");

        if (getIntent().getExtras().containsKey("device"))
            deviceInformation = (String) getIntent().getSerializableExtra("device");

        if (getIntent().getExtras().containsKey("firmware"))
            firmwareInformation = (String) getIntent().getSerializableExtra("firmware");

        if (getIntent().getExtras().containsKey("activityName"))
            activityName = (String) getIntent().getSerializableExtra("activityName");

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo != null) {
            versionNumber = pInfo.versionCode;
        }

        StringTokenizer tokens = new StringTokenizer(deviceInformation, "\n");
        String headDevice = tokens.nextToken();
        device = tokens.nextToken();
        brand = tokens.nextToken();
        model = tokens.nextToken();
        id = tokens.nextToken();
        product = tokens.nextToken();

        StringTokenizer tokensFirmware = new StringTokenizer(firmwareInformation, "\n");
        String headFirmware = tokensFirmware.nextToken();
        androidVersion = tokensFirmware.nextToken();
        release = tokensFirmware.nextToken();
        incremental = tokensFirmware.nextToken();
        // CONNECTION TIPE AND OPERATOR
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        ConnectivityManager managerType = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        boolean isWifi = managerType.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();

        if (isWifi) {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifiManager.getConnectionInfo();
            operatorName = "SSID Name : " + info.getSSID();
        } else {
            operatorName = "Operator Name : " + manager.getNetworkOperatorName();
        }
        //===============================
        // CPU USAGE
        readUsage();
        //===============================
        // RAM USAGE
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        availableMegs = mi.availMem / 0x100000L;

        //Percentage can be calculated for API 16+
        percentAvail = mi.availMem / (double) mi.totalMem * 100.0;
        //===============================

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Bas.startActivity(ErrorActivity.this);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomor = "+628118808833";
                Intent tlp_intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", nomor, null));
                try {
                    startActivity(tlp_intent);
                } catch (android.content.ActivityNotFoundException ex) {
                }
            }
        });

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone=" + "+628118808833";
                try {
                    PackageManager pm = getPackageManager();
                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    Intent wa_intent = new Intent(Intent.ACTION_VIEW);
                    wa_intent.setData(Uri.parse(url));
                    startActivity(wa_intent);
                } catch (PackageManager.NameNotFoundException e) {
                }

            }
        });

        percentAvailableRam = "Percent Available : " + percentAvail + "%";
        availableRam = "Available RAM : " + availableMegs + "MB";

        btnSeeError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rltErrorPreview.setVisibility(View.VISIBLE);

                String text;

                if (memoryUsage == 0) {

                    text = errorMessage
                            + "\n"
                            + deviceInformation
                            + "\n"
                            + firmwareInformation
                            + "\n\n************ NETWORK ************\n"
                            + operatorName
                            + "\n\n************ MEMORY ************\n"
                            + percentAvailableRam
                            + "\n"
                            + availableRam
                            + "\n"
                            + "Activity : " + activityName;
                } else {

                    text = errorMessage
                            + "\n"
                            + deviceInformation
                            + "\n"
                            + firmwareInformation
                            + "\n\n************ NETWORK ************\n"
                            + operatorName
                            + "\n\n************ MEMORY ************\n"
                            + percentAvailableRam
                            + "\n"
                            + availableRam
                            + "\n"
                            + "App Usage : " + memoryUsage;
                }

                txvErrorMessage.setText(text);
            }
        });

        closeErrorReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rltErrorPreview.setVisibility(View.GONE);
            }
        });

        if (mSignalStrength > 30) {
            Log.d(getClass().getCanonicalName(), "Signal GSM : Good");
            Log.d(getClass().getCanonicalName(), String.valueOf(mSignalStrength));
            signalStrength = "Signal GSM : Good";

        } else if (mSignalStrength > 20 && mSignalStrength < 30) {
            Log.d(getClass().getCanonicalName(), "Signal GSM : Avarage");
            Log.d(getClass().getCanonicalName(), String.valueOf(mSignalStrength));
            signalStrength = "Signal GSM : Avarage";

        } else if (mSignalStrength < 20 && mSignalStrength > 3) {
            Log.d(getClass().getCanonicalName(), "Signal GSM : Weak");
            Log.d(getClass().getCanonicalName(), String.valueOf(mSignalStrength));
            signalStrength = "Signal GSM : Weak";

        } else if (mSignalStrength < 3) {
            Log.d(getClass().getCanonicalName(), "Signal GSM : Very weak");
            Log.d(getClass().getCanonicalName(), String.valueOf(mSignalStrength));

            signalStrength = "Signal GSM : Very weak";
        }

        if (getBatteryPercentage(this) > 0) {
            battery = "Battery Available : " + getBatteryPercentage(this) + "%";
        }

        availableStorage = "Available storage : " + String.valueOf(getAvailableSpaceInMB() + " MB");

        System.out.println("soutttt 1 " + signalStrength);
        System.out.println("soutttt 2 " + operatorName);
        System.out.println("soutttt 3 " + battery);
        System.out.println("soutttt 4 " + availableStorage);
        System.out.println("soutttt 5 " + percentAvailableRam);
        System.out.println("soutttt 6 " + availableRam);

        userViewModel.reportError(versionNumber, errorMessage, brand, device, model, id, product,androidVersion , release, incremental, percentAvailableRam, availableRam, operatorName, activityName, signalStrength, battery, availableStorage);

    }

    @Override
    public void onBackPressed() {
        if (rltErrorPreview.getVisibility() == View.VISIBLE) {
            rltErrorPreview.setVisibility(View.GONE);
        } else {
            finish();
        }
    }

    private void readUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String load = reader.readLine();

            String[] toks = load.split(" +");  // Split on one or more spaces

            long idle1 = Long.parseLong(toks[4]);
            long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            try {
                Thread.sleep(360);
            } catch (Exception e) {
            }

            reader.seek(0);
            load = reader.readLine();
            reader.close();

            toks = load.split(" +");

            long idle2 = Long.parseLong(toks[4]);
            long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            memoryUsage = (cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));

            Toast.makeText(getApplicationContext(), "MEMORY USAGE = " + memoryUsage, Toast.LENGTH_LONG).show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
