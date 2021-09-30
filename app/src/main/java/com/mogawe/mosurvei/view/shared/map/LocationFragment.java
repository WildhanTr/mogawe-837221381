package com.mogawe.mosurvei.view.shared.map;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.ui.IconGenerator;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.location.Coordinate;
import com.mogawe.mosurvei.model.bean.location.Place;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.presenter.LocationPresenter;
import com.mogawe.mosurvei.util.Constant;
import com.mogawe.mosurvei.util.PermissionUtil;
import com.mogawe.mosurvei.util.RxBusObject;
import com.mogawe.mosurvei.util.helper.DirectionsParser;
import com.mogawe.mosurvei.util.ui.Animator;
import com.mogawe.mosurvei.util.ui.NestedScrollMapView;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;

public class LocationFragment extends BaseFragment implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.InfoWindowAdapter,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraIdleListener,
        GoogleMap.OnMarkerClickListener,
        LocationListener {


    public static final String TAG = LocationFragment.class.getSimpleName();
    public LocationSettingsResult locationSettingsResult;
    public GoogleApiClient googleApiClient;
    @BindView(R.id.mapView)
    NestedScrollMapView mMapView;
    @BindView(R.id.btnSelectLocation)
    LinearLayout btnSelectLocation;
    @BindView(R.id.txvSelectLocation)
    TextView txvSelectLocation;
    @BindView(R.id.imvSelectLocation)
    ImageView imvSelectLocation;
    @BindColor(R.color.red_400)
    int red;
    @BindColor(R.color.deep_purple_400)
    int deepPurple;
    @BindColor(R.color.light_blue_400)
    int lightBlue;
    @BindColor(R.color.green_400)
    int green;
    @BindColor(R.color.yellow_400)
    int yellow;
    @BindColor(R.color.deep_orange_400)
    int deepOrange;
    @BindColor(R.color.pink_400)
    int pink;
    @BindColor(R.color.indigo_400)
    int indigo;
    @BindColor(R.color.cyan_400)
    int cyan;
    @BindColor(R.color.amber_400)
    int amber;
    int[] colors;
    private LocationPresenter presenter;
    private GoogleMap googleMap;
    private RxBusObject.RxBusKey action;
    private LocationRequest locationRequest;

    private Bundle savedInstanceState;

    private List<Polyline> drawnLine = new ArrayList<>();
    private List<Marker> drawnMarkers = new ArrayList<>();
    private List<Place> drawnAreaPlace = new ArrayList<>();
    private Timer t;

    private BaseFragment fragment;

    private boolean isCalled = false;

    public static void showFragment(BaseActivity sourceActivity) {

        if (!sourceActivity.isFragmentNotNull(TAG)) {
            FragmentTransaction fragmentTransaction = sourceActivity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new LocationFragment(), TAG);
            fragmentTransaction.commit();
        }

    }

    @Override
    protected int layout() {
        return R.layout.f_mapview;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawnLine = new ArrayList<>();
        drawnMarkers = new ArrayList<>();
        drawnAreaPlace = new ArrayList<>();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        colors = new int[]{red, deepPurple, lightBlue, green, yellow, deepOrange, pink, indigo, cyan, amber};

        presenter = new LocationPresenter(this);

        locationRequest = LocationRequest.create()
                .setInterval(30000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setFastestInterval(1000)
                .setSmallestDisplacement(6);

        initiateMapView(savedInstanceState);
        btnSelectLocation.setVisibility(View.GONE);

        if (PrefHelper.getBoolean(PrefKey.IS_OFFLINE)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onSelectLocationClicked();
                }
            }, 2100);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        removeMarkers();
        removeLines();
        removePlaces();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
        System.gc();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Constant.REQ_PERMISSION_LOCATION:
                if (PermissionUtil.hasPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) || PermissionUtil.hasPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION))
                    presenter.connectToGoogleApiClientAndGetLocationServices();
                break;
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            presenter.checkGpsSetting(googleApiClient);
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        } catch (Exception e) {

        }


    }

    @Override
    public void onConnectionSuspended(int i) {
        showFailedDialog("Cannot open google maps");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        showFailedDialog("Cannot open google maps");
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        //if (province != null && city != null)
        //AntarGoodsFragment.finishWithLocation(getActivity());
    }

    @Override
    public void onMapClick(LatLng latLng) {
        selectArea(latLng);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        selectMarker(marker);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setOnMapClickListener(this);
        googleMap.setOnInfoWindowClickListener(this);
        googleMap.setInfoWindowAdapter(this);
        googleMap.setOnCameraMoveListener(null);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnCameraIdleListener(this);

        presenter.connectToGoogleApiClientAndGetLocationServices();
        if (getArguments() != null) {
            if (getArguments().containsKey("action")) {
                if (getArguments().getSerializable("action").equals("drawmarker") && getArguments().getSerializable("fragment").equals(Constant.GAWEAN_INFORMATION_FRAGMENT)) {
                    Task job = (Task) getArguments().getSerializable("job");
                    Place area = (Place) getArguments().getParcelable("area");

                    getInformationGawean(job, area);

                } else if (getArguments().getSerializable("action").equals("drawmarker") && getArguments().getSerializable("fragment").equals(Constant.GAWEAN_INFORMATION_ACTIVITY)) {
                    Task job = (Task) getArguments().getSerializable("job");
                    Place area = (Place) getArguments().getParcelable("area");

                    getInformationGawean(job, area);

                } else if (getArguments().getSerializable("action").equals("drawmarker") && getArguments().getSerializable("fragment").equals(Constant.ROUTE_FRAGMENT)) {
                    ((RouteGaweanFragment) getParentFragment()).drawMarker();
                }
            }
        }

    }

    public void getInformationGawean(Task job, Place area) {
        if (job != null) {
            if (job.getLongitude() != null && job.getLongitude() != null && !job.getLatitude().equals("") && !job.getLongitude().equals("")) {
                Place place = new Place();
                place.setId(job.getId_job());
                place.setDesc(job.getBrief());
                LatLng jobLatLng = new LatLng(job.getLatitude(), job.getLongitude());
                place.setLatLng(jobLatLng);
                place.setIcon(R.drawable.ic_marker_red);
//                place.setIcon(R.drawable.ic_interview_marker);
//                switch (job.getId_job_type()) {
//                    case JOB_OBSERVATION:
//                        place.setIcon(R.drawable.ic_observation_marker);
//                        break;
//                    case JOB_INTERVIEW:
//                        place.setIcon(R.drawable.ic_interview_marker);
//                        break;
//                    case JOB_CENSUS:
//                        place.setIcon(R.drawable.ic_census_marker);
//                        break;
//                }
                if (place.getLatLng().longitude != 0 && place.getLatLng().latitude != 0) {
                    drawMarker(place);
                }
                LatLngBounds.Builder builder = new LatLngBounds.Builder();

                if (area != null) {
                    System.out.println("getInformationGaweanAREA ");
                    if (job.getLocation_level().equals(Constant.LEVEL_COUNTRY)) {
                        drawPolylines(area, "#00000000");
                    } else {
                        drawPolylines(area, null);
                        for (LatLng marker : area.getArea()) {
                            builder.include(marker);
                        }
                    }
                } else {
                    builder.include(jobLatLng);
                }


                //GET MY POSITION
                LatLng myPosition;
                Gson gson = new Gson();
                if (PrefHelper.getString(PrefKey.MY_LOCATION) != null && !PrefHelper.getString(PrefKey.MY_LOCATION).isEmpty()) {
                    Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
                    myPosition = new LatLng(myCoordinate.getLat(), myCoordinate.getLng());
                } else {
                    myPosition = new LatLng(0, 0);
                }

                int padding = 50; // offset from edges of the map in pixels
                CameraUpdate jobLocation;
                if (area != null) {
                    if (job.getLocation_level().equals(Constant.LEVEL_COUNTRY)) {
                        padding += 1000;
                    }
                    builder.include(myPosition);
                    LatLngBounds bounds = builder.build();
                    jobLocation = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                } else {
                    if (jobLatLng.longitude != 0 && jobLatLng.latitude != 0) {
                        jobLocation = CameraUpdateFactory.newLatLngZoom(jobLatLng, 15);
                    } else {
                        jobLocation = CameraUpdateFactory.newLatLngZoom(myPosition, 15);
                    }
                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            googleMap.animateCamera(jobLocation);
                            if (getParentFragment() != null) {

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, 1000);


            }
        }
    }

    @Override
    public void onCameraMove() {
        if (action == RxBusObject.RxBusKey.SELECT_LOCATION && txvSelectLocation.getVisibility() != View.VISIBLE) {
            Animator.scale(txvSelectLocation, 0, 1, Animator.SHORT);
            if (getArguments() != null) {
                if (getArguments().containsKey("fragment")) {
                    if (!getArguments().getSerializable("fragment").equals(Constant.GAWEAN_INFORMATION_FRAGMENT)) {
                        getBus().send(new RxBusObject(RxBusObject.RxBusKey.MAP_MOVE, null));
                    }
                } else {
                    getBus().send(new RxBusObject(RxBusObject.RxBusKey.MAP_MOVE, null));
                }
            } else {
                getBus().send(new RxBusObject(RxBusObject.RxBusKey.MAP_MOVE, null));
            }

        }

    }

    @Override
    public void onCameraIdle() {
        final LatLng centeredLatLng = new LatLng(googleMap.getCameraPosition().target.latitude, googleMap.getCameraPosition().target.longitude);
        if (t != null) {
            t.purge();
            t.cancel();
        }
        t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                getBus().send(new RxBusObject(RxBusObject.RxBusKey.SEND_CENTER, centeredLatLng));
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 1000);
    }


    @Override
    public void onLocationChanged(Location location) {

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        getBus().send(new RxBusObject(RxBusObject.RxBusKey.LOCATION_CHANGE, latLng));

    }


    @Override
    public void busHandler(RxBusObject.RxBusKey busKey, Object busObject) {
        Place place;
        switch (busKey) {

            case CLEAR_MAP:
                removeLines();
                removeMarkers();
                removePlaces();
                break;

            case CLEAR_LINES:
                removeLines();
                break;

            case REINITIATE_MAP:
                initiateMapView(savedInstanceState);
                break;

            case VIEW_MAP:
                action = RxBusObject.RxBusKey.VIEW_MAP;
                btnSelectLocation.setVisibility(View.INVISIBLE);
                txvSelectLocation.setVisibility(View.INVISIBLE);
                googleMap.setOnCameraMoveListener(null);
                break;

            case SELECT_LOCATION:
                action = RxBusObject.RxBusKey.SELECT_LOCATION;
                btnSelectLocation.setVisibility(View.VISIBLE);
                txvSelectLocation.setVisibility(View.INVISIBLE);
                googleMap.setOnCameraMoveListener(this);
                imvSelectLocation.setImageResource(R.drawable.ic_location_on_32px);
                break;

            case DRAW_MARKER:
                place = (Place) busObject;
                drawMarker(place);
                break;

            case DRAW_AREA:
                place = (Place) busObject;
                drawPolylines(place, null);
                break;

            case GO_TO_LOC:
                LatLng loc = (LatLng) busObject;
                if (loc.latitude != 0 && loc.longitude != 0)
                    goToLocation(loc);
                break;

            case DRAW_ROUTES:
                String url = (String) busObject;
                TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                System.out.println("INI URL HAHAHAHA : " + url);
                taskRequestDirections.execute(url);
                break;

            case DRAW_ROUTES_FROM_POLYLINES:
                String polylines = (String) busObject;

                decodeAndDrawRoutes(polylines);
                break;

            case PAUSE_LOCATION_UPDATE:
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
                break;

            case RESUME_LOCATION_UPDATE:
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
                break;
            case GET_INFORMATION_JOB_LOCATION:
                place = (Place) busObject;
                drawMarker(place);
                goToLocation(place.getLatLng());
                break;
            case FIT_BOUNDS_ALL:
                fitBounds();
                break;

        }
    }

    private void decodeAndDrawRoutes(String polylines) {
        List list = decodePolyline(polylines);

        ArrayList points = null;

        PolylineOptions polylineOptions = null;
        PolylineOptions polylineOptionsBorder = null;

        points = new ArrayList();
        //Loop for all points
        for (int l = 0; l < list.size(); l++) {
            double lat = ((LatLng) list.get(l)).latitude;
            double lon = ((LatLng) list.get(l)).longitude;

            points.add(new LatLng(lat, lon));
        }

        polylineOptionsBorder = new PolylineOptions();

        polylineOptionsBorder.addAll(points);
        polylineOptionsBorder.width(12);
        polylineOptionsBorder.color(getResources().getColor(R.color.blue_900));
//                polylineOptions.color(getRandomColor());
        polylineOptionsBorder.geodesic(true);
        //=====================================================================
        polylineOptions = new PolylineOptions();

        polylineOptions.addAll(points);
        polylineOptions.width(8);
        polylineOptions.color(getResources().getColor(R.color.blue_500));
//                polylineOptions.color(getRandomColor());
        polylineOptions.geodesic(true);


        if (polylineOptions != null && googleMap != null) {
            Polyline polylineBorder = googleMap.addPolyline(polylineOptionsBorder);
            Polyline polyline = googleMap.addPolyline(polylineOptions);
            drawnLine.add(polylineBorder);
            drawnLine.add(polyline);
//            Toast.makeText(getContext(), "Distance : "+(distance/1000)+ " KM | Duration : "+(duration/60) + " Minutes ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Direction not found!", Toast.LENGTH_SHORT).show();
        }
    }

    private List decodePolyline(String encoded) {

        List poly = new ArrayList();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                try {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    public void initiateMapView(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        mMapView.getMapAsync(this);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void drawMyLocation(Location myLocation) {
        try {
            LatLng originLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(originLatLng, 16);
            googleMap.moveCamera(yourLocation);

            getBus().send(new RxBusObject(RxBusObject.RxBusKey.MAP_READY, originLatLng));
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            googleMap.setMyLocationEnabled(true);

            selectCityFromLocation(myLocation.getLatitude(), myLocation.getLongitude());
            selectAddressFromLocation(myLocation.getLatitude(), myLocation.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void drawMarker(Place place) {
        int icon = R.drawable.ic_marker_red;
        if (place.getIcon() != 0)
            icon = place.getIcon();

        IconGenerator iconGen = new IconGenerator(getContext());
//        iconGen.setBackground(getResources().getDrawable(icon));
        iconGen.setColor(getResources().getColor(R.color.colorPrimary));

        if (place.getSequence() != null) {
            iconGen.setTextAppearance(R.style.markerStyleText);
        }

        try {

            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(place.getLatLng())
                    .title(place.getName() != null ? place.getName() : place.getDesc())
                    .icon(place.getSequence() != null ? BitmapDescriptorFactory.fromBitmap(iconGen.makeIcon(place.getSequence())) : BitmapDescriptorFactory.fromResource(icon)));
            marker.setTag(place.getId());
            marker.showInfoWindow();

            if (marker != null)
                drawnMarkers.add(marker);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawPolylines(Place place, String lineColor) {
        try {
            PolylineOptions polylineOptions = new PolylineOptions();
            final PatternItem GAP = new Gap(0);
            final PatternItem DASH = new Dash(3);

            final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DASH);

            polylineOptions.addAll(place.getArea());
            Polyline line = googleMap.addPolyline(polylineOptions);
            line.setWidth(16);
            line.setPattern(PATTERN_POLYLINE_DOTTED);
            if (place.getIcon() != 0) {
                line.setColor(getResources().getColor(place.getIcon()));
            } else {
                int id = 0;
                if (!place.getId().equals("")) {
                    id = Integer.parseInt(place.getId());
                }
                int lastDigit = id % 10;

                if (lineColor != null) {
//                line.setColor(colors[lastDigit]);
                    line.setColor(Color.parseColor(lineColor));
                } else {
                    line.setColor(colors[lastDigit]);
                }

            }

            drawnLine.add(line);
            drawnAreaPlace.add(place);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void goToLocation(LatLng latLng) {

        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        googleMap.animateCamera(yourLocation);
        googleMap.setOnCameraMoveListener(this);

    }

    public void goLocationFragmentChild(LatLng latLng) {

        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        googleMap.animateCamera(yourLocation);
        googleMap.setOnCameraMoveListener(this);

    }


    @OnClick(R.id.btnSelectLocation)
    void onSelectLocationClicked() {
        Animator.popDisappear(txvSelectLocation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txvSelectLocation.setVisibility(View.INVISIBLE);
                txvSelectLocation.setAlpha(1);
            }
        }, 400);

        LatLng centeredLatLng = new LatLng(googleMap.getCameraPosition().target.latitude, googleMap.getCameraPosition().target.longitude);
        getBus().send(new RxBusObject(RxBusObject.RxBusKey.DONE_SELECT_LOCATION, centeredLatLng));

        System.out.println("googlemap " + googleMap);
        System.out.println("googlemapgetcamerapositon " + googleMap.getCameraPosition());
        System.out.println("googlemapgetcamerapositontarget " + googleMap.getCameraPosition().target);
        System.out.println("googlemapgetcamerapositontargetlatitude " + googleMap.getCameraPosition().target.latitude);

//        if (googleMap != null) {
        try {
            selectCityFromLocation(centeredLatLng.latitude, centeredLatLng.longitude);
            selectAddressFromLocation(centeredLatLng.latitude, centeredLatLng.longitude);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        }

    }

    void selectAddressFromLocation(Double lat, Double lng) {
        String address;
        try {
            if (PrefHelper.getBoolean(PrefKey.IS_OFFLINE)) {
                address = "";
            } else {
                address = presenter.getAddressFromLatLng(lat, lng);
            }
            getBus().send(new RxBusObject(RxBusObject.RxBusKey.GET_ADDRESS, address));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void selectCityFromLocation(Double lat, Double lng) {
        String city;
        try {
            if (PrefHelper.getBoolean(PrefKey.IS_OFFLINE)) {
                city = "";
            } else {
                city = presenter.getCityFromLatLng(lat, lng);
            }
            getBus().send(new RxBusObject(RxBusObject.RxBusKey.GET_CITY, city));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean checkIfInBounds(LatLng myLocation) {
        try {
            System.out.println("drawnAreaPlace : " + drawnAreaPlace);
            if (drawnAreaPlace.size() > 0) {
                for (Place place : drawnAreaPlace) {
                    if (PolyUtil.containsLocation(myLocation, place.getArea(), true)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void selectArea(LatLng latLng) {
        try {
            if (drawnLine.size() > 0) {
                for (Place place : drawnAreaPlace) {
                    if (PolyUtil.containsLocation(latLng, place.getArea(), true)) {
                        getBus().send(new RxBusObject(RxBusObject.RxBusKey.SELECT_PLACE, place));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void selectMarker(Marker marker) {
        if (marker.getTag() != null) {
            String id = marker.getTag().toString();
//            goToLocation(marker.getPosition());
            getBus().send(new RxBusObject(RxBusObject.RxBusKey.SELECT_PLACE, id));
        } else {
            getBus().send(new RxBusObject(RxBusObject.RxBusKey.SELECT_NULL_PLACE, null));
        }
    }

    public void removeMarkers() {
        for (Marker marker : drawnMarkers) {
            marker.remove();
        }
    }

    public void removeLines() {
        for (Polyline line : drawnLine) {
            line.remove();
        }
    }

    public void removePlaces() {
        drawnAreaPlace = new ArrayList<>();
    }

    public void fitBounds() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        try {
            for (Marker marker : drawnMarkers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();

            int padding = 50; // offset from edges of the map in pixels
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            googleMap.animateCamera(cu);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getRequestUrl(LatLng origin, LatLng dest, List<LatLng> waypoints) {

        String delim = "|";

        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < waypoints.size() - 1) {
            sb.append(waypoints.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(waypoints.get(i));

        String res = sb.toString();

        res = res.replace("lat/lng: (", "").replace(")", "");

        //Value of waypoints
        String waypoint = "waypoints=" + res;
        //Value of origin
        String str_org = "origin=" + origin.latitude + "," + origin.longitude;
        //Value of destination
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        //Set value enable the sensor
        String sensor = "sensor=false";
        //Set value avoid restrictions
        String avoid = "avoid=tolls|highways";
        //Api Key
        String apiKey = "key=" + getString(R.string.google_directions_key);
        //Mode for find direction
        String mode = "mode=driving";
        //Build the full param
        String param = str_org + "&" + str_dest + "&" + waypoint + "&" + sensor + "&" + avoid + "&" + mode + "&" + apiKey;
        //Output format
        String output = "json";
        //Create url to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;

        return url;
    }

    private String requestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //Get the response result
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);

        }
    }

    public class TaskParser extends AsyncTask<String, Void, HashMap<String, Object>> {

        @Override
        protected HashMap<String, Object> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            HashMap<String, Object> parsed = new HashMap<>();
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
//                routes = directionsParser.parse(jsonObject);
                parsed = directionsParser.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return parsed;
        }

        @Override
        protected void onPostExecute(HashMap<String, Object> parsed) {
            //Get list route and display it into the map

            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            List<List<HashMap<String, String>>> lists = null;
            List<HashMap<String, String>> detail = null;
            Integer distance = 0;
            Integer duration = 0;

            lists = (List<List<HashMap<String, String>>>) parsed.get("routes");
            detail = (List<HashMap<String, String>>) parsed.get("detail");
            distance = Integer.parseInt(parsed.get("distanceTotal").toString());
            duration = Integer.parseInt(parsed.get("durationTotal").toString());

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));
                    points.add(new LatLng(lat, lon));
                }
                polylineOptions.addAll(points);
                polylineOptions.width(8);
                polylineOptions.color(Color.parseColor("#2196F3"));
//                polylineOptions.color(getRandomColor());
                polylineOptions.geodesic(true);


            }

            if (polylineOptions != null) {
                Polyline polyline = googleMap.addPolyline(polylineOptions);
                drawnLine.add(polyline);
                Toast.makeText(getContext(), "Distance : " + (distance / 1000) + " KM | Duration : " + (duration / 60) + " Minutes ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Direction not found!", Toast.LENGTH_SHORT).show();
            }

            getBus().send(new RxBusObject(RxBusObject.RxBusKey.DETAIL_FROM_DIRECTIONS, detail));

        }
    }

    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
