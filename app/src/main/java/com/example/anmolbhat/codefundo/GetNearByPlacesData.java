package com.example.anmolbhat.codefundo;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearByPlacesData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects) {

        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];

        DownloadURL downloadURL = new DownloadURL();

        try {
            googlePlacesData = downloadURL.ReadURL(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String, String>> nearbyplacelist = null;
        DataParser dp = new DataParser();
        nearbyplacelist = dp.parse(s);
        showNearByPlaces(nearbyplacelist);
    }

    private void showNearByPlaces(List<HashMap<String, String>> nearByPlaceList){

        for(int i=0;i<nearByPlaceList.size();i++){
            MarkerOptions mo = new MarkerOptions();
            HashMap<String, String> googlePlace = nearByPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));

            LatLng ll = new LatLng(lat, lng);
            mo.position(ll);
            mo.title(placeName+" : "+vicinity);

            mMap.addMarker(mo);
            CameraUpdate cm = CameraUpdateFactory.newLatLngZoom(ll,10);
            mMap.animateCamera(cm);
        }
    }
}
