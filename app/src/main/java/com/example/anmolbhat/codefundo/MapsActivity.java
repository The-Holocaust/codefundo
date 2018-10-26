package com.example.anmolbhat.codefundo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient client;
    private Button helpbttn;
    double lat,lng;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        helpbttn = findViewById(R.id.buttonhelp);
        helpbttn.setEnabled(false);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Spinner precaution = findViewById(R.id.spinnerhelp);
        String[] disasters=getResources().getStringArray(R.array.disaster_array);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this, R.layout.custom_spinner, disasters);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        precaution.setAdapter(dataAdapter);

        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        client.getLastLocation().addOnSuccessListener(MapsActivity.this, (loc) -> {
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
                if(checkPermission(Manifest.permission.SEND_SMS) && checkPermission(Manifest.permission.READ_PHONE_STATE)){
                    SmsManager smsManager = SmsManager.getDefault();
                    //PendingIntent setIntent = PendingIntent.getBroadcast(MapsActivity.this, 0, new Intent("SENT_SMS"),0);
                    //PendingIntent deliveredIntent = PendingIntent.getBroadcast(MapsActivity.this, 0, new Intent("SMS_DELIVERED"),0);
                    smsManager.sendTextMessage("+917899270142", null, "NEED HELP!\nAT COORDINATES "+lat+", "+lng, null, null);
                    Toast.makeText(MapsActivity.this,"Sent",Toast.LENGTH_LONG).show();
                }
                else {
                    ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
                    ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, SEND_SMS_PERMISSION_REQUEST_CODE);
                    if(checkPermission(Manifest.permission.SEND_SMS)){
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage("+917899270142", null, "Fuck OFF", null, null);
                        Toast.makeText(MapsActivity.this,"Sent",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(MapsActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                }
            }
        });
        precaution.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                helpbttn.setEnabled(true);
                String url = getURL(lat, lng, "hospital");
                Object[] transfer = new Object[2];
                transfer[0] = mMap;
                transfer[1] = url;

                GetNearByPlacesData getNearByPlacesData = new GetNearByPlacesData();
                getNearByPlacesData.execute(transfer);

                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                helpbttn.setEnabled(false);
            }
        });
    }

    private String getURL(double lat, double lng, String s){

        String send = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&radius=50000&type="+s+"&sensor=true&key=AIzaSyAnBG9DjRxPi1ChqmfAlJjIst1FWr-ihN0";
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

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
