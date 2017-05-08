package com.oceanbrasil.tdcexample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.oceanbrasil.tdcexample.R;
import com.oceanbrasil.tdcexample.model.Local;

import java.util.ArrayList;
import java.util.List;

public class ActivityAgrupamento extends AppCompatActivity implements OnMapReadyCallback {

    private ClusterManager<Local> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrupamento);

        //Vinculo map xml com java api
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-27.6004164, -48.5508872), 16));

        mClusterManager = new ClusterManager<>(this, googleMap);

        googleMap.setOnCameraIdleListener(mClusterManager);

        googleMap.setOnMarkerClickListener(mClusterManager);

        adicionaLocais();
    }

    private void adicionaLocais() {
        List<Local> locais = new ArrayList<>();
        locais.add(new Local(-27.602080,-48.551847));
        locais.add(new Local(-27.6003023,-48.5514665));
        locais.add(new Local(-27.599808, -48.550560));
        locais.add(new Local(-27.6003023,-48.5514665));
        locais.add(new Local(-27.600131,-48.550319));
        locais.add(new Local(-27.602822,-48.548903));
        locais.add(new Local(-27.597402, -48.550786));
        locais.add(new Local(-27.598420, -48.558124));
        locais.add(new Local(-27.593656, -48.548393));
        locais.add(new Local(-27.594747, -48.5448));

        mClusterManager.addItems(locais);
    }

}
