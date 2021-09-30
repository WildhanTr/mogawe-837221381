package com.mogawe.mosurvei.util.helper;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NgocTri on 12/11/2017.
 */

public class DirectionsParser {
    /**
     * Returns a list of lists containing latitude and longitude from a JSONObject
     */
//    public List<List<HashMap<String, String>>> parse(JSONObject jObject) {
      public HashMap<String, Object> parse(JSONObject jObject) {

        HashMap<String,Object> parsed = new HashMap<>();

        List<List<HashMap<String, String>>> routes = new ArrayList<List<HashMap<String, String>>>();
        List<HashMap<String, String>> detail = new ArrayList<HashMap<String, String>>();
        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;

        Integer durationTotal = 0;
        Integer distanceTotal = 0;

        try {

            jRoutes = jObject.getJSONArray("routes");
            System.out.println(((JSONObject) jObject.getJSONArray("routes").get(0)).getJSONObject("overview_polyline").get("points").toString());
            // Loop for all routes
            for (int i = 0; i < jRoutes.length(); i++) {
                jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
                List path = new ArrayList<HashMap<String, String>>();

                //Loop for all legs
                for (int j = 0; j < jLegs.length(); j++) {
                    jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");

                    durationTotal += Integer.parseInt(((JSONObject) jLegs.get(j)).getJSONObject("duration").get("value").toString());
                    distanceTotal += Integer.parseInt(((JSONObject) jLegs.get(j)).getJSONObject("distance").get("value").toString());

                    HashMap<String, String> hashMapDetail = new HashMap<String, String>();
                    hashMapDetail.put("lat", ((JSONObject) jLegs.get(j)).getJSONObject("end_location").get("lat").toString());
                    hashMapDetail.put("lon", ((JSONObject) jLegs.get(j)).getJSONObject("end_location").get("lng").toString());
                    hashMapDetail.put("duration", ((JSONObject) jLegs.get(j)).getJSONObject("duration").get("value").toString());
                    hashMapDetail.put("distance", ((JSONObject) jLegs.get(j)).getJSONObject("distance").get("value").toString());

                    //Loop for all steps
                    for (int k = 0; k < jSteps.length(); k++) {
                        String polyline = "";
                        polyline = (String) ((JSONObject) ((JSONObject) jSteps.get(k)).get("polyline")).get("points");
                        List list = decodePolyline(polyline);

                        //Loop for all points
                        for (int l = 0; l < list.size(); l++) {
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("lat", Double.toString(((LatLng) list.get(l)).latitude));
                            hm.put("lon", Double.toString(((LatLng) list.get(l)).longitude));
                            path.add(hm);
                        }
                    }
                    routes.add(path);
                    detail.add(hashMapDetail);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }

        System.out.println("jRoutes : "+jRoutes);
        System.out.println("jLegs : "+jLegs);
        System.out.println("jSteps : "+jSteps);

//        parsed.put("jRoutes",jRoutes);
//        parsed.put("jLegs",jLegs);
//        parsed.put("jSteps",jSteps);

        parsed.put("routes",routes);
        parsed.put("distanceTotal",distanceTotal);
        parsed.put("durationTotal",durationTotal);
        parsed.put("detail",detail);
        return parsed;
    }

    /**
     * Method to decode polyline
     * Source : http://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     */
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
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
}