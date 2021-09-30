package com.mogawe.mosurvei.view.shared.map;

import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.Task.Tasks;
import com.mogawe.mosurvei.model.bean.location.Place;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.util.Constant;
import com.mogawe.mosurvei.util.RxBusObject;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RouteGaweanFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public Tasks tasks;
    private Location myPosition;

    public RouteGaweanFragment() {
        // Required empty public constructor
    }

    public static RouteGaweanFragment newInstance(String param1, String param2) {
        RouteGaweanFragment fragment = new RouteGaweanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static void showFragment(BaseActivity sourceActivity) {
        if (!sourceActivity.isFragmentNotNull(Constant.ROUTE_FRAGMENT)) {
            FragmentTransaction fragmentTransaction = sourceActivity.getSupportFragmentManager().beginTransaction();
            RouteGaweanFragment routeGaweanFragment = new RouteGaweanFragment();
            fragmentTransaction.replace(R.id.gaweanStatusContainer, routeGaweanFragment, Constant.ROUTE_FRAGMENT);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected int layout() {
        return R.layout.f_route;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_route, container, false);

        loadMap();

        return view;
    }

    private void createRoutesAndDrawRoutes() {
        System.out.println("How manyt timeesss ");
        List<Task> taskRemain = new ArrayList<>();
        taskRemain.addAll(tasks.getTasks());
        List<Task> taskRoute = new ArrayList<>();
        Task currentTaskPosition = null;

        Task myCoor = new Task();
        myCoor.setId_task("myPosition");
        myCoor.setLatitude(myPosition.getLatitude());
        myCoor.setLongitude(myPosition.getLongitude());
        taskRoute.add(myCoor);

        while (taskRemain.size() > 0) {
            Float nearestDistance = Float.valueOf(0);
            Location nowLocation = new Location("");

            if (currentTaskPosition != null) {
                nowLocation.setLatitude(currentTaskPosition.getLatitude());
                nowLocation.setLongitude(currentTaskPosition.getLongitude());
            } else {
                nowLocation.setLatitude(myPosition.getLatitude());
                nowLocation.setLongitude(myPosition.getLongitude());
            }
            for (Task t2 : taskRemain) {
                Location jobLocation = new Location("");
                jobLocation.setLatitude(t2.getLatitude());
                jobLocation.setLongitude(t2.getLongitude());

                Float jobDistance;
                if (currentTaskPosition == null) {
                    jobDistance = myPosition.distanceTo(jobLocation);
                } else {
                    jobDistance = nowLocation.distanceTo(jobLocation);
                }

                if (jobDistance <= nearestDistance || nearestDistance == 0) {
                    currentTaskPosition = t2;
                    nearestDistance = jobDistance;
                }
            }
            currentTaskPosition.setDistanceLocation(nearestDistance);

            taskRoute.add(currentTaskPosition);
            taskRemain.remove(taskRemain.indexOf(currentTaskPosition));
        }

        LatLng myLatLng = new LatLng(myPosition.getLatitude(), myPosition.getLongitude());
        List<LatLng> latLngs = new ArrayList<>();
        Place p = new Place();
        p.setId("1");
        p.setName(this.tasks.getGroup_name());
        List<LatLng> areas = new ArrayList<>();
        p.setIcon(R.color.red_600);
        for (Task t : taskRoute) {
            areas.add(new LatLng(t.getLatitude(), t.getLongitude()));
            latLngs.add(new LatLng(t.getLatitude(), t.getLongitude()));
        }
        p.setArea(areas);

        String url = getRequestUrl(myLatLng, myLatLng, latLngs);

        System.out.println("WAYPOINTS URL " + url);

        getBus().send(new RxBusObject(RxBusObject.RxBusKey.DRAW_ROUTES, url));
//            getBus().send(new RxBusObject(RxBusObject.RxBusKey.DRAW_AREA, p));
        getBus().send(new RxBusObject(RxBusObject.RxBusKey.FIT_BOUNDS_ALL, p));

        taskRoute.remove(taskRoute.indexOf(myCoor));
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

        System.out.println("WAYPOINTS " + res);

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
        String param = str_org + "&" + str_dest + "&" + waypoint + "&" + sensor + "&" + mode + "&" + avoid + "&" + apiKey;
        //Output format
        String output = "json";
        //Create url to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;

        return url;
    }

    private void loadMap() {
        try {
            Bundle bundle = new Bundle();
            bundle.putSerializable("action", "drawmarker");
            bundle.putSerializable("fragment", Constant.ROUTE_FRAGMENT);
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_out_right, R.anim.slide_in_left);
            LocationFragment locationFragment = new LocationFragment();
            locationFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.containerMappy, locationFragment, "locationFragment").commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawMarker() {
        try {
            getBus().send(new RxBusObject(RxBusObject.RxBusKey.CLEAR_MAP, null));
            for (Task task : tasks.getTasks()) {
                if (task != null) {
                    if (task.getLatitude() != null && task.getLongitude() != null && !task.getLatitude().equals("") && !task.getLongitude().equals("")) {
                        Place place = new Place();
                        place.setId(task.getId_job());
                        place.setDesc(task.getBrief());
                        LatLng latLng = new LatLng(task.getLatitude(), task.getLongitude());
                        place.setLatLng(latLng);
                        place.setIcon(R.drawable.ic_interview_marker);
                        switch (task.getId_job_type()) {
                            case JOB_OBSERVATION:
                                place.setIcon(R.drawable.ic_observation_marker);
                                break;
                            case JOB_INTERVIEW:
                                place.setIcon(R.drawable.ic_interview_marker);
                                break;
                            case JOB_CENSUS:
                                place.setIcon(R.drawable.ic_census_marker);
                                break;
                        }
                        getBus().send(new RxBusObject(RxBusObject.RxBusKey.DRAW_MARKER, place));
                    }
                }
            }
        } catch (Exception e) {

        }

        createRoutesAndDrawRoutes();

    }
}