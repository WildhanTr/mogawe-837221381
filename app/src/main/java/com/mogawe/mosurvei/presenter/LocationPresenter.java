package com.mogawe.mosurvei.presenter;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.util.Constant;
import com.mogawe.mosurvei.util.LocationUtil;
import com.mogawe.mosurvei.util.PermissionUtil;
import com.mogawe.mosurvei.view.shared.map.LocationFragment;

import java.util.List;
import java.util.Locale;

import androidx.core.content.ContextCompat;

/**
 * Created by glenrynaldi on 8/13/16.
 */

public class LocationPresenter {

    private LocationFragment fragment;

    public LocationPresenter(LocationFragment fragment) {
        this.fragment = fragment;
    }


    public void checkPermission() {
        if (PermissionUtil.useRunTimePermissions()) {
            if (PermissionUtil.shouldAskForPermission(fragment.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                showPermissionDialog();
            } else {
                PermissionUtil.requestPermissions(fragment, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, Constant.REQ_PERMISSION_LOCATION);
            }
        } else {
            connectToGoogleApiClientAndGetLocationServices();
        }
    }

    private void showPermissionDialog() {
        try {
            if(fragment.getActivity()!= null){
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
                builder.setTitle(R.string.confirmation)
                        .setMessage(R.string.permission_coordinate)
                        .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PermissionUtil.requestPermissions(fragment.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constant.REQ_PERMISSION_LOCATION);
                                PermissionUtil.requestPermissions(fragment.getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, Constant.REQ_PERMISSION_LOCATION);
                                PermissionUtil.requestPermissions(fragment.getActivity(), new String[]{Manifest.permission.INTERNET}, Constant.REQ_PERMISSION_LOCATION);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setCancelable(false)
                        .show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void showGPSDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
        builder.setTitle(R.string.confirmation)
                .setMessage(R.string.permission_coordinate)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            fragment.locationSettingsResult.getStatus().startResolutionForResult(fragment.getActivity(), Constant.REQ_PERMISSION_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            Toast.makeText(fragment.getActivity(), "Cannot Open Coordinate Setting", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .setCancelable(false)
                .show();

    }


    public void checkGpsSetting(final GoogleApiClient googleApiClient) {
        LocationServices.SettingsApi.checkLocationSettings(googleApiClient,
                LocationUtil.getLocationSettingRequest(LocationUtil.getLocationRequest()))
                .setResultCallback(new ResultCallback<LocationSettingsResult>() {
                    @Override
                    public void onResult(LocationSettingsResult result) {
                        fragment.locationSettingsResult = result;
                        final Status status = result.getStatus();
                        switch (status.getStatusCode()) {
                            case LocationSettingsStatusCodes.SUCCESS:
                                if (getLastLocation(googleApiClient) != null) {
                                    fragment.drawMyLocation(getLastLocation(googleApiClient));
                                }
                                break;
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                showGPSDialog();
                                break;
                        }
                    }
                });
    }

    public void connectToGoogleApiClientAndGetLocationServices() {
        System.out.println("connectToGoogleApiClientAndGetLocationServices");
        fragment.googleApiClient = new GoogleApiClient.Builder(fragment.getActivity())
                .addConnectionCallbacks(fragment)
                .addOnConnectionFailedListener(fragment)
                .addApi(LocationServices.API)
                .build();
        fragment.googleApiClient.connect();
        /**
         * when connected, setup location request. onConnected method
         * */
    }


    public Location getLastLocation(GoogleApiClient googleApiClient) {
        if (fragment != null) {
            try {
                if (ContextCompat.checkSelfPermission(fragment.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    return LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                } else {
                    showPermissionDialog();
                    return null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }

    }


    public String getAddressFromLatLng(Double latitude, Double longitude) throws Exception {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(fragment.getActivity(), new Locale("in", "ID"));

        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL


        return (address + " " + city + " " + postalCode).replace(",", "");
    }

    public String getCityFromLatLng(Double latitude, Double longitude) throws Exception {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(fragment.getActivity(), new Locale("in", "ID"));

        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String city = addresses.get(0).getLocality();

        return (city).replace(",", "");
    }


}
