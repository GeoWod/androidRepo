package com.geowod.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geowod.R;
import com.geowod.activity.CreateGeowodActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by sony on 25-10-2015.
 */
public class FragmentLocation extends Fragment {

    GoogleMap map;
    MapView mapView;
    View view;
    FloatingActionButton actionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_location,container,false);

        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        floatingButtinCall(view);
        initView(view);

        return view;

    }

    private void floatingButtinCall(View view) {
        actionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "toast clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),CreateGeowodActivity.class));
            }
        });
    }

    private void initView(View view) {



        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();

        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(18.5203, 73.8567), 10);
        map.animateCamera(cameraUpdate);
        LatLng currentPosition = new LatLng(18.5203,73.8567);
        map.addMarker(new MarkerOptions()
                .position(currentPosition)
                .snippet("Lat:" + "18.5203"))
        .setTitle("GeoWod");


    }

    /*private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment)getActivity(). getFragmentManager().findFragmentById(R.id.map)).getMap();
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getActivity(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
            }
        }
    }*/


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
