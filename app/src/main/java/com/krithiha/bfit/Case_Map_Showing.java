package com.krithiha.bfit;

import android.support.v4.app.Fragment;

/**
 * Created by QBS 06 on 15-04-2016.
 */
public class Case_Map_Showing extends Fragment {

   /* MapView mapcerca;
    GPSTracker gps;
    String lat, lon;
    private GoogleMap mMap;
    private Map<Marker, String> allMarkersMap = new HashMap<Marker, String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.case_mapshowing, container, false);

        mapcerca = (MapView) v.findViewById(R.id.mapview);
        mapcerca.onCreate(savedInstanceState);
        mapcerca.onResume();

        Bundle bndle = getArguments();

        lat = "" + bndle.getString("latitude");
        lon = "" + bndle.getString("longitude");
        Log.e("MapSh", "MapSh" + lat + lon);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (lat != null) {

            setUpMapIfNeededCerca();


        }

        return v;
    }


    private void setUpMapIfNeededCerca() {

        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            FragmentManager myFM = getActivity().getSupportFragmentManager();
//            mMap = ((SupportMapFragment) myFM.findFragmentById(R.id.map))
//                    .getMap();
            mMap = mapcerca.getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                setUpMapMall();
            }
        }

    }

    private void setUpMapMall() {

if(lat!=null) {

 if(lon!=null) {
     drawerMarkerCerca(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)), "Location");

     CameraUpdate center =
             CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(lat),
                     Double.parseDouble(lon)));
     CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);

     mMap.moveCamera(center);
     mMap.animateCamera(zoom);
 }
}
    }

    private void drawerMarkerCerca(LatLng point, String location) {
        Marker markerOptions = mMap.addMarker(new MarkerOptions().position(point)
                .title(location));

        allMarkersMap.put(markerOptions, location);
        //  Toast.makeText(getActivity(),"Point"+point+"ShopName"+shopName,Toast.LENGTH_SHORT).show();
        markerOptions.showInfoWindow();
        markerOptions.hideInfoWindow();


    }
*/

}
