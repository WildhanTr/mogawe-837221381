package com.mogawe.mosurvei.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.entity.Tracking;
import com.mogawe.mosurvei.model.repository.TrackingRepository;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.Constant;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class TrackingService extends Service {

    private static final String TAG = "MOGAWEGPSTRACK";
    private LocationManager mLocationManager = null;
//    private static final int LOCATION_INTERVAL = 1000 * 60 * 10; //10 minutes
    private static final int LOCATION_INTERVAL = 1000 * 10;
    private static final float LOCATION_DISTANCE = 100f; //500 meters

    private TrackingRepository repository;
    private Integer versionNumber;

    String operatorName, brand, device, model, id, product, androidVersion, release, incremental;

    float memoryUsage;

    double availableMegs, percentAvail;

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo != null) {
            versionNumber = pInfo.versionCode;
        }

        brand = Build.BRAND;
        device = Build.DEVICE;
        model = Build.MODEL;
        id = Build.ID;
        product = Build.PRODUCT;
        androidVersion = Build.VERSION.SDK;
        release = Build.VERSION.RELEASE;
        incremental = Build.VERSION.INCREMENTAL;

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

        repository = new TrackingRepository((MoSurveiApplication) getApplication());
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE, mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE, mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    private class LocationListener implements android.location.LocationListener {
        Location mLastLocation;

        public LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            System.out.println("OnLocationChanged");
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
//            String operatorName, brand, device, model, id, product, androidVersion, release, incremental;
            if (!PrefHelper.getString(PrefKey.ID_USER).equals("")) {
                Tracking tracking = new Tracking();
                tracking.setLatitude(location.getLatitude());
                tracking.setLongitude(location.getLongitude());
                tracking.setTimestamp(new Date());
                tracking.setCode(PrefHelper.getString(PrefKey.ID_USER));
                tracking.setSynced(false);
                tracking.setVersionNumber(versionNumber);
                tracking.setOperatorName(operatorName);
                tracking.setBrand(brand);
                tracking.setDevice(device);
                tracking.setModel(model);
                tracking.setIdDevice(id);
                tracking.setProduct(product);
                tracking.setSdk(androidVersion);
                tracking.setRelease(release);
                tracking.setIncremental(incremental);

                repository.save(tracking);
            }
            PrefHelper.setString(PrefKey.MY_LOCATION, "{ lat:" + location.getLatitude() + ", lng:" + location.getLongitude() + " }");

            if(new Date().getTime() > PrefHelper.getLong(PrefKey.NEXT_NEAR_JOB_NOTIFICATION_TIMESTAMP)){
                PrefHelper.setLong(PrefKey.NEXT_NEAR_JOB_NOTIFICATION_TIMESTAMP, new Date().getTime() + Constant.NEAR_JOB_NOTIFICATION_INTERVAL);
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
        Intent broadcastIntent = new Intent(this, TrackingRestarterBroadcastReceiver.class);
        sendBroadcast(broadcastIntent);
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
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

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

