package com.mogawe.mosurvei.view.shared.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.appbar.AppBarLayout;
import com.google.maps.android.PolyUtil;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.MapPlace;
import com.mogawe.mosurvei.model.bean.location.Place;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.util.RxBusObject;
import com.mogawe.mosurvei.util.ui.Animator;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MapActivity extends BaseActivity {

    private static final String PLACE = "place";
    private static final String SELECTABLE = "selectable";
    private static final String FACT_ID = "factId";
    private static final String JOB_ID = "jobId";
    private static final String SECTION = "section";
    private static final String REQUEST_CODE = "request_code";
    private final static int REQUEST_LOC = 2;
    private final static int REQUEST_LOC_SPOT_CHECK = 22;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txvLatLong)
    TextView txvLatLong;
    @BindView(R.id.edtAlamat)
    EditText edtAlamat;
    @BindView(R.id.btnSetLocation)
    Button btnSetLocation;
    @BindView(R.id.cardSetLocation)
    CardView cardSetLocation;
    @BindView(R.id.btnCloseEdit)
    ImageView btnCloseEdit;

    private MapPlace place;
    private LatLng selectedLatLng;
    private LatLng myLatLng;

    private Boolean isSelectable = false;
    private Boolean isSelected = false;
    private int requestCode;

    private String factId = "";
    private String jobId = "";
    private Section section;
    private String latLngStr;

    private String city;


    public static void startActivity(BaseActivity sourceActivity, MapPlace place, Boolean isSelectable) {

        Intent intent = new Intent(sourceActivity, MapActivity.class);
        intent.putExtra(PLACE, place);
        intent.putExtra(SELECTABLE, isSelectable);
        sourceActivity.startActivity(intent);
    }


    public static void startActivityForResult(BaseActivity sourceActivity, int requestCode) {
        Intent intent = new Intent(sourceActivity, MapActivity.class);
        intent.putExtra(SELECTABLE, true);
        sourceActivity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(BaseActivity sourceActivity, MapPlace place, int requestCode, Boolean isSelectable) {

        Intent intent = new Intent(sourceActivity, MapActivity.class);
        intent.putExtra(PLACE, place);
        intent.putExtra(SELECTABLE, isSelectable);
        sourceActivity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(BaseActivity sourceActivity, MapPlace place, int requestCode, Boolean isSelectable, String jobId, String factId, Section section) {

        Intent intent = new Intent(sourceActivity, MapActivity.class);
        intent.putExtra(PLACE, place);
        System.out.println("startActivityForResult");
        intent.putExtra(SELECTABLE, isSelectable);
        intent.putExtra(JOB_ID, jobId);
        intent.putExtra(FACT_ID, factId);
        intent.putExtra(SECTION, section);
        intent.putExtra(REQUEST_CODE, requestCode);
        sourceActivity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(BaseActivity sourceActivity, int requestCode, Boolean isSelectable, String jobId, String factId, Section section) {

        Intent intent = new Intent(sourceActivity, MapActivity.class);
        intent.putExtra(SELECTABLE, isSelectable);
        intent.putExtra(JOB_ID, jobId);
        intent.putExtra(FACT_ID, factId);
        intent.putExtra(SECTION, section);
        intent.putExtra(REQUEST_CODE, requestCode);
        sourceActivity.startActivityForResult(intent, requestCode);
    }


    @Override
    protected int layout() {
        return R.layout.a_map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        AppBarLayout.LayoutParams params = new AppBarLayout.LayoutParams(
                AppBarLayout.LayoutParams.MATCH_PARENT,
                AppBarLayout.LayoutParams.WRAP_CONTENT
        );

        city = getIntent().getStringExtra("CITY");
        System.out.println("CITY CITY: " + city);

        System.out.println("getStatusBarHeight " + getStatusBarHeight());
        params.setMargins(0, getStatusBarHeight(), 0, 0);
        toolbar.setLayoutParams(params);

        place = (MapPlace) getIntent().getSerializableExtra(PLACE);
        isSelectable = getIntent().getBooleanExtra(SELECTABLE, false);
        requestCode = getIntent().getIntExtra(REQUEST_CODE, 0);

        if (getIntent().hasExtra(JOB_ID))
            jobId = getIntent().getStringExtra(JOB_ID);
        if (getIntent().hasExtra(FACT_ID))
            factId = getIntent().getStringExtra(FACT_ID);
        if (getIntent().hasExtra(SECTION))
            section = (Section) getIntent().getSerializableExtra(SECTION);

        LocationFragment.showFragment(this);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));



//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if(extras == null) {
//                iCity = null;
//                iProvince = null;
//                postalCode = null;
//            } else {
//                iCity= extras.getString("selectedCity");
//                iProvince= extras.getString("selectedProvince");
//                postalCode= extras.getString("postalCode");
//            }
//        } else {
//            iCity= (String) savedInstanceState.getSerializable("selectedCity");
//            iProvince = (String) savedInstanceState.getSerializable("selectedProvince");
//            postalCode = (String) savedInstanceState.getSerializable("postalCode");
//        }
//
//        System.out.println("postal code, city. prv: " + iCity + " " + iProvince + " " + postalCode);
    }


    @Override
    public void busHandler(RxBusObject.RxBusKey busKey, Object busObject) {
        super.busHandler(busKey, busObject);
        switch (busKey) {
            case MAP_READY:

//                if (!jobId.equals("") && !factId.equals("")) {
//                    for (MarkerLoc mLoc : getRealmHelper().loadMarkers(jobId, factId)) {
//                        Place p = new Place();
//                        p.setIcon(R.drawable.ic_census_marker);
//                        LatLng latLng = new LatLng(mLoc.getLatitude(), mLoc.getLongitude());
//                        p.setLatLng(latLng);
//                        getBus().send(new RxBusObject(RxBusObject.RxBusKey.DRAW_MARKER, p));
//                    }
//
//
//                    Place place = new Place();
//                    place.setIdPK("");
//                    place.setDesc("");
//                    List<LatLng> areas = new ArrayList<>();
//                    LatLng previousLatLng = null;
//                    int count = 0;
//
//                    for (Tracking t : getRealmHelper().loadTracks()) {
//                        double distance = 0;
//
//                        if (previousLatLng != null) {
//
//                            android.location.Coordinate currentLoc = new android.location.Coordinate("");
//                            currentLoc.setLatitude(t.getLatitude());
//                            currentLoc.setLongitude(t.getLongitude());
//
//                            android.location.Coordinate prevLoc = new android.location.Coordinate("");
//                            prevLoc.setLatitude(previousLatLng.latitude);
//                            prevLoc.setLongitude(previousLatLng.longitude);
//                            distance = currentLoc.distanceTo(prevLoc);
//                        }
//
//
//                        if (distance < 50 || count == 4) {
//
//                            areas.add(new LatLng(t.getLatitude(), t.getLongitude()));
//                            previousLatLng = new LatLng(t.getLatitude(), t.getLongitude());
//
//                            count = 0;
//                        } else {
//                            count++;
//                        }
//                    }

//                    place.setIcon(R.color.green_400);
//                    place.setArea(areas);
                //getBus().send(new RxBusObject(RxBusObject.RxBusKey.DRAW_AREA, place));
//                }

                myLatLng = (LatLng) busObject;

                if (place != null) {
                    if (!place.hasArea()) {
                        Place p = new Place();
                        p.setId(place.getId());
                        p.setDesc(place.getDesc());
                        p.setIcon(place.getIcon());

                        if (place.getLatLng() != null) {

                            LatLng latLng = new LatLng(place.getLatLng().get(0), place.getLatLng().get(1));
                            p.setLatLng(latLng);
                            getBus().send(new RxBusObject(RxBusObject.RxBusKey.DRAW_MARKER, p));
                            if (!isSelectable)
                                getBus().send(new RxBusObject(RxBusObject.RxBusKey.GO_TO_LOC, latLng));
                        }
                    } else {

                        if (place.getAreas().size() == 1) {
                            Place p = new Place();
                            p.setId(place.getId());
                            p.setDesc(place.getDesc());
                            p.setIcon(place.getIcon());

                            if (place.getAreas().get(0) != null) {

                                LatLng latLng = new LatLng(place.getAreas().get(0).get(0), place.getAreas().get(0).get(1));
                                p.setLatLng(latLng);
                                getBus().send(new RxBusObject(RxBusObject.RxBusKey.DRAW_MARKER, p));
                                if (!isSelectable)
                                    getBus().send(new RxBusObject(RxBusObject.RxBusKey.GO_TO_LOC, latLng));
                            }
                        } else if (place.getAreas().size() > 1) {
                            Place p = new Place();
                            p.setId(place.getId());
                            p.setDesc(place.getDesc());
                            List<LatLng> areas = new ArrayList<>();
                            LatLng latLng = null;
                            for (List<Double> area : place.getAreas()) {
                                areas.add(new LatLng(area.get(0), area.get(1)));
                                latLng = new LatLng(area.get(0), area.get(1));
                            }

                            p.setArea(areas);
                            getBus().send(new RxBusObject(RxBusObject.RxBusKey.DRAW_AREA, p));
                            if (!isSelectable)
                                getBus().send(new RxBusObject(RxBusObject.RxBusKey.GO_TO_LOC, latLng));
                        }


                    }
                }

                if (isSelectable) {
                    getBus().send(new RxBusObject(RxBusObject.RxBusKey.SELECT_LOCATION, null));
                }


                break;

            case DONE_SELECT_LOCATION:
                System.out.println("DONE_SELECT_LOCATION");

                selectedLatLng = (LatLng) busObject;
                System.out.println(selectedLatLng);
                isSelected = false;

                if (isSelectable && place != null) {
                    android.location.Location locA = new android.location.Location("");
                    locA.setLatitude(myLatLng.latitude);
                    locA.setLongitude(myLatLng.longitude);
                    android.location.Location locB = new android.location.Location("");
                    locB.setLatitude(selectedLatLng.latitude);
                    locB.setLongitude(selectedLatLng.longitude);
                    double distance = locA.distanceTo(locB); //metres

                    if (requestCode == REQUEST_LOC)
                        if (distance <= 20)
                            selectLocation();
                        else
                            showNeutralCostumDialog("", "Jarak lokasi yang dipilih tidak boleh lebih dari 20 meter", false);

                    else
                        selectLocation();
                } else {
                    selectLocation();
                }
                break;

            case GET_ADDRESS:
                System.out.println("GET ADDRESS" + isSelected);
                String address = busObject.toString();
                if (isSelected) {
                    txvLatLong.setText(selectedLatLng.latitude + ", " + selectedLatLng.longitude);
                    edtAlamat.setText(address);
                    Animator.slideUpAnimationView(cardSetLocation);
                    if (address.equals("")) {
                        Toast.makeText(this, "Mohon input alamat manual karena anda sedang offline", Toast.LENGTH_LONG).show();
                    }
                } else {
                    getBus().send(new RxBusObject(RxBusObject.RxBusKey.SELECT_LOCATION, null));
                }
                break;
        }
    }

    private void selectLocation() {
        if(place != null){
            if (place.hasArea()) {
                if (place.getAreas().size() > 1) {
                    List<LatLng> areas = new ArrayList<>();
                    for (List<Double> area : place.getAreas()) {
                        areas.add(new LatLng(area.get(0), area.get(1)));
                    }
                    if (PolyUtil.containsLocation(new LatLng(selectedLatLng.latitude, selectedLatLng.longitude), areas, true)) {
                        isSelected = true;
                    } else {
                        showNeutralCostumDialog("", "Kamu harus berada di dalam area gawean", false);
                    }
                } else {
                    isSelected = true;
                }
            } else {
                isSelected = true;
            }
        } else {
            isSelected = true;
        }
    }


    @OnClick(R.id.btnSetLocation)
    void onSetLocation(View v) {


        if (!edtAlamat.getText().toString().equals("")) {

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String value = extras.getString("CITY");
                System.out.println("CITY IS " + value);
            }
            latLngStr = selectedLatLng.latitude + "," + selectedLatLng.longitude + "," + edtAlamat.getText().toString();

            Intent intent = new Intent();
            intent.putExtra("latlngstr", latLngStr);
            intent.putExtra(SECTION, section);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "Alamat tidak boleh kosong!", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.btnCloseEdit)
    void onSetLocationClose(View v) {
        Animator.slideDownAnimationView(cardSetLocation);
    }

}
