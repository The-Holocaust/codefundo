package com.example.anmolbhat.codefundo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onesignal.OneSignal;


public class relief_activity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient client;
    private Button helpbttn;
    double lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relief_activity);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        helpbttn = findViewById(R.id.findrelief);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        client.getLastLocation().addOnSuccessListener(relief_activity.this, (loc) -> {
            if(loc == null)
                Toast.makeText(this,"Cant get Location",Toast.LENGTH_LONG).show();
            else {
                lat = loc.getLatitude();
                lng = loc.getLongitude();
                LatLng ll = new LatLng(lat,lng);
                CameraUpdate cm = CameraUpdateFactory.newLatLngZoom(ll,15);
                mMap.addMarker(new MarkerOptions().position(ll).title("You Are Here"));
                mMap.animateCamera(cm);
            }
        });

        helpbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getURL(lat, lng, "reliefcamps");
                Object[] transfer = new Object[2];
                transfer[0] = mMap;
                transfer[1] = url;

                GetNearByPlacesData getNearByPlacesData = new GetNearByPlacesData();
                getNearByPlacesData.execute(transfer);

                Toast.makeText(relief_activity.this, "Nearby Relief Camps", Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getURL(double lat, double lng, String s){

        String send = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&radius=50000&types="+s+"&sensor=true&key=AIzaSyAnBG9DjRxPi1ChqmfAlJjIst1FWr-ihN0";
        return send;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

}
