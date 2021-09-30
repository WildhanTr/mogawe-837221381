package com.mogawe.mosurvei.util;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location loc) {
        System.out.println("Coordinate");
        System.out.println(loc.getLatitude());
        System.out.println(loc.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
