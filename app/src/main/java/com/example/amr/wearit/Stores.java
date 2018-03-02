package com.example.amr.wearit;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Stores extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    LocationManager IM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        IM = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        IM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 40, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Double Latitude = location.getLatitude();
                Double Long = location.getLongitude();
                LatLng current = new LatLng(Latitude,Long);

                Geocoder geo = new Geocoder(getApplicationContext());

                try {
                    List<Address> address = geo.getFromLocation(Latitude,Long,1);
                    String ad = "";
                    ad = address.get(0).getLocality() + "   "+ address.get(0).getCountryName();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mMap.addMarker(new MarkerOptions().position(current).title("E7na hena"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current,10));




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
        });
        mMap.setMyLocationEnabled(true);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(25, 151);
        LatLng gep = new LatLng(30.088821, 31.275158);
        LatLng nc = new LatLng(30.048940,  31.233873);
        LatLng h = new LatLng(30.091940, 31.289406);
        LatLng s = new LatLng(30.119265, 31.333179);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nc));

        mMap.addMarker(new MarkerOptions().position(gep).title("Nasr City Shop"));

        mMap.addMarker(new MarkerOptions().position(h).title("Heliopolis Shop"));

        mMap.addMarker(new MarkerOptions().position(nc).title("New Cairo Shop"));

        mMap.addMarker(new MarkerOptions().position(s).title("Sheraton Shop"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h,10));



    }
}