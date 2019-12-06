package com.krithiha.bfit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class MyActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap map;
    GPSTrackerService gpsTrackerService;
    double lat, longi;
    private Map<Marker, String> allMarkersMap = new HashMap<Marker, String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map);
        gpsTrackerService = new GPSTrackerService(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (map != null) {
            lat = gpsTrackerService.getLatitude();
            longi = gpsTrackerService.getLongitude();

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                requestPermissions();

            } else {
                map.setMyLocationEnabled(true);
                setUpMapMall();
                /*LatLng sydney = new LatLng(lat, longi);
                map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                map.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
            }

            return;

        }
    }

    private void setUpMapMall() {

        drawerMarkerCerca(new LatLng(lat, longi), "Location");

        CameraUpdate center =
                CameraUpdateFactory.newLatLng(new LatLng(lat, longi));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        map.moveCamera(center);
        map.animateCamera(zoom);
       // map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    private void drawerMarkerCerca(LatLng point, String location) {
      /*  Marker markerOptions = map.addMarker(new MarkerOptions().position(point)
                .title(location));*/

        LatLng sydney = new LatLng(lat, longi);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
       // map.animateCamera(CameraUpdateFactory.zoomTo(25));

       // Toast.makeText(getApplicationContext(), "" + lat + " " + longi, Toast.LENGTH_SHORT).show();
    }
      /*  allMarkersMap.put(markerOptions, location);
        //  Toast.makeText(getActivity(),"Point"+point+"ShopName"+shopName,Toast.LENGTH_SHORT).show();
        markerOptions.showInfoWindow();
        markerOptions.hideInfoWindow();*/





    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
    }
}